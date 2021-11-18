package com.higroup.Buda.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.node.TextNode;
import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.services.SupplierService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/supplier")
public class SupplierController {
    private final RequestUtil requestUtil;
    private final SupplierService supplierService;
    @Autowired
    public SupplierController(SupplierService supplierService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.supplierService = supplierService;
    }
    @PostMapping(path = "new")
    public ResponseEntity<?> registerNewSupplier(HttpServletRequest httpServletRequest,  @RequestBody Supplier supplier)
    { 
        Long userID = requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.supplierService.registerNewSupplier(userID, supplier));
        
    }

    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllByUserID(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.supplierService.findAllByUserID(userID));
    }
    @GetMapping(path = "/byphone")
    public ResponseEntity<?> findSupplierByCurrentUserWithPhoneNumber(HttpServletRequest httpServletRequest, @RequestBody String phoneNumber)
    {
        Long userID = requestUtil.getUserID(httpServletRequest);
        // System.out.println(phoneNumber);
        return ResponseEntity.ok().body(this.supplierService.findSupplierByUserIDAndPhoneNumber(userID, phoneNumber));
    }
    @PutMapping(path = "/update")
    public ResponseEntity<?> updateSupplier(HttpServletRequest httpServletRequest, @RequestBody Supplier supplier)
    {
        Long userID = requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.supplierService.updateSupplier(userID, supplier));        
    }
}
