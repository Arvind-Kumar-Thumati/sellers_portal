package com.backend.sellers_portal.consumer;

import com.backend.sellers_portal.entities.Products;
import com.backend.sellers_portal.entities.ProductsUpdateStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AddProductsConsumer {

    @Autowired
    @Qualifier("eCommerce")
    KafkaTemplate<String, Products> kafkaTemplate;
    
    @Autowired
    @Qualifier("sellersPortal")
    KafkaTemplate<String, ProductsUpdateStatus> kafkaTemplateUpdate;
    
    @Value(value = "${kafka.sellersPortalTopic}")
    private String sellersTopic;

    @Value(value = "${kafka.productsUpdateTopic}")
    private String productsTopic;

    @Value(value = "${kafka.updateTopic}")
    private String updateTopic;

    void simulateProcess(){
        try{
            Thread.sleep(5000);
        }
        catch(Exception e){
            throw new IllegalStateException(e);
        }
    }


    @KafkaListener(topics = "${kafka.productsUpdateTopic}", groupId="${kafka.groupId}", containerFactory = "productKafkaListenerContainerFactory")
    public Products addProduct(Products product){
        System.out.println("Inside sellers addProduct : initialising pipeline..."+product);
        initiateValidationPipeline(product);        
        return product;
    }

    @KafkaListener(topics = "${kafka.sellersPortalTopic}", groupId = "{kafka.groupId}", containerFactory = "pipelineKafkaListenerContainerFactory")
    void updatePipeline(ProductsUpdateStatus productUpdateStatus){
        Integer currentStatus = productUpdateStatus.getVerificationStatusId();
        if( currentStatus < 5){
            currentStatus = productUpdateStatus.getVerificationStatusId();
            System.out.println("Validation at step "+currentStatus+" "+productUpdateStatus.getProduct());
            productUpdateStatus.getProduct().setVerificationStatusId(currentStatus+1);
            productUpdateStatus.setVerificationStatusId( currentStatus+1);

            currentStatus++;
            simulateProcess();
            kafkaTemplateUpdate.send(sellersTopic, productUpdateStatus);
        }
        else{
            kafkaTemplate.send(updateTopic, productUpdateStatus.getProduct());
        }
        // kafkaTemplate.send(updateTopic, productUpdateStatus.getProduct());
    }

    void initiateValidationPipeline(Products product){
        System.out.println("Validation pipeline started...");
        ProductsUpdateStatus productsUpdateStatus = new ProductsUpdateStatus(product, 1);
        System.out.println("Inside pipeline : "+productsUpdateStatus);
        kafkaTemplateUpdate.send(sellersTopic, productsUpdateStatus);
        // simulateProcess();
        // System.out.println("Validation pipeline finished...");
        // kafkaTemplate.send(updateTopic, product);
    }

}
