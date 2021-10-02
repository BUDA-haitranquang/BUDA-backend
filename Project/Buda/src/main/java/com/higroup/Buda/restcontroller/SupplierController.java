package com.higroup.Buda.restcontroller;

import java.util.List;

import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.services.SupplierService;

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
@RequestMapping("api/supplier")
public class SupplierController {
    private final SupplierService supplierService;
    @Autowired
    public SupplierController(SupplierService supplierService)
    {
        this.supplierService = supplierService;
    }
    @PostMapping(path = "/userID/{userID}")
    public ResponseEntity<?> registerNewSupplier(@PathVariable Long userID, @RequestBody Supplier supplier)
    {
        return this.supplierService.registerNewSupplier(userID, supplier);
    }
    @GetMapping(path = "/userID/{userID}/all")
    public List<Supplier> findAllByUserID(@PathVariable Long userID)
    {
        return this.supplierService.findAllByUserID(userID);
    }
    @GetMapping(path = "/userID/{userID}/byphone")
    public ResponseEntity<?> findSupplierByUserIDAndPhoneNumber(@PathVariable Long userID, @RequestParam(required = true) String phoneNumber)
    {
        return this.supplierService.findSupplierByUserIDAndPhoneNumber(userID, phoneNumber);
    }
}
