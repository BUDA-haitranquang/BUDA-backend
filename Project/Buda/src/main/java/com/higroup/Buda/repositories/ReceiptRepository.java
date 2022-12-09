package com.higroup.Buda.repositories;

import java.util.List;

import com.higroup.Buda.customDTO.RevenueByTimeStatistics;
import com.higroup.Buda.entities.Receipt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReceiptRepository extends JpaRepository<Receipt, Long>{
    Receipt findReceiptByReceiptID(@Param("receiptID") Long receiptID);
    @Query("select r from Receipt r where userID = :userID")
    List<Receipt> findAllByUserID(@Param("userID") Long userID);
    @Query("select new com.higroup.Buda.customDTO.RevenueByTimeStatistics(DATE_FORMAT(f.creationTime, '%V-%Y'), SUM(f.totalCost)) "
    + "from Receipt f where f.userID = :userID and year(f.creationTime) = year(current_date) "
    + "group by DATE_FORMAT(f.creationTime, '%V-%Y') "
    + "order by DATE_FORMAT(f.creationTime, '%V-%Y') ")
    List<RevenueByTimeStatistics> findReceiptRevenueByWeek(@Param("userID") Long userID);
    @Query(value = "select new com.higroup.Buda.customDTO.RevenueByTimeStatistics(DATE_FORMAT(f.creationTime, '%d-%m-%Y'), SUM(f.totalCost)) "
    + "from Receipt f where f.userID = :userID and year(f.creationTime) = year(current_date) and month(f.creationTime) = month(current_date) "
    + "group by DATE_FORMAT(f.creationTime, '%d-%m-%Y') "
    + "order by DATE_FORMAT(f.creationTime, '%d-%m-%Y') ")
    List<RevenueByTimeStatistics> findReceiptRevenueCurrentMonth(@Param("userID") Long userID);
    @Query(value = "select new com.higroup.Buda.customDTO.RevenueByTimeStatistics(DATE_FORMAT(f.creationTime, '%m-%Y'), SUM(f.totalCost)) "
    + "from Receipt f where f.userID = :userID and year(f.creationTime) >= (year(current_date) - 1) "
    + "group by DATE_FORMAT(f.creationTime, '%m-%Y') "
    + "order by DATE_FORMAT(f.creationTime, '%m-%Y') ")
    List<RevenueByTimeStatistics> findReceiptRevenueGroupByMonth(@Param("userID") Long userID);
}
