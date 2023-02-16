package com.higroup.Buda.api.payment.zalopay;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.higroup.Buda.util.Checker.RequestUtil;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/payment/zalopay")
public class CreateOrderController {
    
    @Autowired 
    private CreateOrderService createOrderService;

    @Autowired 
    private RequestUtil requestUtil;

    @GetMapping(path = "/create-order")
    public ResponseEntity<?> createNewBuyOrder(HttpServletRequest httpServletRequest) throws IOException
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(createOrderService.createOrder(null));
    }
    
}
