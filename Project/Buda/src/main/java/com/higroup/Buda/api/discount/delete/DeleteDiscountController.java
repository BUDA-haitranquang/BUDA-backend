package com.higroup.Buda.api.discount.delete;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/discount/delete")
@CrossOrigin("*")
public class DeleteDiscountController {
    private final DeleteDiscountService deleteDiscountService;
    private final RequestUtil requestUtil;
    @Autowired
    public DeleteDiscountController(DeleteDiscountService deleteDiscountService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.deleteDiscountService = deleteDiscountService;
    }
    @DeleteMapping(path = "id/{discountID}")
    public ResponseEntity<?> deleteDiscountByID(HttpServletRequest httpServletRequest, @PathVariable Long discountID){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.deleteDiscountService.deleteDiscount(userID, discountID);
        return ResponseEntity.ok().body("Delete successfully");
    }
}
