package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Discount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface DiscountRepository extends JpaRepository<Discount, Long>{
    Optional<Discount> findDiscountByDiscountID(@Param("discountID") Long discountID);
    List<Discount> findAllDiscountByUserID(@Param("userID") Long userID);
    Discount findDiscountByUserIDAndDiscountCode(@Param("userID") Long userID, @Param("discountCode") String discountCode);
}
