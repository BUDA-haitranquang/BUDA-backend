package com.higroup.Buda.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.BuyOrderItem;
import com.higroup.Buda.services.BuyOrderItemService;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/buy-order-item")
public class BuyOrderItemController {
    private final BuyOrderItemService buyOrderItemService;
    private final RequestUtil requestUtil;
    @Autowired
    public BuyOrderItemController(BuyOrderItemService buyOrderItemService, RequestUtil requestUtil)
    {
        this.buyOrderItemService = buyOrderItemService;
        this.requestUtil = requestUtil;
    }
    @GetMapping("/buy-order/{buyOrderID}")
    public ResponseEntity<?> findAllByBuyOrderID(HttpServletRequest httpServletRequest, @PathVariable Long buyOrderID)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.buyOrderItemService.findAllByBuyOrderID(userID, buyOrderID));
    }
    @GetMapping("all/ingredient/{ingredientID}")
    public ResponseEntity<?> findAllBuyOrderItemByIngredientID(HttpServletRequest httpServletRequest, @PathVariable Long ingredientID)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.buyOrderItemService.findAllBuyOrderItemByIngredientID(userID, ingredientID));
    }
    @DeleteMapping("{buyOrderItemID}")
    public ResponseEntity<?> deleteBuyOrderItemByID(HttpServletRequest httpServletRequest, @PathVariable Long buyOrderItemID)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        this.buyOrderItemService.deleteBuyOrderItem(userID, buyOrderItemID);
        return ResponseEntity.ok().body("Delete successfully");
    }
}
