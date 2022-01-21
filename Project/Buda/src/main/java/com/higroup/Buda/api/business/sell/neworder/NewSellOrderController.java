package com.higroup.Buda.api.business.sell.neworder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/business/sell/new-order")
public class NewSellOrderController {
    private final NewSellOrderService newsellOrderService;
    private final RequestUtil requestUtil;
    @Autowired
    public NewSellOrderController(NewSellOrderService newsellOrderService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.newsellOrderService = newsellOrderService;
    }
    @PostMapping(path = "/new")
    public ResponseEntity<?> registerNewSellOrder(HttpServletRequest httpServletRequest, @RequestBody SellOrderDTO sellOrderDTO)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.newsellOrderService.registerSellOrder(userID, sellOrderDTO));
    }
    
    @DeleteMapping(path = "/{sellOrderID}")
    public ResponseEntity<?> deleteSellOrderBySellOrderID(HttpServletRequest httpServletRequest, @PathVariable Long sellOrderID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.newsellOrderService.deleteSellOrderBySellOrderID(userID, sellOrderID);
        return ResponseEntity.ok().body("Delete succesfully");
    }
    @PutMapping(path = "/update")
    public ResponseEntity<?> updateSellOrder(HttpServletRequest httpServletRequest, @RequestBody SellOrder sellOrder)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.newsellOrderService.updateSellOrder(userID, sellOrder));
    }
}
