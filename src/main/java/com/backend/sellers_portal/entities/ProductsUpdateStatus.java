package com.backend.sellers_portal.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ProductsUpdateStatus implements Serializable{
    @Override
    public String toString() {
        return "ProductsUpdateStatus [product=" + product + ", productsStausId=" + productsStausId
                + ", verificationStatusId=" + verificationStatusId + "]";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer productsStausId;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Products product;
    private Integer verificationStatusId=1;

    public ProductsUpdateStatus(){}
    
    public ProductsUpdateStatus(Products product){
        this.product = product;
    }

    public ProductsUpdateStatus(Products product, Integer verificationStatusId){
        this.product = product;
        this.verificationStatusId = verificationStatusId;
    }
    
    // public Integer getProductId() {
    //     return productId;
    // }

    // public void setProductId(Integer productId) {
    //     this.productId = productId;
    // }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Integer getVerificationStatusId() {
        return verificationStatusId;
    }

    public void setVerificationStatusId(Integer verificationStatusId) {
        this.verificationStatusId = verificationStatusId;
    }
}
