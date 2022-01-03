package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.WarrantyOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WarrantyOrderRepository extends JpaRepository<WarrantyOrder, Long> {
    Optional<WarrantyOrder> findWarrantyOrderByWarrantyOrderID(Long warrantyOrderID);
    List<WarrantyOrder> findAllByUserID(Long userID);
    @Query(value = "select * from warranty_order w where w.product_id = :productID", nativeQuery = true)
    List<WarrantyOrder> findAllByProductID(@Param("productID") Long productID);
    @Query(value = "select * from warranty_order w where w.customer_id = :customerID", nativeQuery = true)
    List<WarrantyOrder> findAllByCustomerID(@Param("customerID") Long customerID);
}
