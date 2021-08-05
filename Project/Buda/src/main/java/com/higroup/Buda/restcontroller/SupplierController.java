package com.higroup.Buda.restcontroller;

import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<Supplier> getSuppliers() {
        return supplierService.getSuppliers();
    }

    @GetMapping(path = "/id/{id}")
    public Supplier getSupplierByID(@PathVariable("id") Long id) {
        return supplierService.getSupplierByID(id);
    }
}
