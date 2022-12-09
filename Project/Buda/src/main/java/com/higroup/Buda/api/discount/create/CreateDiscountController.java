package com.higroup.Buda.api.discount.create;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/discount/create")
@CrossOrigin("*")
public class CreateDiscountController {
    private final CreateDiscountService createDiscountService;
    private final RequestUtil requestUtil;
    @Autowired
    public CreateDiscountController(CreateDiscountService createDiscountService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.createDiscountService = createDiscountService;
    }
    @PostMapping
    public ResponseEntity<?> createDiscount(HttpServletRequest httpServletRequest, @RequestBody Discount discount){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.createDiscountService.createDiscount(userID, discount));
    }
}
