package com.higroup.Buda.api.payment.zalopay;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Purchase;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("")
public class ZaloPaymentController {
    
    @Autowired 
    private ZaloPaymentService zaloPaymentService;

    @Autowired 
    private RequestUtil requestUtil;

    // @PostMapping(path = "/api/payment/zalopay")
    // public ResponseEntity<?> createNewBuyOrder(HttpServletRequest httpServletRequest)
    // {
    //     Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
    //     return zaloPaymentService.createOrder(purchase);
    // }
    
}
