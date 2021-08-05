package com.higroup.Buda.restcontroller;

import java.util.List;

import com.higroup.Buda.entities.ProductLeftLog;
import com.higroup.Buda.services.ProductLeftLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product-left-log")
@CrossOrigin("*")
public class ProductLeftLogController {
    private final ProductLeftLogService productLeftLogService;
    @Autowired
    public ProductLeftLogController(ProductLeftLogService productLeftLogService)
    {
        this.productLeftLogService = productLeftLogService;
    }
    @PostMapping(path = "/userID/{userID}")
    public ResponseEntity<?> registerNewProductLeftLog(@PathVariable Long userID, @RequestBody ProductLeftLog productLeftLog)
    {
        return this.productLeftLogService.registerNewProductLeftLog(userID, productLeftLog);
    }
    @GetMapping(path = "/id/{productLeftLogID}")
    public ResponseEntity<?> findProductLeftLogByProductLeftLogID(@PathVariable Long productLeftLogID)
    {
        return this.productLeftLogService.findProductLeftLogByProductLeftLogID(productLeftLogID);
    }
    @GetMapping(path = "product/{productID}/all")
    public List<ProductLeftLog> findAllProductLeftLogByProduct(@PathVariable Long productID)
    {
        return this.productLeftLogService.findAllProductLeftLogByProduct(productID);
    }
    @GetMapping(path = "user/{userID}/all")
    public List<ProductLeftLog> findAllProductLeftLogByUserID(@PathVariable Long userID)
    {
        return this.productLeftLogService.findAllProductLeftLogByUserID(userID);
    }
    @GetMapping(path = "staff/{staffID}/all")
    public List<ProductLeftLog> findAllProductLeftLogByStaffID(@PathVariable Long staffID)
    {
        return this.findAllProductLeftLogByStaffID(staffID);
    }
}
