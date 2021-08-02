package com.higroup.Buda.restcontroller;

import com.higroup.Buda.services.StaffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/staff")

public class StaffController {
    private StaffService staffService;
    @Autowired
    public StaffController(StaffService staffService){
        this.staffService = staffService;
    }
}
