package com.higroup.Buda.api.supplier.create;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/supplier/create")
@CrossOrigin("*")
public class CreateSupplierController {
    private final CreateSupplierService createSupplierService;
    private final RequestUtil requestUtil;
    @Autowired
    public CreateSupplierController(CreateSupplierService createSupplierService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.createSupplierService = createSupplierService;
    }
    @PostMapping
    public ResponseEntity<?> createNewSupplier(HttpServletRequest httpServletRequest, @RequestBody Supplier supplier){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.createSupplierService.createNewSupplier(userID, supplier));
    }
}
