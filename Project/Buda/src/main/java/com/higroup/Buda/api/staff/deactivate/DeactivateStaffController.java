package com.higroup.Buda.api.staff.deactivate;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/staff/deactivate")
@CrossOrigin("*")
public class DeactivateStaffController {
    private final DeactivateStaffService deactivateStaffService;
    private final RequestUtil requestUtil;
    @Autowired
    public DeactivateStaffController(DeactivateStaffService deactivateStaffService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.deactivateStaffService = deactivateStaffService;
    }
    @PutMapping(path = "id/{staffID}")
    public ResponseEntity<?> deactivateStaffByStaffID(HttpServletRequest httpServletRequest, @PathVariable Long staffID){
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.deactivateStaffService.deactivateStaffByStaffID(userID, staffID));
    }
}
