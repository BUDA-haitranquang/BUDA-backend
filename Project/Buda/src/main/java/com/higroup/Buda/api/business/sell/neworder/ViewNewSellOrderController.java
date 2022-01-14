package com.higroup.Buda.api.business.sell.neworder;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/business/sell/neworder")
public class ViewNewSellOrderController {
    private final ViewNewSellOrderService viewNewSellOrderService;
    private final RequestUtil requestUtil;

    @Autowired
    public ViewNewSellOrderController(ViewNewSellOrderService viewNewSellOrderService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.viewNewSellOrderService = viewNewSellOrderService;
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllSellOrderByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewNewSellOrderService.findAllSellOrderByUserID(userID));
    }
    @GetMapping(path = "customer/{customerID}/all")
    public ResponseEntity<?> findAllSellOrderByCustomerID(HttpServletRequest httpServletRequest, @PathVariable Long customerID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewNewSellOrderService.findAllSellOrderByCustomerID(userID, customerID));
    }

    @GetMapping(path = "all/last-x-days/{X}")
    public ResponseEntity<?> findAllLastXDaysSellOrderByCurrentUser(HttpServletRequest httpServletRequest, @PathVariable Long X)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewNewSellOrderService.findAllSellOrderByUserIDLastXDays(userID, X));
    }
    @GetMapping(path = "all/incompleted")
    public ResponseEntity<?> findAllIncompletedSellOrderByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewNewSellOrderService.findAllIIncompletedSellOrderByUserID(userID));
    }
    @GetMapping(path = "all/status/{status}")
    public ResponseEntity<?> findAllSellOrderByCurrentUserAndStatus(HttpServletRequest httpServletRequest, @PathVariable Status status)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewNewSellOrderService.findAllSellOrderByUserAndStatus(userID, status));
    }
}
