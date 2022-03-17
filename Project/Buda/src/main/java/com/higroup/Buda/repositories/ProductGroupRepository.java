package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.ProductGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long>{
    @Query(value = "select pg from ProductGroup pg LEFT JOIN FETCH pg.products p LEFT JOIN FETCH p.sellOrderItems where pg.productGroupID = :productGroupID")
    Optional<ProductGroup> findProductGroupByProductGroupID(Long productGroupID);
    List<ProductGroup> findAllByUserID(Long userID);
    @Query(value = "select * from product_group pg where pg.product_group_id in (select pgc.product_group_id from product_group_component pgc where pgc.product_id = :productID) and pg.user_id = :userID", nativeQuery = true)
    List<ProductGroup> findAllByProduct(@Param("userID") Long userID, @Param("productID") Long productID);
}
