package com.higroup.Buda.api.customer.update;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customer/update")
@CrossOrigin("*")
public class UpdateCustomerController {
    private final UpdateCustomerService updateCustomerService;
    private final RequestUtil requestUtil;
    @Autowired
    public UpdateCustomerController(UpdateCustomerService updateCustomerService, RequestUtil requestUtil){
        this.updateCustomerService = updateCustomerService;
        this.requestUtil = requestUtil;
    }
    @PutMapping
    public ResponseEntity<?> updateCustomer(HttpServletRequest httpServletRequest, @RequestBody Customer customer) throws IllegalAccessException, InvocationTargetException{
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.updateCustomerService.updateCustomer(userID, customer));
    }
}
