package com.higroup.Buda.repositories;

import java.util.Optional;

import com.higroup.Buda.entities.ProductComponent;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductComponentRepository extends JpaRepository<ProductComponent, Long> {
    Optional<ProductComponent> findByProductAndIngredient(Long productID, Long ingredientID);
    
}
