package com.higroup.Buda.services;

import com.higroup.Buda.repositories.SellOrderItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellOrderItemService {
    private final SellOrderItemRepository sellOrderItemRepository;
    @Autowired
    public SellOrderItemService(SellOrderItemRepository sellOrderItemRepository)
    {
        this.sellOrderItemRepository = sellOrderItemRepository;
    }
}
