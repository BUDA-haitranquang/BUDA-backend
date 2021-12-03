package com.higroup.Buda.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.services.DiscountService;
import com.higroup.Buda.util.JwtTokenUtil;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/discount")
@CrossOrigin("*")
public class DiscountController {
    private final DiscountService discountService;
    private final RequestUtil requestUtil;
    @Autowired
    public DiscountController(DiscountService discountService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.discountService = discountService;
    }
    @PostMapping(path = "/new")
    public ResponseEntity<?> registerNewDiscount(HttpServletRequest httpServletRequest, @RequestBody Discount discount)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.discountService.createNewDiscount(userID, discount));
    }
    @GetMapping(path = "/discountID/{discountID}")
    public ResponseEntity<?> findDiscountByDiscountID(HttpServletRequest httpServletRequest, @PathVariable Long discountID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        Discount discount = this.discountService.findDiscountByDiscountID(discountID);
        if (discount.getUserID()!=userID)
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        return ResponseEntity.ok().body(discount);
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllDiscountByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.discountService.findAllDiscountByUserID(userID));
    }
}
