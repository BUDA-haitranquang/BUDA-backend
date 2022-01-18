package com.higroup.Buda.api.statistics.revenue.receipt;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/statistics/revenue/receipt")
@CrossOrigin("*")
public class ReceiptRevenueController {
    private final ReceiptRevenueService receiptRevenueService;
    private final RequestUtil requestUtil;
    @Autowired
    public ReceiptRevenueController(RequestUtil requestUtil, ReceiptRevenueService receiptRevenueService){
        this.receiptRevenueService = receiptRevenueService;
        this.requestUtil = requestUtil;
    }
}
