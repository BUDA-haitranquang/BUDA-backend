package com.higroup.Buda.repositories;

import java.util.List;

import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.SellOrderItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SellOrderItemRepository extends JpaRepository<SellOrderItem, Long>{
    List<SellOrderItem> findAllSellOrderItemBySellOrder(SellOrder sellOrder);
}
