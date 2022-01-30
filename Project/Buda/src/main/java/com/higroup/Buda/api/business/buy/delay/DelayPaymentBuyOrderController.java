package com.higroup.Buda.api.business.buy.delay;

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
@RequestMapping("api/business/buy/delay")
@CrossOrigin("*")
public class DelayPaymentBuyOrderController {
    private final RequestUtil requestUtil;
    private final DelayPaymentBuyOrderService delayPaymentBuyOrderService;
    @Autowired
    public DelayPaymentBuyOrderController(RequestUtil requestUtil, DelayPaymentBuyOrderService delayPaymentBuyOrderService){
        this.requestUtil = requestUtil;
        this.delayPaymentBuyOrderService = delayPaymentBuyOrderService;
    }
    @PutMapping("/id/{buyOrderID}")
    public ResponseEntity<?> delayPaymentBuyOrder(HttpServletRequest httpServletRequest, @PathVariable Long buyOrderID){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.delayPaymentBuyOrderService.delayPayment(userID, buyOrderID));
    }
}
