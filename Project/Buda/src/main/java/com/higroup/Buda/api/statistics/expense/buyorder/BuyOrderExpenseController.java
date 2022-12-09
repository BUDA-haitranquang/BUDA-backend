package com.higroup.Buda.api.statistics.expense.buyorder;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/statistics/expense/buy-order")
@CrossOrigin("*")
public class BuyOrderExpenseController {
    private final RequestUtil requestUtil;
    private final BuyOrderExpenseService buyOrderExpenseService;
    @Autowired
    public BuyOrderExpenseController(RequestUtil requestUtil, BuyOrderExpenseService buyOrderExpenseService)
    {
        this.requestUtil = requestUtil;
        this.buyOrderExpenseService = buyOrderExpenseService;
    }
    @GetMapping(path = "/monthly")
    public ResponseEntity<?> findBuyOrderExpenseGroupByMonth(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.buyOrderExpenseService.findBuyOrderExpenseGroupByMonth(userID));
    }
    @GetMapping(path = "/this-month")
    public ResponseEntity<?> findBuyOrderExpenseCurrentMonth(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.buyOrderExpenseService.findBuyOrderExpenseCurrentMonth(userID));
    }
    @GetMapping(path = "/weekly")
    public ResponseEntity<?> findBuyOrderExpenseByWeek(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.buyOrderExpenseService.findBuyOrderExpenseByWeek(userID));
    }
}
