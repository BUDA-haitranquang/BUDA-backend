package com.higroup.Buda.restcontroller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.customDTO.RegisterSellOrderItem;
import com.higroup.Buda.entities.SellOrderItem;
import com.higroup.Buda.services.SellOrderItemService;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/sell-order-item")
public class SellOrderItemController {
    private final SellOrderItemService sellOrderItemService;
    private final RequestUtil requestUtil;
    @Autowired
    public SellOrderItemController(SellOrderItemService sellOrderItemService, RequestUtil requestUtil)
    {
        this.sellOrderItemService = sellOrderItemService;
        this.requestUtil = requestUtil;
    }
    @GetMapping("/sell-order-id/{sellOrderID}")
    public ResponseEntity<?> findAllBySellOrderID(HttpServletRequest httpServletRequest, @PathVariable Long sellOrderID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.sellOrderItemService.findAllBySellOrderID(userID, sellOrderID));
    }
    @DeleteMapping("{sellOrderItemID}")
    public ResponseEntity<?> deleteSellOrderItemBySellOrderItemID(HttpServletRequest httpServletRequest, @PathVariable Long sellOrderItemID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.sellOrderItemService.deleteSellOrderItem(userID, sellOrderItemID);
        return ResponseEntity.ok().body("Delete successfully");
    }
    @PutMapping("update/{sellOrderItemID}")
    public ResponseEntity<?> updateSellOrderItem(HttpServletRequest httpServletRequest, 
    @RequestBody SellOrderItem sellOrderItem, @PathVariable Long sellOrderItemID) throws IllegalAccessException, InvocationTargetException{
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.sellOrderItemService.updateSellOrderItem(userID, sellOrderItemID, sellOrderItem));
    }
    @GetMapping("/product/{productID}")
    public ResponseEntity<?> findAllSellOrderItemByProductID(HttpServletRequest httpServletRequest, @PathVariable Long productID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.sellOrderItemService.findAllSellOrderItemByProductID(userID, productID));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerSellOrderItem(HttpServletRequest httpServletRequest, @RequestBody RegisterSellOrderItem registerSellOrderItem){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.sellOrderItemService.registerNewSellOrderItem(userID, registerSellOrderItem));
    }
}
