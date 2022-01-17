package com.higroup.Buda.api.business.payslip.create;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.PaySlip;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/business/pay-slip/create")
@CrossOrigin("*")
public class CreatePaySlipController {
    private final CreatePaySlipService createPaySlipService;
    private final RequestUtil requestUtil;
    @Autowired
    public CreatePaySlipController(CreatePaySlipService createPaySlipService, RequestUtil requestUtil){
        this.createPaySlipService = createPaySlipService;
        this.requestUtil = requestUtil;
    }
    @PostMapping
    public ResponseEntity<?> createNewPaySlip(HttpServletRequest httpServletRequest, 
    @RequestBody PaySlip paySlip){
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.createPaySlipService.createPaySlip(userID, paySlip));
    }
}
