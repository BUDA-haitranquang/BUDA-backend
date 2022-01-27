package com.higroup.Buda.api.discount.view;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/discount/view")
@CrossOrigin("*")
public class ViewDiscountController {
    private final ViewDiscountService viewDiscountService;
    private final RequestUtil requestUtil;
    @Autowired
    public ViewDiscountController(ViewDiscountService viewDiscountService, RequestUtil requestUtil){
        this.viewDiscountService = viewDiscountService;
        this.requestUtil = requestUtil;
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAllByCurrentUser(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewDiscountService.findAllDiscountByUserID(userID));
    }
    @GetMapping("id/{discountID}")
    public ResponseEntity<?> findDiscountByDiscountID(HttpServletRequest httpServletRequest, @PathVariable Long discountID){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewDiscountService.findDiscountByDiscountID(userID, discountID));
    }
}
