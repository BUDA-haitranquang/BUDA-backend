package com.higroup.Buda.api.business.sell.neworder.pos;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.higroup.Buda.api.business.sell.neworder.SellOrderDTO;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/business/sell/new-order/pos")
@CrossOrigin("*")
public class PosNewSellOrderController {
    private final PosNewSellOrderService posNewSellOrderService;
    private final RequestUtil requestUtil;
    @Autowired
    public PosNewSellOrderController(RequestUtil requestUtil, PosNewSellOrderService posNewSellOrderService){
        this.requestUtil = requestUtil;
        this.posNewSellOrderService = posNewSellOrderService;
    }
    @PostMapping(path = "/new")
    public ResponseEntity<?> newPosSellOrder(HttpServletRequest httpServletRequest, @RequestBody @Valid SellOrderDTO sellOrderDTO){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.posNewSellOrderService.newPosSellOrder(userID, sellOrderDTO));
    }
}
