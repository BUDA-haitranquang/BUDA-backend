package com.higroup.Buda.repositories;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import com.higroup.Buda.customDTO.ActiveHoursStatistics;
import com.higroup.Buda.customDTO.AgeGroupStatistics;
import com.higroup.Buda.customDTO.GenderStatistics;
import com.higroup.Buda.customDTO.RevenueByTimeStatistics;
import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.enumeration.Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SellOrderRepository extends JpaRepository<SellOrder, Long> {
    Optional<SellOrder> findSellOrderBySellOrderID(Long sellOrderID);
    List<SellOrder> findAllSellOrderByUserID(Long userID);
    List<SellOrder> findAllSellOrderByCustomer(Customer customer);
    @Query(value = "select * from sell_order s where s.discount_id = :discountID", nativeQuery = true)
    List<SellOrder> findAllSellOrderByDiscountID(@Param("discountID") Long discountID);
    @Query(value = "select * from sell_order s where s.status LIKE :status and s.user_id = :userID", nativeQuery = true)
    List<SellOrder> findAllSellOrderByStatusAndUserID(@Param("userID") Long userID, @Param("status") String status);
    //@Query(value = "select * from sell_order s where (s.creation_time BETWEEN NOW() - INTERVAL :X DAY and NOW()) and s.user_id = :userID", nativeQuery = true)
    @Query(value = "select s from SellOrder s where (s.creationTime > :X) and s.userID = :userID")
    List<SellOrder> findAllSellOrderByUserIDLastXDays(@Param("userID") Long userID, @Param("X") ZonedDateTime X);
    //@Query(value = "select * from sell_order s where s.status NOT LIKE 'FINISHED' and s.status NOT LIKE 'CANCELLED' and s.user_id = :userID", nativeQuery = true)
    @Query(value = "select s from SellOrder s where s.status NOT LIKE 'FINISHED' and s.status NOT LIKE 'CANCELLED' and s.userID = :userID")
    List<SellOrder> findAllIncompletedSellOrderByUser(@Param("userID") Long userID);
    @Query(value = "select * from sell_order s where s.status LIKE 'FINISHED' and s.user_id = :userID", nativeQuery = true)
    List<SellOrder> findAllCompletedSellOrderByUser(@Param("userID") Long userID);
    List<SellOrder> findAllSellOrderByUserIDAndStatus(Long userID, Status status);
    @Query(value = "select new com.higroup.Buda.customDTO.AgeGroupStatistics(s.ageGroup, SUM(s.realCost))"
    + " from SellOrder s WHERE s.userID = :userID"
    + " and s.status = 'FINISHED'"
    + " GROUP BY s.ageGroup")
    List<AgeGroupStatistics> findTotalSpendOfAgeGroupByUserID(Long userID);
    @Query(value = "select new com.higroup.Buda.customDTO.AgeGroupStatistics(s.ageGroup, SUM(s.realCost))"
    + " from SellOrder s WHERE s.userID = :userID"
    + " and s.status = 'FINISHED'"
    + " and month(s.creationTime) >= (month(current_date) - 1) and year(s.creationTime) = year(current_date)"
    + " GROUP BY s.ageGroup")
    List<AgeGroupStatistics> findCurrentMonthSpendOfAgeGroupByUserID(Long userID);
    @Query(value = "select new com.higroup.Buda.customDTO.GenderStatistics(s.gender, SUM(s.realCost))"
    + " from SellOrder s where s.userID = :userID"
    + " and s.status = 'FINISHED'"
    + " GROUP BY s.gender")
    List<GenderStatistics> findTotalSpendOfGenderByUserID(Long userID);
    @Query(value = "select new com.higroup.Buda.customDTO.GenderStatistics(s.gender, SUM(s.realCost))"
    + " from SellOrder s where s.userID = :userID"
    + " and month(s.creationTime) >= (month(current_date) - 1) and year(s.creationTime) = year(current_date)"
    + " and s.status = 'FINISHED'"
    + " GROUP BY s.gender")
    List<GenderStatistics> findCurrentMonthSpendOfGenderByUserID(Long userID);
    @Query(value = "select new com.higroup.Buda.customDTO.RevenueByTimeStatistics(DATE_FORMAT(s.creationTime, '%m-%Y'), SUM(s.realCost))"
    + " from SellOrder s where s.userID = :userID and year(s.creationTime) >= (year(current_date) - 1)"
    + " and s.status = 'FINISHED'"
    + " GROUP BY DATE_FORMAT(s.creationTime, '%m-%Y')"
    + " ORDER BY DATE_FORMAT(s.creationTime, '%V-%Y')")
    List<RevenueByTimeStatistics> findRevenueGroupByMonth(Long userID);
    @Query(value = "select new com.higroup.Buda.customDTO.RevenueByTimeStatistics(DATE_FORMAT(s.creationTime, '%V-%Y'), SUM(s.realCost))"
    + " from SellOrder s where s.userID = :userID"
    + " and s.status = 'FINISHED'"
    + " GROUP BY DATE_FORMAT(s.creationTime, '%V-%Y')"
    + " ORDER BY DATE_FORMAT(s.creationTime, '%V-%Y')")
    List<RevenueByTimeStatistics> findRevenueGroupByWeek(Long userID);
    @Query(value = "select new com.higroup.Buda.customDTO.RevenueByTimeStatistics(DATE_FORMAT(s.creationTime, '%W-%Y'), SUM(s.realCost))"
    + " from SellOrder s where s.userID = :userID and year(s.creationTime) = year(current_date)"
    + " and s.status = 'FINISHED'"
    + " GROUP BY DATE_FORMAT(s.creationTime, '%W-%Y')"
    + " ORDER BY DATE_FORMAT(s.creationTime, '%W-%Y')")
    List<RevenueByTimeStatistics> findRevenueGroupByWeekday(Long userID);
    @Query(value = "select new com.higroup.Buda.customDTO.RevenueByTimeStatistics(DATE_FORMAT(s.creationTime, '%d-%m-%Y'), SUM(s.realCost))"
    + " from SellOrder s where s.userID = :userID and month(s.creationTime) = month(current_date) and year(s.creationTime) = year(current_date)"
    + " and s.status = 'FINISHED'"
    + " GROUP BY DATE_FORMAT(s.creationTime, '%d-%m-%Y')"
    + " ORDER BY DATE_FORMAT(s.creationTime, '%d-%m-%Y')")
    List<RevenueByTimeStatistics> findRevenueAllDaysCurrentMonth(Long userID);

    @Query(value = "select new com.higroup.Buda.customDTO.ActiveHoursStatistics(DATE_FORMAT(s.creationTime, '%H'), COUNT(s.sellOrderID))"
    + " from SellOrder s where s.userID = :userID and s.status = 'FINISHED'"
    + " group by DATE_FORMAT(s.creationTime, '%H')"
    + " order by DATE_FORMAT(s.creationTime, '%H')")
    List<ActiveHoursStatistics> findTotalCountGroupByHours(Long userID);

    @Query(value = "select new com.higroup.Buda.customDTO.ActiveHoursStatistics(DATE_FORMAT(s.creationTime, '%H'), COUNT(s.sellOrderID))"
            + " from SellOrder s where s.userID = :userID and s.status = 'FINISHED'"
            + " and month(s.creationTime) >= (month(current_date) - 1) and year(s.creationTime) = year(current_date)"
            + " group by DATE_FORMAT(s.creationTime, '%H')"
            + " order by DATE_FORMAT(s.creationTime, '%H')")
    List<ActiveHoursStatistics> findCurrentMonthCountGroupByHours(Long userID);

    @Query(value = "select COUNT(s.sellOrderID)"
            + " from SellOrder s where s.userID = :userID and s.status = 'FINISHED'")
    Long findTotalCountSellOrderByUserID(Long userID);
    @Query(value = "select COUNT(s.sell_order_id)"
            + " from sell_order s where s.user_id = :userID and s.status = 'FINISHED'"
            + " and s.customer_id in"
            + " (select distinct s1.customer_id from sell_order s1 "
            + " where s1.user_id = :userID and month(s1.creation_time) >= (month(current_date) - 1) and year(s1.creation_time) = year(current_date))", nativeQuery = true)
    Long findCurrentMonthCountSellOrderByUserID(Long userID);
    @Query(value = "select COUNT(s.sell_order_id)"
            + " from sell_order s where s.user_id = :userID and s.status = 'FINISHED'"
            + " and s.customer_id in"
            + " (select distinct s1.customer_id from sell_order s1 "
            + " where s1.user_id = :userID and week(s1.creation_time) >= (week(current_date) - 1) and month(s1.creation_time) = (month(current_date)) and year(s1.creation_time) = year(current_date))", nativeQuery = true)
    Long findCurrentWeekCountSellOrderByUserID(Long userID);
}
