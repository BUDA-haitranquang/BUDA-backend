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
    private final StaffService staffService;

    @Autowired
    public SalaryLogController(SalaryLogService salaryLogService, RequestUtil requestUtil, StaffService staffService)
    {
        this.staffService = staffService;
        this.requestUtil = requestUtil;
        this.salaryLogService = salaryLogService;
    }
    @PostMapping("userID/{userID}")
    public ResponseEntity<?> registerNewSalaryLog(HttpServletRequest request, @PathVariable Long userID, @RequestBody SalaryLog salaryLog)
    {
        Long jwtuserID = requestUtil.getUserIDFromUserToken(request);
        if(!jwtuserID.equals(userID)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
        }
        return ResponseEntity.ok(this.salaryLogService.registerNewSalaryLog(userID, salaryLog));
    }
    @GetMapping("userID/{userID}/all")
    public ResponseEntity<?> findAllByUserID(HttpServletRequest request, @PathVariable Long userID)
    {
        Long jwtuserID = requestUtil.getUserIDFromUserToken(request);
        if(!jwtuserID.equals(userID)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
        }
        return ResponseEntity.ok(this.salaryLogService.findAllByUserID(userID));
    }

    @GetMapping("staffID/{staffID}/all")
    public ResponseEntity<?> findAllByStaffID(HttpServletRequest request, @PathVariable Long staffID)
    {
        Long jwtuserID = requestUtil.getUserIDFromUserToken(request);
        Staff staff = staffService.findStaffByID(staffID);
        if(staff == null){
            return ResponseEntity.badRequest().body("Staff not exists");
        }
        if(!staff.getUserID().equals(jwtuserID)){
            return ResponseEntity.badRequest().body("Staff not belong to user ID");
        }
        return ResponseEntity.ok(this.salaryLogService.findAllByStaffID(staffID));
    }

    @DeleteMapping("salary-logID/{salary_logID}")
    public ResponseEntity<?> deleteSalaryLogbyID(HttpServletRequest request, @PathVariable Long salary_logID){
        Long jwtuserID = requestUtil.getUserIDFromUserToken(request);
        SalaryLog salaryLog = salaryLogService.findByID(salary_logID);
        if(!salaryLog.getUserID().equals(jwtuserID)){
            return ResponseEntity.badRequest().body("SalaryLog not belong to user ID");
        }
        this.salaryLogService.deleteSalaryLogbyID(salary_logID);
        return ResponseEntity.ok("delete successfully");
    }
    
}
