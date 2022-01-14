package com.higroup.Buda.api.business.buy.delay;

import com.higroup.Buda.repositories.BuyOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DelayPaymentBuyOrderService {
    private final BuyOrderRepository buyOrderRepository;
    @Autowired
    public DelayPaymentBuyOrderService(BuyOrderRepository buyOrderRepository)
    {
        this.buyOrderRepository = buyOrderRepository;
    }
}
