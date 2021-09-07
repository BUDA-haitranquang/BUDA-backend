package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.FixedCost;
import com.higroup.Buda.entities.FixedCostBill;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FixedCostBillRepository extends JpaRepository<FixedCostBill, Long> {
    Optional<FixedCostBill> findFixedCostBillByFixedCostBillID(Long fixedCostBillID);
    List<FixedCostBill> findAllByFixedCost(FixedCost fixedCost);
    List<FixedCostBill> findAllByUserID(Long userID);
}
