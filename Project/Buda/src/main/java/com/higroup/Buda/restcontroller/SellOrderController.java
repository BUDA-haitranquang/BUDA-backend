package com.higroup.Buda.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.customDTO.RegisterSellOrder;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.services.SellOrderService;
import com.higroup.Buda.util.JwtTokenUtil;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/sell-order")
public class SellOrderController {
    private final SellOrderService sellOrderService;
    private final RequestUtil requestUtil;
    @Autowired
    public SellOrderController(SellOrderService sellOrderService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.sellOrderService = sellOrderService;
    }
    @PostMapping(path = "/new")
    public ResponseEntity<?> registerNewSellOrder(HttpServletRequest httpServletRequest, @RequestBody RegisterSellOrder registerSellOrder)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.sellOrderService.registerSellOrder(userID, registerSellOrder));
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllSellOrderByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.sellOrderService.findAllSellOrderByUserID(userID));
    }
    @GetMapping(path = "customer/{customerID}/all")
    public ResponseEntity<?> findAllSellOrderByCustomerID(HttpServletRequest httpServletRequest, @PathVariable Long customerID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.sellOrderService.findAllSellOrderByCustomerID(userID, customerID));
    }
    @DeleteMapping(path = "/{sellOrderID}")
    public ResponseEntity<?> deleteSellOrderBySellOrderID(HttpServletRequest httpServletRequest, @PathVariable Long sellOrderID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.sellOrderService.deleteSellOrderBySellOrderID(userID, sellOrderID);
        return ResponseEntity.ok().body("Delete succesfully");
    }
    @PutMapping(path = "/update")
    public ResponseEntity<?> updateSellOrder(HttpServletRequest httpServletRequest, @RequestBody SellOrder sellOrder)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.sellOrderService.updateSellOrder(userID, sellOrder));
    }
    @GetMapping(path = "all/last-x-days/{X}")
    public ResponseEntity<?> findAllLastXDaysSellOrderByCurrentUser(HttpServletRequest httpServletRequest, @PathVariable Long X)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.sellOrderService.findAllSellOrderByUserIDLastXDays(userID, X));
    }
    @GetMapping(path = "all/incompleted")
    public ResponseEntity<?> findAllIncompletedSellOrderByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.sellOrderService.findAllIIncompletedSellOrderByUserID(userID));
    }
    @GetMapping(path = "all/status/{status}")
    public ResponseEntity<?> findAllSellOrderByCurrentUserAndStatus(HttpServletRequest httpServletRequest, @PathVariable Status status)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.sellOrderService.findAllSellOrderByUserAndStatus(userID, status));
    }
}
