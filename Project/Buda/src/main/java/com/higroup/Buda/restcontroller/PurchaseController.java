package com.higroup.Buda.restcontroller;

import java.time.*;
import java.util.List;

import com.higroup.Buda.entities.Purchase;
import com.higroup.Buda.services.PurchaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/userID/{userID}")
    public ResponseEntity<?> createNewPurchase(@PathVariable Long userID, Purchase purchase)
    {
        return this.purchaseService.createNewPurchase(userID, purchase);
    }
    @GetMapping("/userID/{userID}/all")
    public List<Purchase> findPurchasesByUserID(@PathVariable("userID") Long userID)
    {
        return this.purchaseService.findAllByUserID(userID);
    }
    @GetMapping("/userID/{userID}/")
    public List<Purchase> findPurchasesByUserIDFromTo(
            @PathVariable("userID") Long userID,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to
    )
    {
        return this.purchaseService.findAllByUserIdFromTo(userID, from, to);
    }

}
