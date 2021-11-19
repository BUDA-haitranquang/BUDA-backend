package com.higroup.Buda.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseEntity<?> correctLogin(@RequestBody StaffLogin staffLogin)
    {
        String account = staffLogin.getAccount();
        String password = staffLogin.getPassword();
        return ResponseEntity.ok(this.staffService.correctLogin(account, password));
    }
    
    @PostMapping(path = "/register")
    public ResponseEntity<?> registerNewStaff(HttpServletRequest request, @RequestBody Staff newStaff)
    {
        Long userID = requestUtil.getUserID(request);
        newStaff.setUserID(userID);
        return this.staffService.registerNewStaff(newStaff);
    }

    @GetMapping(path = "/userID/all")
    public List<Staff> findAllByUserID(HttpServletRequest request)
    {
        Long userID = requestUtil.getUserID(request);
        return this.staffService.findAllByUserID(userID);
    }

    @DeleteMapping(path = "/id/{staffID}")
    public ResponseEntity<?> deleteStaffByStaffID(HttpServletRequest request, @PathVariable("staffID") Long id){
        Long userID = requestUtil.getUserID(request);
        Staff staff = staffService.getStaffByID(id);
        if(staff.getUserID() == userID){
            staffService.deleteStaffByID(id);
            return ResponseEntity.ok().body("Delete Succesfully");
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
        }
    }

    @PutMapping(path = "id/{staffID}")
    public ResponseEntity<?> updateStaffByID(HttpServletRequest request, @PathVariable("staffID") Long id,
            @RequestParam(required = false) String name, @RequestParam(required = false) String address,
            @RequestParam(required = false) String phoneNumber, @RequestParam(required = false) StaffPosition staffPosition,
            @RequestParam(required = false) String staffUUID, @RequestParam(required = false) String password, 
            @RequestParam(required = false) Double salary, @RequestParam(required = false) String account) 
    {
        Long userID = requestUtil.getUserID(request);
        Staff staff = staffService.getStaffByID(id);
        if(staff == null){
            return ResponseEntity.badRequest().body("Staff ID not exist: " + String.valueOf(id));
        }
        if(staff.getUserID() != userID){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
        }

        if(name == null){
            System.out.println("Name null");
            name = staff.getName();
        }
        if(address == null){
            System.out.println("Address null");
            address = staff.getAddress();
        }
        if(phoneNumber == null){
            System.out.println("Phone null");
            phoneNumber = staff.getPhoneNumber();
        }
        if(staffPosition == null){
            System.out.println("staff position null");
            staffPosition = staff.getStaffPosition();
        }
        if(staffUUID == null){
            System.out.println("staffUUID null");
            staffUUID = staff.getStaffUUID();
        }
        if(password == null){
            System.out.println("password null");
            password = staff.getPassword();
        }
        if(account == null){
            System.out.println("account null");
            account = staff.getAccount();
        }
        if(salary == null){
            System.out.println("salary null");
            salary = staff.getSalary();
        }
        return ResponseEntity.ok(
            staffService.updateStaffByID(id, name, phoneNumber, password, address, salary, staffUUID, account, staffPosition)
        );
    }
}
