package com.higroup.Buda.api.business.buy.neworder.staff;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.api.business.buy.neworder.BuyOrderDTO;
import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/business/buy/new-order/staff")
public class NewBuyOrderStaffController {
    private final NewBuyOrderStaffService newBuyOrderService;
    private final RequestUtil requestUtil;
    @Autowired
    public NewBuyOrderStaffController(NewBuyOrderStaffService newBuyOrderService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.newBuyOrderService = newBuyOrderService;
    }
    @PostMapping(path = "/new")
    public ResponseEntity<?> createNewBuyOrder(HttpServletRequest httpServletRequest, @RequestBody BuyOrderDTO buyOrderDTO)
    {
        Long userID = this.requestUtil.getUserIDFromStaffToken(httpServletRequest);
        Long staffID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.newBuyOrderService.createNewBuyOrder(userID, staffID, buyOrderDTO));
    }

    @DeleteMapping(path = "{buyOrderID}")
    public ResponseEntity<?> deleteBuyOrderByBuyOrderID(HttpServletRequest httpServletRequest, @PathVariable Long buyOrderID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.newBuyOrderService.deleteBuyOrderByBuyOrderID(userID, buyOrderID);
        return ResponseEntity.ok().body("Delete successfully");
    }
}
