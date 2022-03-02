package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.customDTO.ExpenseByTimeStatistics;
import com.higroup.Buda.entities.SalaryLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalaryLogRepository extends JpaRepository<SalaryLog, Long>{
    Optional<SalaryLog> findSalaryLogBySalaryLogID(Long salaryLogID);
    List<SalaryLog> findAllByStaffID(Long staffID);
    List<SalaryLog> findAllByUserID(Long userID);
    @Query(value = "select new com.higroup.Buda.customDTO.ExpenseByTimeStatistics(DATE_FORMAT(f.creationTime, '%m-%Y'), SUM(f.salary)) "
    + "from SalaryLog f where f.userID = :userID and year(f.creationTime) >= (year(current_date) - 1) "
    + "group by DATE_FORMAT(f.creationTime, '%m-%Y') "
    + "order by DATE_FORMAT(f.creationTime, '%m-%Y') ")
    List<ExpenseByTimeStatistics> findSalaryLogExpenseGroupByMonth(Long userID);
    @Query(value = "select new com.higroup.Buda.customDTO.ExpenseByTimeStatistics(DATE_FORMAT(f.creationTime, '%d-%m-%Y'), SUM(f.salary)) "
    + "from SalaryLog f where f.userID = :userID and year(f.creationTime) = year(current_date) and month(f.creationTime) = month(current_date) and year(f.creationTime) = year(current_date) "
    + "group by DATE_FORMAT(f.creationTime, '%d-%m-%Y') "
    + "order by DATE_FORMAT(f.creationTime, '%d-%m-%Y') ")
    List<ExpenseByTimeStatistics> findSalaryLogExpenseCurrentMonth(Long userID);
}
