package com.higroup.Buda.api.business.sell.view;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.customDTO.PeriodDTO;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return ResponseEntity.ok().body(this.viewSellOrderService.findAllIncompletedSellOrderByUserID(userID));
    }
    @GetMapping(path = "all/completed")
    public ResponseEntity<?> findAllCompletedSellOrderByCurrentUser(HttpServletRequest httpServletRequest, Pageable pageable)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewSellOrderService.findAllCompletedSellOrderByUserID(userID, pageable));
    }
    @GetMapping(path = "all/status/{status}")
    public ResponseEntity<?> findAllSellOrderByCurrentUserAndStatus(HttpServletRequest httpServletRequest, @PathVariable Status status, Pageable pageable)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewSellOrderService.findAllSellOrderByUserAndStatus(userID, status, pageable));
    }
    @GetMapping(path = "textID/{textID}")
    public ResponseEntity<?> findAllSellOrderByTextID(HttpServletRequest httpServletRequest, @PathVariable String textID, Pageable pageable){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewSellOrderService.findSellOrderByTextID(userID, textID, pageable));
    }
    @GetMapping(path = "id/{sellOrderID}")
    public ResponseEntity<?> findSellOrderBySellOrderID(HttpServletRequest httpServletRequest, @PathVariable Long sellOrderID) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewSellOrderService.findSellOrderBySellOrderID(userID, sellOrderID));
    }
    @PostMapping(path="period")
    public ResponseEntity<?> findAllSellOrderByPeriod(HttpServletRequest httpServletRequest, @RequestBody PeriodDTO period, Pageable pageable){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewSellOrderService.findAllSellOrderInPeriod(userID, period, pageable));
    }
    @GetMapping(path="customer/{customerName}")
    public ResponseEntity<?> findAllSellOrderByCustomerName(HttpServletRequest httpServletRequest, @PathVariable String customerName, Pageable pageable){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewSellOrderService.findAllSellOrderByCustomerName(userID, customerName, pageable));
    }
}
