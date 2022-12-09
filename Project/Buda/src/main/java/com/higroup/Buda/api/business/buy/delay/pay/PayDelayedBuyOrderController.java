package com.higroup.Buda.api.business.buy.delay.pay;

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
@RequestMapping("api/business/buy/delay/pay")
@CrossOrigin("*")
public class PayDelayedBuyOrderController {
    private final RequestUtil requestUtil;
    private final PayDelayedBuyOrderService payDelayedBuyOrderService;
    @Autowired
    public PayDelayedBuyOrderController(RequestUtil requestUtil, PayDelayedBuyOrderService payDelayedBuyOrderService)
    {
        this.requestUtil = requestUtil;
        this.payDelayedBuyOrderService = payDelayedBuyOrderService;
    }
    @PutMapping("id/{buyOrderID}")
    public ResponseEntity<?> payDelayedBuyOrder(HttpServletRequest httpServletRequest, @PathVariable Long buyOrderID){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.payDelayedBuyOrderService.payDelayedBuyOrder(userID, buyOrderID));
    }
}
