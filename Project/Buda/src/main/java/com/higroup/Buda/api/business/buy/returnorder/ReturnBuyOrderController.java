package com.higroup.Buda.api.business.buy.returnorder;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/business/buy/return-order")
@CrossOrigin("*")
public class ReturnBuyOrderController {
    private final ReturnBuyOrderService returnBuyOrderService;
    private final RequestUtil requestUtil;
    @Autowired
    public ReturnBuyOrderController(ReturnBuyOrderService returnOrderService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.returnBuyOrderService = returnOrderService;
    }
    @PutMapping(path = "/id/{buyOrderID}")
    public ResponseEntity<?> returnBuyOrderByID(HttpServletRequest httpServletRequest, @PathVariable Long buyOrderID){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.returnBuyOrderService.returnBuyOrderByBuyOrderID(userID, buyOrderID));
    }
}
