package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.SellOrderItem;
import com.higroup.Buda.repositories.SellOrderItemRepository;
import com.higroup.Buda.repositories.SellOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellOrderItemService {
    private final SellOrderItemRepository sellOrderItemRepository;
    private final SellOrderRepository sellOrderRepository;
    @Autowired
    public SellOrderItemService(SellOrderItemRepository sellOrderItemRepository, SellOrderRepository sellOrderRepository)
    {
        this.sellOrderRepository = sellOrderRepository;
        this.sellOrderItemRepository = sellOrderItemRepository;
    }
    public List<SellOrderItem> findAllBySellOrderID(Long sellOrderID)
    {
        Optional<SellOrder> sellOrder = this.sellOrderRepository.findSellOrderBySellOrderID(sellOrderID);
        if (sellOrder.isPresent())
        {
            return this.sellOrderItemRepository.findAllSellOrderItemBySellOrder(sellOrder.get());
        }
        return null;
    }
}
