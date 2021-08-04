package com.higroup.Buda.restcontroller;

import java.util.List;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
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
    private CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService)
    {
        this.customerService = customerService;
    }
    @GetMapping(path = "userID")
    public List<Customer> findAllByUserID(@RequestParam(required = true) Long userID)
    {
        return this.customerService.findAllByUserID(userID);
    }
    @PostMapping(path = "userID/{userID}")
    public ResponseEntity<?> registerNewCustomer(@PathVariable Long userID, @RequestBody Customer customer)
    {
        return this.customerService.registerNewCustomer(userID, customer);
    }
}
