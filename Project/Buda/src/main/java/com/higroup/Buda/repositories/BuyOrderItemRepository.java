package com.higroup.Buda.repositories;

import com.higroup.Buda.entities.BuyOrderItem;
import com.higroup.Buda.entities.BuyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyOrderItemRepository extends JpaRepository<BuyOrderItem, Long> {
    List<BuyOrderItem> findAllBuyOrderItemByBuyOrder(BuyOrder BuyOrder);
}
