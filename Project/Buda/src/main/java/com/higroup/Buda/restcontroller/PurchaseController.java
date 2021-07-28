package com.higroup.Buda.restcontroller;

import java.util.List;

import com.higroup.Buda.entities.Purchase;
import com.higroup.Buda.services.PurchaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;
    @Autowired
    public PurchaseController(PurchaseService purchaseService)
    {
        this.purchaseService = purchaseService;
    }
    @GetMapping("/userID/{userID}")
    public List<Purchase> findPurchasesByUserID(@PathVariable("userID") Long userID)
    {
        return this.purchaseService.findAllByUserID(userID);
    }
}
