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
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllByCurrentUser(HttpServletRequest request)
    {   
        final String token = request.getHeader("Authorization").substring(7);

        Long userID = jwtTokenUtil.getUserIDFromToken(token);

        if((userID != null) && (jwtTokenUtil.isValid(token))){
            return this.customerService.findAllByUserID(userID);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
        } 
    }

    @PostMapping(path = "/new")
    public ResponseEntity<?> registerNewCustomer(HttpServletRequest request, @RequestBody Customer customer)
    {
        final String token = request.getHeader("Authorization").substring(7);

        Long userID = jwtTokenUtil.getUserIDFromToken(token);

        if((userID != null) && (jwtTokenUtil.isValid(token))){
            return this.customerService.registerNewCustomer(userID, customer);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
        }
    }
    
    @GetMapping(path = "/byphone")
    public ResponseEntity<?> findCustomerByCurrentUserWithPhoneNumber(HttpServletRequest request, @RequestBody String phoneNumber)
    {
        final String token = request.getHeader("Authorization").substring(7);

        Long userID = jwtTokenUtil.getUserIDFromToken(token);

        if((userID != null) && (jwtTokenUtil.isValid(token))){
            return this.customerService.findCustomerByUserIDAndPhoneNumber(userID, phoneNumber);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
        }
    }
}
