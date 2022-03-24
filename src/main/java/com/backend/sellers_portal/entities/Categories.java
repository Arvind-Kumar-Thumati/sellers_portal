package com.backend.sellers_portal.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Categories implements Serializable{
    @Override
    public String toString() {
        return "Categories [categoryId=" + categoryId + ", name=" + name + "]";
    }
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    public Integer categoryId;

    private String name;
    public Categories(){}

    public Categories(Integer id, String name){
        this.categoryId = id;
        this.name = name;
    }
    public Categories(String name){
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }
    public String getName() {
        return name;
    }

    public void setCategoryId(Integer id) {
        this.categoryId = id;
    }
    public void setName(String name) {
        this.name = name;
    }

}
