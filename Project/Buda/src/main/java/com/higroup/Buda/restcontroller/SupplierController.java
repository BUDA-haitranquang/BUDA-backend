package com.higroup.Buda.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.services.SupplierService;
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
@RequestMapping("api/supplier")
public class SupplierController {
    private final JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    private final SupplierService supplierService;
    @Autowired
    public SupplierController(SupplierService supplierService)
    {
        this.supplierService = supplierService;
    }
    @PostMapping(path = "/userID/{userID}")
    public ResponseEntity<?> registerNewSupplier(HttpServletRequest request, @PathVariable Long userID, @RequestBody Supplier supplier)
    {   
        final String token = request.getHeader("Authorization").substring(7);

        Long get_userID = jwtTokenUtil.getUserIDFromToken(token);

        if(get_userID == userID){
            return ResponseEntity.ok(this.supplierService.registerNewSupplier(userID, supplier));
        }
        else{
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(path = "/userID/{userID}/all")
    public ResponseEntity<?> findAllByUserID(HttpServletRequest request, @PathVariable Long userID)
    {
        final String token = request.getHeader("Authorization").substring(7);

        Long get_userID = jwtTokenUtil.getUserIDFromToken(token);

        if(get_userID == userID){
            return ResponseEntity.ok(this.supplierService.findAllByUserID(userID));
        }
        else{
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping(path = "/userID/{userID}/byphone")
    public ResponseEntity<?> findSupplierByUserIDAndPhoneNumber(HttpServletRequest request, @PathVariable Long userID, @RequestParam(required = true) String phoneNumber)
    {
        final String token = request.getHeader("Authorization").substring(7);

        Long get_userID = jwtTokenUtil.getUserIDFromToken(token);

        if(get_userID == userID){
            return ResponseEntity.ok(this.supplierService.findSupplierByUserIDAndPhoneNumber(userID, phoneNumber));
        }
        else{
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }
}
