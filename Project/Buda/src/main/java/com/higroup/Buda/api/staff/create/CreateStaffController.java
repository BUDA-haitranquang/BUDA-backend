package com.higroup.Buda.api.staff.create;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/staff/create")
@CrossOrigin("*")
public class CreateStaffController {
    private final CreateStaffService createStaffService;
    private final RequestUtil requestUtil;
    @Autowired
    public CreateStaffController(CreateStaffService createStaffService, RequestUtil requestUtil){
        this.createStaffService = createStaffService;
        this.requestUtil = requestUtil;
    }
    @PostMapping
    public ResponseEntity<?> createStaff(HttpServletRequest httpServletRequest, @RequestBody Staff staff){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.createStaffService.createStaff(userID, staff));
    }
}
