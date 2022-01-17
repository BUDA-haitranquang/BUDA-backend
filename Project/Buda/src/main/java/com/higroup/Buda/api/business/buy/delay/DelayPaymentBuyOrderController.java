package com.higroup.Buda.api.business.buy.delay;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/business/buy/delay")
@CrossOrigin("*")
public class DelayPaymentBuyOrderController {
    private final RequestUtil requestUtil;
    private final DelayPaymentBuyOrderService delayPaymentBuyOrderService;
    @Autowired
    public DelayPaymentBuyOrderController(RequestUtil requestUtil, DelayPaymentBuyOrderService delayPaymentBuyOrderService){
        this.requestUtil = requestUtil;
        this.delayPaymentBuyOrderService = delayPaymentBuyOrderService;
    }
}
