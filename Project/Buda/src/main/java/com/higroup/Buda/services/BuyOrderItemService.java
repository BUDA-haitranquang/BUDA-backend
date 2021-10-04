package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.BuyOrderItem;
import com.higroup.Buda.repositories.BuyOrderItemRepository;
import com.higroup.Buda.repositories.BuyOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyOrderItemService {
    private final BuyOrderItemRepository buyOrderItemRepository;
    private final BuyOrderRepository buyOrderRepository;
    @Autowired
    public BuyOrderItemService(BuyOrderItemRepository buyOrderItemRepository, BuyOrderRepository buyOrderRepository)
    {
        this.buyOrderRepository = buyOrderRepository;
        this.buyOrderItemRepository = buyOrderItemRepository;
    }
    public List<BuyOrderItem> findAllByBuyOrderID(Long buyOrderID)
    {
        Optional<BuyOrder> BuyOrder = this.buyOrderRepository.findBuyOrderByBuyOrderID(buyOrderID);
        return BuyOrder.map(this.buyOrderItemRepository::findAllBuyOrderItemByBuyOrder).orElse(null);
    }
}
