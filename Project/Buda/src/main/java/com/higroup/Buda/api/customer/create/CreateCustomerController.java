package com.higroup.Buda.api.customer.create;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customer/create")
@CrossOrigin("*")
public class CreateCustomerController {
    private final CreateCustomerService createCustomerService;
    private final RequestUtil requestUtil;
    @Autowired
    public CreateCustomerController(CreateCustomerService createCustomerService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.createCustomerService = createCustomerService;
    }
    @PostMapping
    public ResponseEntity<?> createCustomer(HttpServletRequest httpServletRequest, @RequestBody Customer customer){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.createCustomerService.createNewCustomer(userID, customer));
    }
}
