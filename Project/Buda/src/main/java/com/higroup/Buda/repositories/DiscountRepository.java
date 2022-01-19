package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.customDTO.RevenueByTimeStatistics;
import com.higroup.Buda.entities.Discount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long>{
    Discount findDiscountByDiscountID(Long discountID);
    List<Discount> findAllDiscountByUserID(Long userID);
}
