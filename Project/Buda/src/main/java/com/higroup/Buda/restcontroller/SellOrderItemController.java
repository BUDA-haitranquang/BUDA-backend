package com.higroup.Buda.restcontroller;

import java.util.List;

import com.higroup.Buda.entities.SellOrderItem;
import com.higroup.Buda.services.SellOrderItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/sell-order-item")
public class SellOrderItemController {
    private final SellOrderItemService sellOrderItemService;
    @Autowired
    public SellOrderItemController(SellOrderItemService sellOrderItemService)
    {
        this.sellOrderItemService = sellOrderItemService;
    }
    @GetMapping("/sell-order-id/{sellOrderID}")
    public List<SellOrderItem> findAllBySellOrderID(@PathVariable Long sellOrderID)
    {
        return this.sellOrderItemService.findAllBySellOrderID(sellOrderID);
    }
}
