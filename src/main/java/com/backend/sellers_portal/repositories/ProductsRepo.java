package com.backend.sellers_portal.repositories;

import com.backend.sellers_portal.entities.Products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Integer>{
    
}
