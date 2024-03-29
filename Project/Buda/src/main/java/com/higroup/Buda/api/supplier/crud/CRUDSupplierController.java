package com.higroup.Buda.api.supplier.crud;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/supplier/crud")
public class CRUDSupplierController {
    private final RequestUtil requestUtil;
    private final CRUDSupplierService supplierService;
    @Autowired
    public CRUDSupplierController(CRUDSupplierService supplierService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.supplierService = supplierService;
    }
    
    @GetMapping(path = "/byphone")
    public ResponseEntity<?> findSupplierByCurrentUserWithPhoneNumber(HttpServletRequest httpServletRequest, @RequestBody String phoneNumber)
    {
        Long userID = requestUtil.getUserIDFromUserToken(httpServletRequest);
        // System.out.println(phoneNumber);
        return ResponseEntity.ok().body(this.supplierService.findSupplierByUserIDAndPhoneNumber(userID, phoneNumber));
    }
    // @PutMapping(path = "/update")
    // public ResponseEntity<?> updateSupplier(HttpServletRequest httpServletRequest, @RequestBody Supplier supplier)
    // {
    //     Long userID = requestUtil.getUserIDFromUserToken(httpServletRequest);
    //     return ResponseEntity.ok().body(this.supplierService.updateSupplier(userID, supplier));        
    // }
    @GetMapping(path = "/hide/{supplierID}")
    public ResponseEntity<?> hideSupplier(HttpServletRequest httpServletRequest, @PathVariable Long supplierID)
    {
        Long userID = requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.supplierService.hideSupplier(userID, supplierID));
    }
}
