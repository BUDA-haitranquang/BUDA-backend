package com.higroup.Buda.restcontroller;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.services.WarrantyOrderService;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/warranty-order")
public class WarrantyOrderController {
    private final WarrantyOrderService warrantyOrderService;
    private final RequestUtil requestUtil;
    @Autowired
    public WarrantyOrderController(WarrantyOrderService warrantyOrderService, RequestUtil requestUtil)
    {
        this.warrantyOrderService = warrantyOrderService;
        this.requestUtil = requestUtil;
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllWarrantyOrderByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.warrantyOrderService.findAllWarrantyOrderByUserID(userID));
    }
    @GetMapping(path = "/product/{productID}/all")
    public ResponseEntity<?> findAllWarrantyOrderByProductID(HttpServletRequest httpServletRequest, @PathVariable Long productID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.warrantyOrderService.findAllWarrantyOrderByProductID(userID, productID));
    }
    @GetMapping(path = "/customer/{customerID}/all")
    public ResponseEntity<?> findAllWarrantyOrderByCustomerID(HttpServletRequest httpServletRequest, @PathVariable Long customerID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.warrantyOrderService.findAllWarrantyOrderByCustomerID(userID, customerID));
    }
}
