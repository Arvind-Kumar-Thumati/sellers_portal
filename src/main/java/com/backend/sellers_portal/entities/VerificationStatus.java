package com.backend.sellers_portal.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VerificationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    public Integer id;

    private String status;

    public VerificationStatus(){}

    public VerificationStatus(String status){
        this.status = status;
    }

    public Integer getId() {
        return id;
    }
    public String getStatus() {
        return status;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
