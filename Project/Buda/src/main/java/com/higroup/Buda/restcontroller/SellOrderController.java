package com.higroup.Buda.restcontroller;

import java.util.List;

import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.services.SellOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/sell-order")
public class SellOrderController {
    private final SellOrderService sellOrderService;
    @Autowired
    public SellOrderController(SellOrderService sellOrderService)
    {
        this.sellOrderService = sellOrderService;
    }
    @PostMapping(path = "new/userID/{userID}")
    public ResponseEntity<?> registerNewSellOrder(@PathVariable Long userID, @RequestBody SellOrder sellOrder)
    {
        return this.sellOrderService.registerNewSellOrder(userID, sellOrder);
    }
    @GetMapping(path = "user/{userID}/all")
    public List<SellOrder> findAllSellOrderByUserID(@PathVariable Long userID)
    {
        return this.sellOrderService.findAllSellOrderByUserID(userID);
    }
    @GetMapping(path = "customer/{customerID}/all")
    public List<SellOrder> findAllSellOrderByCustomerID(@PathVariable Long customerID)
    {
        return this.sellOrderService.findAllSellOrderByCustomerID(customerID);
    }
}
