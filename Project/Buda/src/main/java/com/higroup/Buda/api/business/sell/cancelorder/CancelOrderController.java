package com.higroup.Buda.api.business.sell.cancelorder;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/business/sell/cancel-order")
@CrossOrigin("*")
public class CancelOrderController {
    private final CancelOrderService cancelOrderService;
    private final RequestUtil requestUtil;
    public CancelOrderController(RequestUtil requestUtil, CancelOrderService cancelOrderService){
        this.cancelOrderService = cancelOrderService;
        this.requestUtil = requestUtil;
    }
    @PutMapping("id/{sellOrderID}")
    public ResponseEntity<?> cancelSellOrder(HttpServletRequest httpServletRequest, @PathVariable Long sellOrderID){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.cancelOrderService.cancelSellOrder(userID, sellOrderID));
    }
}
