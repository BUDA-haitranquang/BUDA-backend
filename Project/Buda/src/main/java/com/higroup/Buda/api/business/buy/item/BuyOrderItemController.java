package com.higroup.Buda.api.business.buy.item;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

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
@RequestMapping("api/business/buy/item")
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
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.buyOrderItemService.findAllByBuyOrderID(userID, buyOrderID));
    }
    @GetMapping("all/ingredient/{ingredientID}")
    public ResponseEntity<?> findAllBuyOrderItemByIngredientID(HttpServletRequest httpServletRequest, @PathVariable Long ingredientID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.buyOrderItemService.findAllBuyOrderItemByIngredientID(userID, ingredientID));
    }
    @DeleteMapping("{buyOrderItemID}")
    public ResponseEntity<?> deleteBuyOrderItemByID(HttpServletRequest httpServletRequest, @PathVariable Long buyOrderItemID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.buyOrderItemService.deleteBuyOrderItem(userID, buyOrderItemID);
        return ResponseEntity.ok().body("Delete successfully");
    }
}
