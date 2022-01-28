package com.higroup.Buda.api.business.sell.finish;

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
@RequestMapping("api/business/sell/finish")
@CrossOrigin("*")
public class FinishSellOrderController {
    private final FinishSellOrderService finishSellOrderService;
    private final RequestUtil requestUtil;
    @Autowired
    public FinishSellOrderController(FinishSellOrderService finishSellOrderService, RequestUtil requestUtil){
        this.finishSellOrderService = finishSellOrderService;
        this.requestUtil = requestUtil;
    }
    @PutMapping(path = "id/{sellOrderID}")
    public ResponseEntity<?> finishSellOrder(HttpServletRequest httpServletRequest, @PathVariable Long sellOrderID){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.finishSellOrderService.finishSellOrder(userID, sellOrderID));
    }
}
