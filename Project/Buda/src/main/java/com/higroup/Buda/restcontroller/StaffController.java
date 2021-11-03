package com.higroup.Buda.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.entities.StaffLogin;
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
        String uuid = staffLogin.getUuid();
        String password = staffLogin.getPassword();
        return ResponseEntity.ok(this.staffService.correctLogin(uuid, password));
    }
    
    @PostMapping(path = "/register")
    public ResponseEntity<?> registerNewStaff(@RequestBody Staff newStaff)
    {
        return this.staffService.registerNewStaff(newStaff);
    }

    @GetMapping(path = "userID/{userID}/all")
    public List<Staff> findAllByUserID(@PathVariable Long userID)
    {
        return this.staffService.findAllByUserID(userID);
    }

    @DeleteMapping(path = "id/{staffID}")
    public ResponseEntity<?> deleteStaffByStaffUUID(HttpServletRequest request, @PathVariable("StaffID") Long id){
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
}
