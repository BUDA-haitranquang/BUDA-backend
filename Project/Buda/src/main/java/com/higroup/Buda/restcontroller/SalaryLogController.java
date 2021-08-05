package com.higroup.Buda.restcontroller;

import java.util.List;

import com.higroup.Buda.entities.SalaryLog;
import com.higroup.Buda.services.SalaryLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    @Autowired
    public SalaryLogController(SalaryLogService salaryLogService)
    {
        this.salaryLogService = salaryLogService;
    }
    @PostMapping("userID/{userID}")
    public ResponseEntity<?> registerNewSalaryLog(@PathVariable Long userID, @RequestBody SalaryLog salaryLog)
    {
        return this.salaryLogService.registerNewSalaryLog(userID, salaryLog);
    }
    @GetMapping("userID/{userID}/all")
    public List<SalaryLog> findAllByUserID(@PathVariable Long userID)
    {
        return this.salaryLogService.findAllByUserID(userID);
    }
    @GetMapping("staffID/{staffID}/all")
    public List<SalaryLog> findAllByStaffID(@PathVariable Long staffID)
    {
        return this.salaryLogService.findAllByStaffID(staffID);
    }
}
