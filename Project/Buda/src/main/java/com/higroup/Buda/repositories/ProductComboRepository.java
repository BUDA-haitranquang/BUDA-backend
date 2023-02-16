package com.higroup.Buda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.higroup.Buda.entities.ProductCombo;

public interface ProductComboRepository extends JpaRepository<ProductCombo, Long>{
    @Query(value = "select * from product_combo p where p.user_id = :userID", nativeQuery = true)
    List<ProductCombo> findAllByUserID(@Param("userID") Long userID);
    @Query(value = "select * from product_combo p where p.product_combo_id in "
    + "(select ppc.product_combo_id from product_combo_item ppc where ppc.product_id = :productID) and p.user_id = :userID", nativeQuery = true)
    List<ProductCombo> findAllProductComboIncludeProduct(@Param("userID") Long userID, @Param("productID") Long productID);
}
