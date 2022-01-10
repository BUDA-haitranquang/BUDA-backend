package com.higroup.Buda.api.statistics.expense.salary;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/statistics/expense/salary")
@CrossOrigin("*")
public class SalaryExpenseController {
    private final RequestUtil requestUtil;
    private final SalaryExpenseService salaryExpenseService;
    @Autowired
    public SalaryExpenseController(RequestUtil requestUtil, SalaryExpenseService salaryExpenseService){
        this.requestUtil = requestUtil;
        this.salaryExpenseService = salaryExpenseService;
    }
    @GetMapping("/this-month")
    public ResponseEntity<?> findSalaryLogExpenseCurrentMonth(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.salaryExpenseService.findSalaryLogExpenseCurrentMonth(userID));
    }
    
    @GetMapping("/monthly")
    public ResponseEntity<?> findSalaryLogExpenseGroupByMonth(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.salaryExpenseService.findSalaryLogExpenseGroupByMonth(userID));
    }
}
