package com.higroup.Buda.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.services.DiscountService;
import com.higroup.Buda.util.JwtTokenUtil;

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

@RestController
@RequestMapping("api/discount")
@CrossOrigin("*")
public class DiscountController {
    private final DiscountService discountService;
    private final JwtTokenUtil jwtTokenUtil;
    @Autowired
    public DiscountController(DiscountService discountService, JwtTokenUtil jwtTokenUtil)
    {
        this.jwtTokenUtil = jwtTokenUtil;
        this.discountService = discountService;
    }
    @PostMapping(path = "/new")
    public ResponseEntity<?> registerNewDiscount(HttpServletRequest httpServletRequest, @RequestBody Discount discount)
    {
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        Long userID = jwtTokenUtil.getUserIDFromToken(token);
        if ((userID!=null) && (jwtTokenUtil.isValid(token)))
        {
            return this.discountService.registerNewDiscount(userID, discount);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }
    @GetMapping(path = "/discountID/{discountID}")
    public ResponseEntity<?> findDiscountByDiscountID(HttpServletRequest httpServletRequest, @PathVariable Long discountID)
    {
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        Long userID = jwtTokenUtil.getUserIDFromToken(token);
        Discount discount = (Discount)this.discountService.findDiscountByDiscountID(discountID).getBody();
        if (userID == discount.getUserID() && (jwtTokenUtil.isValid(token)))
        {
            return ResponseEntity.ok().body(discount);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllDiscountByCurrentUser(HttpServletRequest httpServletRequest)
    {
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        Long userID = jwtTokenUtil.getUserIDFromToken(token);
        if ((userID!=null) && (jwtTokenUtil.isValid(token)))
        {
            return this.discountService.findAllDiscountByUserID(userID);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }
}
