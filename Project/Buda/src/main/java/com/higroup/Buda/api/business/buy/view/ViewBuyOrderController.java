package com.higroup.Buda.api.business.buy.view;

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
@RequestMapping("api/business/buy/view")
public class ViewBuyOrderController {

    private final ViewBuyOrderService viewBuyOrderService;
    private final RequestUtil requestUtil;

    @Autowired
    public ViewBuyOrderController(ViewBuyOrderService viewNewBuyOrderService, RequestUtil requestUtil) {
        this.requestUtil = requestUtil;
        this.viewBuyOrderService = viewNewBuyOrderService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllBuyOrderByUserID(HttpServletRequest httpServletRequest, Pageable pageable) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewBuyOrderService.findAllBuyOrderByUserID(userID, pageable));
    }

    @GetMapping(path = "supplier/{supplierName}")
    public ResponseEntity<?> findAllBuyOrderByUserIDAndSupplierName(HttpServletRequest httpServletRequest,
            @PathVariable String supplierName, Pageable pageable) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok()
                .body(this.viewBuyOrderService.findBuyOrderBySupplierName(userID, supplierName, pageable));
    }

    @GetMapping(path = "supplier/{supplierID}/all")
    public ResponseEntity<?> findAllBuyOrderBySupplierID(HttpServletRequest httpServletRequest,
            @PathVariable Long supplierID) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewBuyOrderService.findAllBuyOrderBySupplierID(userID, supplierID));
    }

    @GetMapping(path = "all/last-x-days/{X}")
    public ResponseEntity<?> findAllLastXDaysBuyOrderByCurrentUser(HttpServletRequest httpServletRequest,
            @PathVariable Long X) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewBuyOrderService.findAllBuyOrderByUserIDLastXDays(userID, X));
    }

    @GetMapping(path = "all/incompleted")
    public ResponseEntity<?> findAllIncompletedBuyOrderByCurrentUser(HttpServletRequest httpServletRequest) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewBuyOrderService.findAllIncompletedBuyOrderByUser(userID));
    }

    @GetMapping(path = "all/completed")
    public ResponseEntity<?> findAllCompletedBuyOrderByCurrentUser(HttpServletRequest httpServletRequest,
            Pageable pageable) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewBuyOrderService.findAllCompletedBuyOrderByUser(userID, pageable));
    }

    @GetMapping(path = "all/status/{status}")
    public ResponseEntity<?> findAllBuyOrderByCurrentUserAndStatus(HttpServletRequest httpServletRequest,
            @PathVariable Status status, Pageable pageable) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewBuyOrderService.findAllBuyOrderByStatus(userID, status, pageable));
    }

    @GetMapping(path = "textID/{textID}")
    public ResponseEntity<?> findAllBuyOrderByTextID(HttpServletRequest httpServletRequest, @PathVariable String textID,
            Pageable pageable) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewBuyOrderService.findBuyOrderByTextID(userID, textID, pageable));
    }

    @PostMapping(path = "period")
    public ResponseEntity<?> findAllBuyOrderByPeriod(HttpServletRequest httpServletRequest,
            @RequestBody PeriodDTO periodDTO, Pageable pageable) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewBuyOrderService.findBuyOrderInPeriod(userID, periodDTO, pageable));
    }

    @GetMapping(path = "id/{buyOrderID}")
    public ResponseEntity<?> findBuyOrderByBuyOrderID(HttpServletRequest httpServletRequest,
            @PathVariable Long buyOrderID) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewBuyOrderService.findBuyOrderByBuyOrderID(userID, buyOrderID));
    }

    @PostMapping(path = "filter") 
    public ResponseEntity<?> findBuyOrderByFilter(HttpServletRequest httpServletRequest, @RequestBody ViewBuyOrderFilter viewBuyOrderFilter,
    Pageable pageable) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewBuyOrderService.findBuyOrderByFilter(userID, viewBuyOrderFilter, pageable));
    }
}
