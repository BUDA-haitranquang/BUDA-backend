package com.higroup.Buda.api.business.sell.view;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/business/sell/view")
public class ViewSellOrderController {
    private final ViewSellOrderService viewSellOrderService;
    private final RequestUtil requestUtil;

    @Autowired
    public ViewSellOrderController(ViewSellOrderService viewNewSellOrderService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.viewSellOrderService = viewNewSellOrderService;
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllSellOrderByCurrentUser(HttpServletRequest httpServletRequest, Pageable pageable)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewSellOrderService.findAllSellOrderByUserID(userID, pageable));
    }
    @GetMapping(path = "customer/{customerID}/all")
    public ResponseEntity<?> findAllSellOrderByCustomerID(HttpServletRequest httpServletRequest, @PathVariable Long customerID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewSellOrderService.findAllSellOrderByCustomerID(userID, customerID));
    }

    @GetMapping(path = "all/last-x-days/{X}")
    public ResponseEntity<?> findAllLastXDaysSellOrderByCurrentUser(HttpServletRequest httpServletRequest, @PathVariable Long X)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewSellOrderService.findAllSellOrderByUserIDLastXDays(userID, X));
    }
    @GetMapping(path = "all/incompleted")
    public ResponseEntity<?> findAllIncompletedSellOrderByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewSellOrderService.findAllIIncompletedSellOrderByUserID(userID));
    }
    @GetMapping(path = "all/status/{status}")
    public ResponseEntity<?> findAllSellOrderByCurrentUserAndStatus(HttpServletRequest httpServletRequest, @PathVariable Status status)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewSellOrderService.findAllSellOrderByUserAndStatus(userID, status));
    }
    @GetMapping(path = "textID/{textID}")
    public ResponseEntity<?> findAllSellOrderByTextID(HttpServletRequest httpServletRequest, @PathVariable String textID){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewSellOrderService.findSellOrderByTextID(userID, textID));
    }
}
