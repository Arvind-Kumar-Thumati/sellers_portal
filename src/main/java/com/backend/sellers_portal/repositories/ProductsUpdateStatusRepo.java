package com.backend.sellers_portal.repositories;

import com.backend.sellers_portal.entities.ProductsUpdateStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsUpdateStatusRepo extends JpaRepository<ProductsUpdateStatus, Integer>{
    
}
