package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.ProductComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ProductComponentRepository extends JpaRepository<ProductComponent, Long> {
    @Query(value = "select * from product_component pc where pc.product_id = :productID and pc.ingredient_id = :ingredientID", nativeQuery = true)
    Optional<ProductComponent> findByProductAndIngredient(Long productID, Long ingredientID);
    @Query(value = "select * from product_component pc where pc.product_id = :productID", nativeQuery = true)
    List<ProductComponent> findAllByProductID(@Param("productID") Long productID);
    
}
