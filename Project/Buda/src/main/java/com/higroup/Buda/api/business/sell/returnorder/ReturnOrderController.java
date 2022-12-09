package com.higroup.Buda.api.business.sell.returnorder;

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
@RequestMapping("api/business/sell/return-order")
@CrossOrigin("*")
public class ReturnOrderController {
    private final ReturnOrderService returnOrderService;
    private final RequestUtil requestUtil;
    @Autowired
    public ReturnOrderController(ReturnOrderService returnOrderService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.returnOrderService = returnOrderService;
    }
    @PutMapping(path = "id/{sellOrderID}")
    public ResponseEntity<?> returnSellOrderBySellOrderID(@PathVariable Long sellOrderID, HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.returnOrderService.returnSellOrderBySellOrderID(userID, sellOrderID));
    }
}
