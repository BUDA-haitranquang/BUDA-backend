package com.higroup.Buda.api.statistics.expense.fixedcost;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("api/statistics/expense/fixed-cost")
@CrossOrigin("*")
public class FixedCostExpenseController {
    private final RequestUtil requestUtil;
    private final FixedCostExpenseService fixedCostExpenseService;
    @Autowired
    public FixedCostExpenseController(RequestUtil requestUtil, FixedCostExpenseService fixedCostExpenseService)
    {
        this.fixedCostExpenseService = fixedCostExpenseService;
        this.requestUtil = requestUtil;
    }

    @GetMapping("/weekly")
    public ResponseEntity<?> findFixedCostExpenseByWeek(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.fixedCostExpenseService.findFixedCostBillExpenseByWeek(userID));
    }
    @GetMapping("/this-month")
    public ResponseEntity<?> findFixedCostBillExpenseCurrentMonth(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.fixedCostExpenseService.findFixedCostBillExpenseCurrentMonth(userID));
    }
    @GetMapping("/monthly")
    public ResponseEntity<?> findFixedCostBillExpenseGroupByMonth(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.fixedCostExpenseService.findFixedCostBillExpenseGroupByMonth(userID));
    }
}
