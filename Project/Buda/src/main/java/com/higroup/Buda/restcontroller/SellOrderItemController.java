package com.higroup.Buda.restcontroller;

import com.higroup.Buda.services.SellOrderItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/sell-order-item")
public class SellOrderItemController {
    private SellOrderItemService sellOrderItemService;
    @Autowired
    public SellOrderItemController(SellOrderItemService sellOrderItemService)
    {
        this.sellOrderItemService = sellOrderItemService;
    }
}
