package com.higroup.Buda.repositories;

import java.util.List;

import com.higroup.Buda.customDTO.ExpenseByTimeStatistics;
import com.higroup.Buda.entities.PaySlip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaySlipRepository extends JpaRepository<PaySlip, Long>{
    PaySlip findPaySlipByPaySlipID(Long paySlipID);
    @Query("select p from PaySlip p where userID = :userID")
    List<PaySlip> findAllPaySlipByUserID(@Param("userID") Long userID);
    @Query("select new com.higroup.Buda.customDTO.ExpenseByTimeStatistics(DATE_FORMAT(f.creationTime, '%V-%Y'), SUM(f.totalCost)) "
    + "from PaySlip f where f.userID = :userID and year(f.creationTime) = year(current_date) "
    + "group by DATE_FORMAT(f.creationTime, '%V-%Y') "
    + "order by DATE_FORMAT(f.creationTime, '%V-%Y') ")
    List<ExpenseByTimeStatistics> findPaySlipExpenseByWeek(@Param("userID") Long userID);
    @Query(value = "select new com.higroup.Buda.customDTO.ExpenseByTimeStatistics(DATE_FORMAT(f.creationTime, '%d-%m-%Y'), SUM(f.totalCost)) "
    + "from PaySlip f where f.userID = :userID and year(f.creationTime) = year(current_date) and month(f.creationTime) = month(current_date) "
    + "group by DATE_FORMAT(f.creationTime, '%d-%m-%Y') "
    + "order by DATE_FORMAT(f.creationTime, '%d-%m-%Y') ")
    List<ExpenseByTimeStatistics> findPaySlipExpenseCurrentMonth(@Param("userID") Long userID);
    @Query(value = "select new com.higroup.Buda.customDTO.ExpenseByTimeStatistics(DATE_FORMAT(f.creationTime, '%m-%Y'), SUM(f.totalCost)) "
    + "from PaySlip f where f.userID = :userID and year(f.creationTime) >= (year(current_date) - 1) "
    + "group by DATE_FORMAT(f.creationTime, '%m-%Y') "
    + "order by DATE_FORMAT(f.creationTime, '%m-%Y') ")
    List<ExpenseByTimeStatistics> findPaySlipExpenseGroupByMonth(@Param("userID") Long userID);
}
