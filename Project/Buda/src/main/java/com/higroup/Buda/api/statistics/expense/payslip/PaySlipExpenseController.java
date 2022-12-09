package com.higroup.Buda.api.statistics.expense.payslip;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/statistics/expense/pay-slip")
@CrossOrigin("*")
public class PaySlipExpenseController {
    private final PaySlipExpenseService paySlipExpenseService;
    private final RequestUtil requestUtil;
    @Autowired
    public PaySlipExpenseController(PaySlipExpenseService paySlipExpenseService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.paySlipExpenseService = paySlipExpenseService;
    }
    @GetMapping("/monthly")
    public ResponseEntity<?> findPaySlipExpenseGroupByMonth(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.paySlipExpenseService.findPaySlipExpenseGroupByMonth(userID));
    }
    @GetMapping("/weekly")
    public ResponseEntity<?> findPaySlipExpenseByWeek(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.paySlipExpenseService.findPaySlipExpenseByWeek(userID));
    }
    @GetMapping("/this-month")
    public ResponseEntity<?> findPaySlipExpenseCurrentMonth(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.paySlipExpenseService.findPaySlipExpenseCurrentMonth(userID));
    }
}
