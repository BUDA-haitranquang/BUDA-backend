package com.higroup.Buda.api.staff.crud;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/staff/crud")

public class StaffController {
    private final StaffService staffService;
    private final RequestUtil requestUtil;

    @Autowired
    public StaffController(StaffService staffService, RequestUtil requestUtil){
        this.staffService = staffService;
        this.requestUtil = requestUtil;
    }
    

    @GetMapping(path = "/all")
    public List<Staff> findAllByUserID(HttpServletRequest request)
    {
        Long userID = requestUtil.getUserIDFromUserToken(request);
        return this.staffService.findAllByUserID(userID);
    }

    @DeleteMapping(path = "/id/{staffID}")
    public ResponseEntity<?> deleteStaffByStaffID(HttpServletRequest request, @PathVariable("staffID") Long staffID){
        Long userID = requestUtil.getUserIDFromUserToken(request);
        staffService.deleteStaffByID(staffID, userID);
        return ResponseEntity.ok().body("Delete Succesfully");
    }

    @PutMapping(path = "/update/{staffID}")
    public ResponseEntity<?> updateStaff(HttpServletRequest httpServletRequest, @RequestBody Staff staff, @PathVariable("staffID") Long staffID) throws IllegalAccessException, InvocationTargetException
    {
        Long userID = requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.staffService.updateStaff(userID, staffID, staff));
    }
}
