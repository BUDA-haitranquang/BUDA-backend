package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.customDTO.ExpenseByTimeStatistics;
import com.higroup.Buda.entities.FixedCost;
import com.higroup.Buda.entities.FixedCostBill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FixedCostBillRepository extends JpaRepository<FixedCostBill, Long> {
    Optional<FixedCostBill> findFixedCostBillByFixedCostBillID(@Param("fixedCostBillID") Long fixedCostBillID);
    List<FixedCostBill> findAllByFixedCost(@Param("fixedCost") FixedCost fixedCost);
    List<FixedCostBill> findAllByUserID(@Param("userID") Long userID);
    @Query(value = "select * from fixed_cost_bill b where b.user_id = :userID and (b.creation_time BETWEEN CAST((NOW() - INTERVAL :X DAY) as DATE) and NOW())", nativeQuery = true)
    List<FixedCostBill> findAllFixedCostBillByUserIDLastXDays(@Param("userID") Long userID, @Param("X") Long X);
    @Query(value = "select * from fixed_cost_bill b where b.user_id = :userID and b.status != 'FINISHED' and b.status != 'CANCELLED'", nativeQuery = true)
    List<FixedCostBill> findAllIncompletedFixedCostBillByUser(@Param("userID") Long userID);
    @Query(value = "select new com.higroup.Buda.customDTO.ExpenseByTimeStatistics(DATE_FORMAT(f.closedTime, '%V-%Y'), SUM(f.totalSpend)) "
    + "from FixedCostBill f where f.userID = :userID and year(f.closedTime) = year(current_date) and f.status = 'FINISHED'"
    + "group by DATE_FORMAT(f.closedTime, '%V-%Y') "
    + "order by DATE_FORMAT(f.closedTime, '%V-%Y') ")
    List<ExpenseByTimeStatistics> findFixedCostBillExpenseByWeek(@Param("userID") Long userID);
    @Query(value = "select new com.higroup.Buda.customDTO.ExpenseByTimeStatistics(DATE_FORMAT(f.closedTime, '%d-%m-%Y'), SUM(f.totalSpend)) "
    + "from FixedCostBill f where f.userID = :userID and year(f.closedTime) = year(current_date) and month(f.closedTime) = month(current_date) and f.status = 'FINISHED'"
    + "group by DATE_FORMAT(f.closedTime, '%d-%m-%Y') "
    + "order by DATE_FORMAT(f.closedTime, '%d-%m-%Y') ")
    List<ExpenseByTimeStatistics> findFixedCostBillExpenseCurrentMonth(@Param("userID") Long userID);
    @Query(value = "select new com.higroup.Buda.customDTO.ExpenseByTimeStatistics(DATE_FORMAT(f.closedTime, '%m-%Y'), SUM(f.totalSpend)) "
    + "from FixedCostBill f where f.userID = :userID and year(f.closedTime) >= (year(current_date) - 1) and f.status = 'FINISHED'"
    + "group by DATE_FORMAT(f.closedTime, '%m-%Y') "
    + "order by DATE_FORMAT(f.closedTime, '%m-%Y') ")
    List<ExpenseByTimeStatistics> findFixedCostBillExpenseGroupByMonth(@Param("userID") Long userID);
}
