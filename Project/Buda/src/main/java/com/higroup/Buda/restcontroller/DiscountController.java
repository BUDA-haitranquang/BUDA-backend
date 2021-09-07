package com.higroup.Buda.restcontroller;

import java.util.List;

import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.services.DiscountService;

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
@RequestMapping("api/discount")
@CrossOrigin("*")
public class DiscountController {
    private final DiscountService discountService;
    @Autowired
    public DiscountController(DiscountService discountService)
    {
        this.discountService = discountService;
    }
    @PostMapping(path = "/userID/{userID}")
    public ResponseEntity<?> registerNewDiscount(@PathVariable Long userID, @RequestBody Discount discount)
    {
        return this.discountService.registerNewDiscount(userID, discount);
    }
    @GetMapping(path = "/discountID/{discountID}")
    public ResponseEntity<?> findDiscountByDiscountID(@PathVariable Long discountID)
    {
        return this.discountService.findDiscountByDiscountID(discountID);
    }
    @GetMapping(path = "/userID/{userID}/all")
    public List<Discount> findAllDiscountByUserID(@PathVariable Long userID)
    {
        return this.discountService.findAllDiscountByUserID(userID);
    }
}
