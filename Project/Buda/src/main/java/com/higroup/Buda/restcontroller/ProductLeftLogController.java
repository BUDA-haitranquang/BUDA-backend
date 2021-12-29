package com.higroup.Buda.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.ProductLeftLog;
import com.higroup.Buda.services.ProductLeftLogService;
import com.higroup.Buda.util.Checker.RequestUtil;

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
    private final RequestUtil requestUtil;
    @Autowired
    public ProductLeftLogController(ProductLeftLogService productLeftLogService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.productLeftLogService = productLeftLogService;
    }
    // @PostMapping(path = "/userID/{userID}")
    // public ResponseEntity<?> registerNewProductLeftLog(@PathVariable Long userID, @RequestBody ProductLeftLog productLeftLog)
    // {
    //     return this.productLeftLogService.registerNewProductLeftLog(userID, productLeftLog);
    // }
    @GetMapping(path = "/id/{productLeftLogID}")
    public ResponseEntity<?> findProductLeftLogByProductLeftLogID(HttpServletRequest httpServletRequest, @PathVariable Long productLeftLogID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productLeftLogService.findProductLeftLogByProductLeftLogID(userID, productLeftLogID));
    }
    @GetMapping(path = "product/{productID}/all")
    public ResponseEntity<?> findAllProductLeftLogByProduct(HttpServletRequest httpServletRequest, @PathVariable Long productID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productLeftLogService.findAllProductLeftLogByProduct(userID, productID));
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllProductLeftLogByUserID(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productLeftLogService.findAllProductLeftLogByUserID(userID));
    }
    @GetMapping(path = "staff/{staffID}/all")
    public ResponseEntity<?> findAllProductLeftLogByStaffID(HttpServletRequest httpServletRequest, @PathVariable Long staffID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productLeftLogService.findAllProductLeftLogByStaffID(userID, staffID));
    }
}
