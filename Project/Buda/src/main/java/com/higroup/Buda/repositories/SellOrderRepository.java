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
    List<SellOrder> findAllSellOrderByStatus(Status status);
}
