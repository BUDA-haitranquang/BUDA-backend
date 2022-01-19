package com.higroup.Buda.api.statistics.revenue.receipt;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/weekly")
    public ResponseEntity<?> findReceiptRevenueGroupByWeek(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.receiptRevenueService.findReceiptRevenueGroupByWeek(userID));
    }
    @GetMapping("/this-month")
    public ResponseEntity<?> findReceiptRevenueCurrentMonth(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.receiptRevenueService.findReceiptRevenueCurrentMonth(userID));
    }
    @GetMapping("/monthly")
    public ResponseEntity<?> findReceiptRevenueGroupByMonth(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.receiptRevenueService.findReceiptRevenueGroupByMonth(userID));
    }
}
