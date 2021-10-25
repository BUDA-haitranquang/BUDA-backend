package com.higroup.Buda.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.node.TextNode;
import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.services.SupplierService;
import com.higroup.Buda.util.JwtTokenUtil;

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
@RequestMapping("api/supplier")
public class SupplierController {
    private final JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    private final SupplierService supplierService;
    @Autowired
    public SupplierController(SupplierService supplierService)
    {
        this.supplierService = supplierService;
    }
    @PostMapping(path = "new")
    public ResponseEntity<?> registerNewSupplier(HttpServletRequest request,  @RequestBody Supplier supplier)
    {   
        final String token = request.getHeader("Authorization").substring(7);

        Long userID = jwtTokenUtil.getUserIDFromToken(token);

        if((userID != null) && (jwtTokenUtil.isValid(token))){
            return this.supplierService.registerNewSupplier(userID, supplier);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
        }
    }

    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllByUserID(HttpServletRequest request)
    {
        final String token = request.getHeader("Authorization").substring(7);

        Long userID = jwtTokenUtil.getUserIDFromToken(token);

        if((userID != null) && (jwtTokenUtil.isValid(token))){
            return this.supplierService.findAllByUserID(userID);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }
    @GetMapping(path = "/byphone")
    public ResponseEntity<?> findSupplierByCurrentUserWithPhoneNumber(HttpServletRequest request, @RequestBody String phoneNumber)
    {
        final String token = request.getHeader("Authorization").substring(7);

        Long userID = jwtTokenUtil.getUserIDFromToken(token);
        System.out.println(phoneNumber);
        if((userID != null ) && (jwtTokenUtil.isValid(token))){
            return this.supplierService.findSupplierByUserIDAndPhoneNumber(userID, phoneNumber);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
        }
    }
}
