package com.higroup.Buda.api.customer.crud;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/customer/crud")
public class CRUDCustomerController {

    private final RequestUtil requestUtil;
    private final CRUDCustomerService customerService;
    @Autowired
    public CRUDCustomerController(CRUDCustomerService customerService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.customerService = customerService;
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllByCurrentUser(HttpServletRequest httpServletRequest)
    {   
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.customerService.findAllByUserID(userID));
    }

    @PostMapping(path = "/new")
    public ResponseEntity<?> registerNewCustomer(HttpServletRequest httpServletRequest, @RequestBody Customer customer)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.customerService.registerNewCustomer(userID, customer));   
    }
    
    @PutMapping(path = "/update")
    public ResponseEntity<?> updateCustomer(HttpServletRequest httpServletRequest, @RequestBody Customer customer)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.customerService.updateCustomer(userID, customer));
    }
    
    @GetMapping(path = "/hide/{customerID}")
    public ResponseEntity<?> hideProduct(HttpServletRequest httpServletRequest, @PathVariable Long customerID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.customerService.hideCustomer(userID, customerID));
    }
}
