package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.SellOrder;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SellOrderRepository extends JpaRepository<SellOrder, Long> {
    Optional<SellOrder> findSellOrderBySellOrderID(Long sellOrderID);
    List<SellOrder> findAllSellOrderByUserID(Long userID);
    List<SellOrder> findAllSellOrderByCustomer(Customer customer);
}
