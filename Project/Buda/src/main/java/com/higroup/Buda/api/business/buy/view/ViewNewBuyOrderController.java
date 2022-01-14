package com.higroup.Buda.api.business.buy.view;

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
@RequestMapping("api/business/buy/new-order")
public class ViewNewBuyOrderController {

    private final ViewNewBuyOrderService viewNewBuyOrderService;
    private final RequestUtil requestUtil;

    @Autowired
    public ViewNewBuyOrderController(ViewNewBuyOrderService viewNewBuyOrderService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.viewNewBuyOrderService = viewNewBuyOrderService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllBuyOrderByUserID(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewNewBuyOrderService.findAllBuyOrderByUserID(userID));
    }

    @GetMapping(path = "supplier/{supplierID}/all")
    public ResponseEntity<?> findAllBuyOrderBySupplierID(HttpServletRequest httpServletRequest, @PathVariable Long supplierID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewNewBuyOrderService.findAllBuyOrderBySupplierID(userID, supplierID));
    }

    @GetMapping(path = "all/last-x-days/{X}")
    public ResponseEntity<?> findAllLastXDaysBuyOrderByCurrentUser(HttpServletRequest httpServletRequest, @PathVariable Long X)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewNewBuyOrderService.findAllBuyOrderByUserIDLastXDays(userID, X));
    }
    @GetMapping(path = "all/incompleted")
    public ResponseEntity<?> findAllIncompletedBuyOrderByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewNewBuyOrderService.findAllIncompletedBuyOrderByUser(userID));
    }
    @GetMapping(path = "all/status/{status}")
    public ResponseEntity<?> findAllBuyOrderByCurrentUserAndStatus(HttpServletRequest httpServletRequest, @PathVariable Status status)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewNewBuyOrderService.findAllBuyOrderByStatus(userID, status));
    }
}
