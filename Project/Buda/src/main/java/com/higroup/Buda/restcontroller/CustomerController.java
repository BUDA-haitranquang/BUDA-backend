package com.higroup.Buda.restcontroller;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.services.CustomerService;
import com.higroup.Buda.util.JwtTokenUtil;

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

    private final JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService)
    {
        this.customerService = customerService;
    }
    @GetMapping(path = "user/{userID}")
    public ResponseEntity<?> findAllByUserID(HttpServletRequest request, @PathVariable Long userID)
    {   
        final String token = request.getHeader("Authorization").substring(7);

        Long get_userID = jwtTokenUtil.getUserIDFromToken(token);

        if(get_userID == userID){
            return ResponseEntity.ok(this.customerService.findAllByUserID(userID));
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
        } 
    }

    @PostMapping(path = "user/{userID}")
    public ResponseEntity<?> registerNewCustomer(HttpServletRequest request, @PathVariable Long userID, @RequestBody Customer customer)
    {
        final String token = request.getHeader("Authorization").substring(7);

        Long get_userID = jwtTokenUtil.getUserIDFromToken(token);

        if(get_userID == userID){
            return ResponseEntity.ok(this.customerService.registerNewCustomer(userID, customer));
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
        }
    }
    
    @GetMapping(path = "user/{userID}/byphone")
    public ResponseEntity<?> findCustomerByUserIDAndPhoneNumber(HttpServletRequest request, @PathVariable Long userID, @RequestParam(required = true) String phoneNumber)
    {
        final String token = request.getHeader("Authorization").substring(7);

        Long get_userID = jwtTokenUtil.getUserIDFromToken(token);

        if(get_userID == userID){
            return ResponseEntity.ok(this.customerService.findCustomerByUserIDAndPhoneNumber(userID, phoneNumber));
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
        }
    }
}
