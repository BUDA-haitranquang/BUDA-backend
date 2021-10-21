package com.higroup.Buda.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.services.BuyOrderService;
import com.higroup.Buda.util.JwtTokenUtil;

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
    private final JwtTokenUtil jwtTokenUtil;
    @Autowired
    public BuyOrderController(BuyOrderService buyOrderService, JwtTokenUtil jwtTokenUtil)
    {
        this.jwtTokenUtil = jwtTokenUtil;
        this.buyOrderService = buyOrderService;
    }
    @PostMapping(path = "/new")
    public ResponseEntity<?> registerNewBuyOrder(HttpServletRequest httpServletRequest, @RequestBody BuyOrder buyOrder)
    {
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        Long userID = this.jwtTokenUtil.getUserIDFromToken(token);
        if ((userID!=null) && (jwtTokenUtil.isValid(token)))
        {
            return this.buyOrderService.registerNewBuyOrder(userID, buyOrder);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }
    @GetMapping(path = "user/all")
    public ResponseEntity<?> findAllBuyOrderByUserID(HttpServletRequest httpServletRequest)
    {
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        Long userID = this.jwtTokenUtil.getUserIDFromToken(token);
        if ((userID!=null) && (jwtTokenUtil.isValid(token)))
        {
            return this.buyOrderService.findAllBuyOrderByUserID(userID);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }
    @GetMapping(path = "supplier/{supplierID}/all")
    public ResponseEntity<?> findAllBuyOrderBySupplierID(HttpServletRequest httpServletRequest, @PathVariable Long supplierID)
    {
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        Long userID = this.jwtTokenUtil.getUserIDFromToken(token);
        if ((userID!=null) && (jwtTokenUtil.isValid(token)))
        {
            return this.buyOrderService.findAllBuyOrderBySupplierID(userID, supplierID);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }
}
