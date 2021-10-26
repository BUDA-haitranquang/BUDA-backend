package com.higroup.Buda.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.services.BuyOrderService;
import com.higroup.Buda.util.JwtTokenUtil;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/buy-order")
public class BuyOrderController {
    private final BuyOrderService buyOrderService;
    private final RequestUtil requestUtil;
    @Autowired
    public BuyOrderController(BuyOrderService buyOrderService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.buyOrderService = buyOrderService;
    }
    @PostMapping(path = "/new")
    public ResponseEntity<?> registerNewBuyOrder(HttpServletRequest httpServletRequest, @RequestBody BuyOrder buyOrder)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.buyOrderService.registerNewBuyOrder(userID, buyOrder));
    }
    @GetMapping(path = "user/all")
    public ResponseEntity<?> findAllBuyOrderByUserID(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.buyOrderService.findAllBuyOrderByUserID(userID));
    }
    @GetMapping(path = "supplier/{supplierID}/all")
    public ResponseEntity<?> findAllBuyOrderBySupplierID(HttpServletRequest httpServletRequest, @PathVariable Long supplierID)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.buyOrderService.findAllBuyOrderBySupplierID(userID, supplierID));
    }
}
