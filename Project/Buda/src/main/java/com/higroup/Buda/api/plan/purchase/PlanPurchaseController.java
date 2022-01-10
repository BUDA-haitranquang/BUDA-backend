package com.higroup.Buda.api.plan.purchase;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Purchase;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/plan/purchase")
public class PlanPurchaseController {
    private final PlanPurchaseService planPurchaseService;
    private final RequestUtil requestUtil;
    @Autowired
    public PlanPurchaseController(PlanPurchaseService purchaseService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.planPurchaseService = purchaseService;
    }
    @PostMapping("/new")
    public ResponseEntity<?> createNewPurchase(HttpServletRequest httpServletRequest, @RequestBody Purchase purchase)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.planPurchaseService.createNewPurchase(userID, purchase));
    }
    @GetMapping("/all")
    public ResponseEntity<?> findPurchasesByUserID(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.planPurchaseService.findAllByUserID(userID));
    }
}
