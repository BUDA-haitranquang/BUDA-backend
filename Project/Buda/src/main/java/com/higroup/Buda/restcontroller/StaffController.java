package com.higroup.Buda.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.entities.StaffLogin;
import com.higroup.Buda.entities.StaffPosition;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/staff")

public class StaffController {
    private final StaffService staffService;
    private final RequestUtil requestUtil;

    @Autowired
    public StaffController(StaffService staffService, RequestUtil requestUtil){
        this.staffService = staffService;
        this.requestUtil = requestUtil;
    }
    @PostMapping(path = "/login")
    public ResponseEntity<?> correctLogin(@RequestBody @Valid StaffLogin staffLogin)
    {
        String account = staffLogin.getAccount();
        String password = staffLogin.getPassword();
        return ResponseEntity.ok(this.staffService.correctLogin(account, password));
    }
    
    @PostMapping(path = "/register")
    public ResponseEntity<?> registerNewStaff(HttpServletRequest request, @RequestBody Staff newStaff)
    {
        Long userID = requestUtil.getUserIDFromUserToken(request);
        return this.staffService.registerNewStaff(newStaff, userID);
    }

    @GetMapping(path = "/userID/all")
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

    @PutMapping(path = "id/{staffID}")
    public ResponseEntity<?> updateStaffByID(HttpServletRequest request, @PathVariable("staffID") Long staffID,
            @RequestParam(required = false) String name, @RequestParam(required = false) String address,
            @RequestParam(required = false) String phoneNumber, @RequestParam(required = false) StaffPosition staffPosition,
            @RequestParam(required = false) String staffUUID, @RequestParam(required = false) String password, 
            @RequestParam(required = false) Double salary, @RequestParam(required = false) String account) 
    {
        Long userID = requestUtil.getUserIDFromUserToken(request);
        return ResponseEntity.ok(
            staffService.updateStaffByID(staffID, userID, name, phoneNumber, password, address, salary, staffUUID, account, staffPosition)
        );
    }
}
