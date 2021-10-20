package com.higroup.Buda.restcontroller;

import java.util.List;

import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.services.BuyOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/Buy-order")
public class BuyOrderController {
    private final BuyOrderService buyOrderService;
    @Autowired
    public BuyOrderController(BuyOrderService buyOrderService)
    {
        this.buyOrderService = buyOrderService;
    }
    @PostMapping(path = "new/userID/{userID}")
    public ResponseEntity<?> registerNewBuyOrder(@PathVariable Long userID, @RequestBody BuyOrder buyOrder)
    {
        return this.buyOrderService.registerNewBuyOrder(userID, buyOrder);
    }
    @GetMapping(path = "user/{userID}/all")
    public List<BuyOrder> findAllBuyOrderByUserID(@PathVariable Long userID)
    {
        return this.buyOrderService.findAllBuyOrderByUserID(userID);
    }
    @GetMapping(path = "supplier/{supplierID}/all")
    public List<BuyOrder> findAllBuyOrderBySupplierID(@PathVariable Long supplierID)
    {
        return this.buyOrderService.findAllBuyOrderBySupplierID(supplierID);
    }
}
