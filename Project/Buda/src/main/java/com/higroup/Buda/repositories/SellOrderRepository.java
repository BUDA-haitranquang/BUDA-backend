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
import com.higroup.Buda.repositories.fetchdefault.FetchDefault;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface SellOrderRepository extends PagingAndSortingRepository<SellOrder, Long> {
        @Query(value = FetchDefault.sellOrder
                        // + "LEFT JOIN FETCH ss.roles "
                        + "where s.sellOrderID = :sellOrderID ")
        Optional<SellOrder> findSellOrderBySellOrderID(Long sellOrderID);

        @Query(value = FetchDefault.sellOrder
                        // + "LEFT JOIN FETCH ss.roles "
                        + "where s.userID = :userID ")
        List<SellOrder> findAllSellOrderByUserID(Long userID, Pageable pageable);

        @Query(value = "select count(*) from SellOrder s "
                        + "where s.userID = :userID ")
        Long countAllSellOrderByUserID(Long userID);
        
        @Query(value = "select distinct s from SellOrder s "
                        + "LEFT JOIN FETCH s.sellOrderItems si "
                        + "LEFT JOIN FETCH si.product pr "
                        + "LEFT JOIN FETCH pr.picture "
                        + "LEFT JOIN FETCH s.customer c "
                        + "LEFT JOIN FETCH s.staff ss LEFT JOIN FETCH ss.roles where s.userID = :userID and s.textID = :textID")
        List<SellOrder> findAllSellOrderByUserIDAndTextID(Long userID, String textID, Pageable pageable);

        List<SellOrder> findAllSellOrderByCustomer(Customer customer);

        @Query(value = "select * from sell_order s where s.discount_id = :discountID", nativeQuery = true)
        List<SellOrder> findAllSellOrderByDiscountID(@Param("discountID") Long discountID);

        @Query(value = "select * from sell_order s "
                        + "LEFT JOIN FETCH s.sellOrderItems si "
                        + "LEFT JOIN FETCH si.product pr "
                        + "LEFT JOIN FETCH pr.picture "
                        + "LEFT JOIN FETCH s.customer c "
                        + "LEFT JOIN FETCH s.staff ss "
                        // + "LEFT JOIN FETCH ss.roles "
                        + "where s.status LIKE :status and s.user_id = :userID", nativeQuery = true)
        List<SellOrder> findAllSellOrderByStatusAndUserID(@Param("userID") Long userID, @Param("status") String status, Pageable pageable);

        // @Query(value = "select * from sell_order s where (s.creation_time BETWEEN
        // NOW() - INTERVAL :X DAY and NOW()) and s.user_id = :userID", nativeQuery =
        // true)
        @Query(value = FetchDefault.sellOrder
                        // + "LEFT JOIN FETCH ss.roles "
                        + "where (s.creationTime >= :X) and s.userID = :userID")
        List<SellOrder> findAllSellOrderByUserIDLastXDays(@Param("userID") Long userID, @Param("X") ZonedDateTime X);

        // @Query(value = "select * from sell_order s where s.status NOT LIKE 'FINISHED'
        // and s.status NOT LIKE 'CANCELLED' and s.user_id = :userID", nativeQuery =
        // true)
        @Query(value = FetchDefault.sellOrder
                        // + "LEFT JOIN FETCH ss.roles "
                        + "where s.status NOT LIKE 'FINISHED' and s.status NOT LIKE 'CANCELLED' and s.userID = :userID")
        List<SellOrder> findAllIncompletedSellOrderByUser(@Param("userID") Long userID);

        @Query(value = FetchDefault.sellOrder
                        // + "LEFT JOIN FETCH ss.roles "
                        + " where s.status LIKE 'FINISHED' and s.userID = :userID")
        List<SellOrder> findAllCompletedSellOrderByUser(@Param("userID") Long userID, Pageable pageable);

        @Query(value = FetchDefault.sellOrder
                        // + "LEFT JOIN FETCH ss.roles "
                        + " where s.status = :status and s.userID = :userID")
        List<SellOrder> findAllSellOrderByUserIDAndStatus(Long userID, Status status, Pageable pageable);

        @Query(value = "select new com.higroup.Buda.customDTO.AgeGroupStatistics(s.ageGroup, SUM(s.finalCost))"
                        + " from SellOrder s WHERE s.userID = :userID"
                        + " and s.status = 'FINISHED'"
                        + " GROUP BY s.ageGroup")
        List<AgeGroupStatistics> findTotalSpendOfAgeGroupByUserID(Long userID);

        @Query(value = "select new com.higroup.Buda.customDTO.AgeGroupStatistics(s.ageGroup, SUM(s.finalCost))"
                        + " from SellOrder s WHERE s.userID = :userID"
                        + " and s.status = 'FINISHED'"
                        + " and month(s.creationTime) >= (month(current_date) - 1) and year(s.creationTime) = year(current_date)"
                        + " GROUP BY s.ageGroup")
        List<AgeGroupStatistics> findCurrentMonthSpendOfAgeGroupByUserID(Long userID);

        @Query(value = "select new com.higroup.Buda.customDTO.GenderStatistics(s.gender, SUM(s.finalCost))"
                        + " from SellOrder s where s.userID = :userID"
                        + " and s.status = 'FINISHED'"
                        + " GROUP BY s.gender")
        List<GenderStatistics> findTotalSpendOfGenderByUserID(Long userID);

        @Query(value = "select new com.higroup.Buda.customDTO.GenderStatistics(s.gender, SUM(s.finalCost))"
                        + " from SellOrder s where s.userID = :userID"
                        + " and month(s.creationTime) >= (month(current_date) - 1) and year(s.creationTime) = year(current_date)"
                        + " and s.status = 'FINISHED'"
                        + " GROUP BY s.gender")
        List<GenderStatistics> findCurrentMonthSpendOfGenderByUserID(Long userID);

        @Query(value = "select new com.higroup.Buda.customDTO.RevenueByTimeStatistics(DATE_FORMAT(s.creationTime, '%m-%Y'), SUM(s.finalCost))"
                        + " from SellOrder s where s.userID = :userID and year(s.creationTime) >= (year(current_date) - 1)"
                        + " and s.status = 'FINISHED'"
                        + " GROUP BY DATE_FORMAT(s.creationTime, '%m-%Y')"
                        + " ORDER BY s.creationTime")
        List<RevenueByTimeStatistics> findRevenueGroupByMonth(Long userID);

        @Query(value = "select new com.higroup.Buda.customDTO.RevenueByTimeStatistics(DATE_FORMAT(s.creationTime, '%V-%Y'), SUM(s.finalCost))"
                        + " from SellOrder s where s.userID = :userID"
                        + " and s.status = 'FINISHED'"
                        + " GROUP BY DATE_FORMAT(s.creationTime, '%V-%Y')"
                        + " ORDER BY s.creationTime")
        List<RevenueByTimeStatistics> findRevenueGroupByWeek(Long userID);

        @Query(value = "select new com.higroup.Buda.customDTO.RevenueByTimeStatistics(DATE_FORMAT(s.creationTime, '%W-%Y'), SUM(s.finalCost))"
                        + " from SellOrder s where s.userID = :userID and year(s.creationTime) = year(current_date)"
                        + " and s.status = 'FINISHED'"
                        + " GROUP BY DATE_FORMAT(s.creationTime, '%W-%Y')"
                        + " ORDER BY s.creationTime")
        List<RevenueByTimeStatistics> findRevenueGroupByWeekday(Long userID);

        @Query(value = "select new com.higroup.Buda.customDTO.RevenueByTimeStatistics(DATE_FORMAT(s.creationTime, '%d-%m-%Y'), SUM(s.finalCost))"
                        + " from SellOrder s where s.userID = :userID and month(s.creationTime) = month(current_date) and year(s.creationTime) = year(current_date)"
                        + " and s.status = 'FINISHED'"
                        + " GROUP BY DATE_FORMAT(s.creationTime, '%d-%m-%Y')"
                        + " ORDER BY s.creationTime")
        List<RevenueByTimeStatistics> findRevenueAllDaysCurrentMonth(Long userID);

        @Query(value = "select new com.higroup.Buda.customDTO.RevenueByTimeStatistics(DATE_FORMAT(s.creationTime, '%d-%m-%Y'), SUM(s.finalCost))"
                        + " from SellOrder s where s.userID = :userID and s.creationTime >= :from and s.creationTime <= :to"
                        + " and s.status = 'FINISHED'"
                        + " GROUP BY DATE_FORMAT(s.creationTime, '%d-%m-%Y')"
                        + " ORDER BY s.creationTime")
        List<RevenueByTimeStatistics> findRevenueInPeriod(Long userID, ZonedDateTime from, ZonedDateTime to);

        @Query(value = "select new com.higroup.Buda.customDTO.ActiveHoursStatistics(DATE_FORMAT(s.creationTime, '%H'), COUNT(s.sellOrderID))"
                        + " from SellOrder s where s.userID = :userID and s.status = 'FINISHED'"
                        + " group by DATE_FORMAT(s.creationTime, '%H')"
                        + " ORDER BY s.creationTime")
        List<ActiveHoursStatistics> findTotalCountGroupByHours(Long userID);

        @Query(value = "select new com.higroup.Buda.customDTO.ActiveHoursStatistics(DATE_FORMAT(s.creationTime, '%H'), COUNT(s.sellOrderID))"
                        + " from SellOrder s where s.userID = :userID and s.status = 'FINISHED'"
                        + " and month(s.creationTime) >= (month(current_date) - 1) and year(s.creationTime) = year(current_date)"
                        + " group by DATE_FORMAT(s.creationTime, '%H')"
                        + " ORDER BY s.creationTime")
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
