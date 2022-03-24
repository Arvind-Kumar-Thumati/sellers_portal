package com.backend.sellers_portal.configs;

import java.util.HashMap;
import java.util.Map;

import com.backend.sellers_portal.entities.Products;
// import com.fasterxml.jackson.databind.JsonDeserializer;
import com.backend.sellers_portal.entities.ProductsUpdateStatus;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
    @Value(value = "${kafka.groupId}")
    private String groupId;

    @Bean
    ConsumerFactory<String, Products> productConsumerFactory(){
        JsonDeserializer<Products> deserializer = new JsonDeserializer<>(Products.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        System.out.println("ConsumerFactory "+bootstrapAddress+ " " + groupId);
        Map<String, Object> props = new HashMap<>();
        props.put( ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);    
        // return new DefaultKafkaConsumerFactory<>(props);     
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);        
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Products> productKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, Products> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(productConsumerFactory());
        return factory;
    }

    @Bean
    ConsumerFactory<String, ProductsUpdateStatus> pipelineConsumerFactory(){
        JsonDeserializer<ProductsUpdateStatus> deserializer = new JsonDeserializer<>(ProductsUpdateStatus.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        System.out.println("ConsumerFactory "+bootstrapAddress+ " " + groupId);
        Map<String, Object> props = new HashMap<>();
        props.put( ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);    
        // return new DefaultKafkaConsumerFactory<>(props);     
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);        
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, ProductsUpdateStatus> pipelineKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, ProductsUpdateStatus> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(pipelineConsumerFactory());
        return factory;
    }
}
