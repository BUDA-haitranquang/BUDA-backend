package com.higroup.Buda.restcontroller;

import java.util.List;

import com.higroup.Buda.entities.BuyOrderItem;
import com.higroup.Buda.services.BuyOrderItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/buy-order-item")
public class BuyOrderItemController {
    private final BuyOrderItemService buyOrderItemService;
    @Autowired
    public BuyOrderItemController(BuyOrderItemService buyOrderItemService)
    {
        this.buyOrderItemService = buyOrderItemService;
    }
    @GetMapping("/buy-order-id/{buyOrderID}")
    public List<BuyOrderItem> findAllByBuyOrderID(@PathVariable Long buyOrderID)
    {
        return this.buyOrderItemService.findAllByBuyOrderID(buyOrderID);
    }
}
