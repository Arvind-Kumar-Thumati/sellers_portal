package com.backend.sellers_portal.entities;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
// import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
// import javax.persistence.OneToMany;

// import com.fasterxml.jackson.annotation.JsonIdentityInfo;
// import com.fasterxml.jackson.annotation.ObjectIdGenerators;

// import org.hibernate.annotations.Cascade;

// @JsonIdentityInfo(
//         generator = ObjectIdGenerators.PropertyGenerator.class,
//         property = "id"
// )
@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    public Integer id;
    private String name;
    // Need to implement random code
    private String productCode = UUID.randomUUID().toString();
    private Integer price;
    
    private Integer sellerId;
    private Integer stockAvailable;
    private String imageUrl;
    private Integer verificationStatusId = 1;
    
    @ManyToMany(targetEntity = Categories.class, cascade = {CascadeType.MERGE ,CascadeType.DETACH })    
    @JoinTable(
        name = "category_product_table"
        ,joinColumns = @JoinColumn( name="products_id", referencedColumnName = "id")
        ,inverseJoinColumns = @JoinColumn( name="category_id", referencedColumnName = "categoryId")
    )    
    private List<Categories> categories;
    // private List<Integer> categories;

    // @ManyToMany(targetEntity = Categories.class, cascade = CascadeType.MERGE) //, mappedBy = "categoryId")
    // private List<Categories> categories;

    public Products(){}

    public Products(String name, Integer price, Integer sellerId, Integer stockAvailable, String imageUrl, List<Categories> categories){        
        this.name = name;
        this.price = price;
        this.sellerId = sellerId;
        this.stockAvailable = stockAvailable;
        this.imageUrl = imageUrl;
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getProductCode() {
        return productCode;
    }
    public Integer getPrice() {
        return price;
    }
    public Integer getSellerId() {
        return sellerId;
    }
    public Integer getStockAvailable() {
        return stockAvailable;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public List<Categories> getCategories() {
        return categories;
    }
    public Integer getVerificationStatusId() {
        return verificationStatusId;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }
    public void setStockAvailable(Integer stockAvailable) {
        this.stockAvailable = stockAvailable;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
    public void setVerificationStatusId(Integer verificationStatusId) {
        this.verificationStatusId = verificationStatusId;
    }

    @Override
    public String toString() {
        return "Products [categories=" + categories + ", id=" + id + ", imageUrl=" + imageUrl + ", name=" + name
                + ", price=" + price + ", productCode=" + productCode + ", sellerId=" + sellerId + ", stockAvailable="
                + stockAvailable + ", verificationStatusId=" + verificationStatusId + "]";
    }
}
