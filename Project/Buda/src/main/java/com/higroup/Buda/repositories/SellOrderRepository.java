package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.Status;

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
    @Query(value = "select * from sell_order s where (s.creation_time BETWEEN NOW() - INTERVAL :X DAY and NOW()) and s.user_id = :userID", nativeQuery = true)
    List<SellOrder> findAllSellOrderByUserIDLastXDays(@Param("userID") Long userID, @Param("X") Long X);
    @Query(value = "select * from sell_order s where s.status NOT LIKE 'FINISHED' and s.status NOT LIKE 'CANCELLED' and s.user_id = :userID", nativeQuery = true)
    List<SellOrder> findAllIncompletedSellOrderByUser(@Param("userID") Long userID);
    @Query(value = "select * from sell_order s where s.status LIKE 'FINISHED' and s.user_id = :userID", nativeQuery = true)
    List<SellOrder> findAllCompletedSellOrderByUser(@Param("userID") Long userID);
    List<SellOrder> findAllSellOrderByUserIDAndStatus(Long userID, Status status);
}
