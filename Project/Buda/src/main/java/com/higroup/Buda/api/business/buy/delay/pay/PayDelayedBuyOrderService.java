package com.higroup.Buda.api.business.buy.delay.pay;

import com.higroup.Buda.repositories.BuyOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayDelayedBuyOrderService {
    private final BuyOrderRepository buyOrderRepository;
    @Autowired
    public PayDelayedBuyOrderService(BuyOrderRepository buyOrderRepository){
        this.buyOrderRepository = buyOrderRepository;
    }
}
