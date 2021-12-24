package com.higroup.Buda.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.SalaryLog;
import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.services.SalaryLogService;
import com.higroup.Buda.services.StaffService;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/salary-log")
public class SalaryLogController {
    private final SalaryLogService salaryLogService;
    private final RequestUtil requestUtil;

    @Autowired
    public SalaryLogController(SalaryLogService salaryLogService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.salaryLogService = salaryLogService;
    }
    @PostMapping("register")
    public ResponseEntity<?> registerNewSalaryLog(HttpServletRequest request, @RequestBody SalaryLog salaryLog)
    {
        Long userID = requestUtil.getUserIDFromUserToken(request);
        return ResponseEntity.ok(this.salaryLogService.registerNewSalaryLog(userID, salaryLog));
    }
    @GetMapping("userID/all")
    public ResponseEntity<?> findAllByUserID(HttpServletRequest request)
    {
        Long userID = requestUtil.getUserIDFromUserToken(request);
        return ResponseEntity.ok(this.salaryLogService.findAllByUserID(userID));
    }

    @GetMapping("staffID/{staffID}/all")
    public ResponseEntity<?> findAllByStaffID(HttpServletRequest request, @PathVariable Long staffID)
    {
        Long userID = requestUtil.getUserIDFromUserToken(request);
        return ResponseEntity.ok(this.salaryLogService.findAllByStaffID(staffID, userID));
    }

    @DeleteMapping("salary-logID/{salary_logID}")
    public ResponseEntity<?> deleteSalaryLogbyID(HttpServletRequest request, @PathVariable Long salary_logID){
        Long jwtuserID = requestUtil.getUserIDFromUserToken(request);
        this.salaryLogService.deleteSalaryLogbyID(salary_logID, jwtuserID);
        return ResponseEntity.ok("delete successfully");
    }

    @GetMapping("expense/this-month")
    public ResponseEntity<?> findSalaryLogExpenseCurrentMonth(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.salaryLogService.findSalaryLogExpenseCurrentMonth(userID));
    }
    
    @GetMapping("expense/monthly")
    public ResponseEntity<?> findSalaryLogExpenseGroupByMonth(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.salaryLogService.findSalaryLogExpenseGroupByMonth(userID));
    }
}
