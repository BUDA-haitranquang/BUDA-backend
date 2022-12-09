package com.higroup.Buda.api.statistics.expense.othercost;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("api/statistics/expense/other-cost")
@CrossOrigin("*")
public class OtherCostExpenseController {
    private final RequestUtil requestUtil;
    private final OtherCostExpenseService otherCostExpenseService;
    @Autowired
    public OtherCostExpenseController(RequestUtil requestUtil, OtherCostExpenseService otherCostExpenseService){
        this.requestUtil = requestUtil;
        this.otherCostExpenseService = otherCostExpenseService;
    }
    @GetMapping("/weekly")
    public ResponseEntity<?> findOtherCostExpenseByWeek(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.otherCostExpenseService.findOtherCostExpenseByWeek(userID));
    }
    @GetMapping("/this-month")
    public ResponseEntity<?> findOtherCostExpenseCurrentMonth(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.otherCostExpenseService.findOtherCostExpenseCurrentMonth(userID));
    }
    @GetMapping("/monthly")
    public ResponseEntity<?> findOtherCostExpenseGroupByMonth(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.otherCostExpenseService.findOtherCostExpenseGroupByMonth(userID));
    }
}
