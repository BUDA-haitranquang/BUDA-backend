package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.FixedCost;
import com.higroup.Buda.entities.FixedCostBill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FixedCostBillRepository extends JpaRepository<FixedCostBill, Long> {
    Optional<FixedCostBill> findFixedCostBillByFixedCostBillID(Long fixedCostBillID);
    List<FixedCostBill> findAllByFixedCost(FixedCost fixedCost);
    List<FixedCostBill> findAllByUserID(Long userID);
    @Query(value = "select * from fixed_cost_bill b where b.user_id = :userID and (b.creation_time BETWEEN NOW() - INTERVAL :X DAY and NOW())", nativeQuery = true)
    List<FixedCostBill> findAllFixedCostBillByUserIDLastXDays(@Param("userID") Long userID, @Param("X") Long X);
    @Query(value = "select * from fixed_cost_bill b where b.user_id = :userID and b.status != 'FINISHED' and b.status != 'CANCELLED'", nativeQuery = true)
    List<FixedCostBill> findAllIncompletedFixedCostBillByUser(@Param("userID") Long userID);
}
