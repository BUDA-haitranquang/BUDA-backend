package com.higroup.Buda.api.staff.view;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/staff/view")
@CrossOrigin("*")
public class ViewStaffController {
    private RequestUtil requestUtil;
    private ViewStaffService viewStaffService;
    @Autowired
    public ViewStaffController(RequestUtil requestUtil, ViewStaffService viewStaffService){
        this.requestUtil = requestUtil;
        this.viewStaffService = viewStaffService;
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAllStaffByCurrentUser(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewStaffService.findAllStaffByUserID(userID));
    }
    @GetMapping("/id/{staffID}")
    public ResponseEntity<?> findStaffByStaffID(HttpServletRequest httpServletRequest, @PathVariable Long staffID){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewStaffService.findStaffByStaffID(userID, staffID));
    }
}
