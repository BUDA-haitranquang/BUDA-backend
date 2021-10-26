package com.higroup.Buda.restcontroller;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.services.CustomerService;
import com.higroup.Buda.util.JwtTokenUtil;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/customer")
public class CustomerController {

    private final RequestUtil requestUtil;
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.customerService = customerService;
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllByCurrentUser(HttpServletRequest httpServletRequest)
    {   
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.customerService.findAllByUserID(userID));
    }

    @PostMapping(path = "/new")
    public ResponseEntity<?> registerNewCustomer(HttpServletRequest httpServletRequest, @RequestBody Customer customer)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.customerService.registerNewCustomer(userID, customer));   
    }
    
    @GetMapping(path = "/byphone")
    public ResponseEntity<?> findCustomerByCurrentUserWithPhoneNumber(HttpServletRequest httpServletRequest, @RequestBody String phoneNumber)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return this.customerService.findCustomerByUserIDAndPhoneNumber(userID, phoneNumber);
    }
}
