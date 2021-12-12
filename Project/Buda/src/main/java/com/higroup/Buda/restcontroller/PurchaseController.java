package com.higroup.Buda.restcontroller;

import java.time.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Purchase;
import com.higroup.Buda.services.PurchaseService;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final RequestUtil requestUtil;
    @Autowired
    public PurchaseController(PurchaseService purchaseService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.purchaseService = purchaseService;
    }
    @PostMapping("/new")
    public ResponseEntity<?> createNewPurchase(HttpServletRequest httpServletRequest, @RequestBody Purchase purchase)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.purchaseService.createNewPurchase(userID, purchase));
    }
    @GetMapping("/all")
    public ResponseEntity<?> findPurchasesByUserID(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.purchaseService.findAllByUserID(userID));
    }
}
