package com.higroup.Buda.restcontroller;

import java.util.List;

import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.entities.StaffLogin;
import com.higroup.Buda.services.StaffService;

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
@RequestMapping("api/staff")

public class StaffController {
    private final StaffService staffService;
    @Autowired
    public StaffController(StaffService staffService){
        this.staffService = staffService;
    }
    @PostMapping(path = "/login")
    public ResponseEntity<?> correctLogin(@RequestBody StaffLogin staffLogin)
    {
        String uuid = staffLogin.getUuid();
        String password = staffLogin.getPassword();
        return this.staffService.correctLogin(uuid, password);
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
}
