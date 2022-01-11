package com.higroup.Buda.api.business.buy.delay.pay;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/business/buy/delay/pay")
@CrossOrigin("*")
public class PayDelayedBuyOrderController {
    private final RequestUtil requestUtil;
    private final PayDelayedBuyOrderService payDelayedBuyOrderService;
    @Autowired
    public PayDelayedBuyOrderController(RequestUtil requestUtil, PayDelayedBuyOrderService payDelayedBuyOrderService)
    {
        this.requestUtil = requestUtil;
        this.payDelayedBuyOrderService = payDelayedBuyOrderService;
    }
}
