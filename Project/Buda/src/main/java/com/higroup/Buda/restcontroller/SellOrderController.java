package com.higroup.Buda.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.services.SellOrderService;
import com.higroup.Buda.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/sell-order")
public class SellOrderController {
    private final SellOrderService sellOrderService;
    private final JwtTokenUtil jwtTokenUtil;
    @Autowired
    public SellOrderController(SellOrderService sellOrderService, JwtTokenUtil jwtTokenUtil)
    {
        this.jwtTokenUtil = jwtTokenUtil;
        this.sellOrderService = sellOrderService;
    }
    @PostMapping(path = "/new")
    public ResponseEntity<?> registerNewSellOrder(HttpServletRequest httpServletRequest, @RequestBody SellOrder sellOrder)
    {
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        Long userID = this.jwtTokenUtil.getUserIDFromToken(token);
        if (userID!=null && jwtTokenUtil.isValid(token))
        {
            return this.sellOrderService.registerNewSellOrder(userID, sellOrder);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllSellOrderByUserID(HttpServletRequest httpServletRequest)
    {
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        Long userID = this.jwtTokenUtil.getUserIDFromToken(token);
        if (userID!=null && jwtTokenUtil.isValid(token))
        {
            return this.sellOrderService.findAllSellOrderByUserID(userID);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }
    @GetMapping(path = "customer/{customerID}/all")
    public ResponseEntity<?> findAllSellOrderByCustomerID(HttpServletRequest httpServletRequest, @PathVariable Long customerID)
    {
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        Long userID = this.jwtTokenUtil.getUserIDFromToken(token);
        if (userID!=null && jwtTokenUtil.isValid(token))
        {
            return this.sellOrderService.findAllSellOrderByCustomerID(userID, customerID);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }
}
