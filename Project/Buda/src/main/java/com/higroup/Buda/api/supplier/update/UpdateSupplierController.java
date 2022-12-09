package com.higroup.Buda.api.supplier.update;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/supplier/update")
public class UpdateSupplierController {
    private final UpdateSupplierService updateSupplierService;
    private final RequestUtil requestUtil;
    @Autowired
    public UpdateSupplierController(UpdateSupplierService updateSupplierService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.updateSupplierService = updateSupplierService;
    }
    @PutMapping
    public ResponseEntity<?> updateSupplier(HttpServletRequest httpServletRequest, @RequestBody Supplier supplier) throws IllegalAccessException, InvocationTargetException
    {
        Long userID = requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.updateSupplierService.updateSupplier(userID, supplier));        
    }
}
