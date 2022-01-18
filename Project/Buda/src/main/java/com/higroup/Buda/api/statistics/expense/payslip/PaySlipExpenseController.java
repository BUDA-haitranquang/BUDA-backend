package com.higroup.Buda.api.statistics.expense.payslip;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
}
