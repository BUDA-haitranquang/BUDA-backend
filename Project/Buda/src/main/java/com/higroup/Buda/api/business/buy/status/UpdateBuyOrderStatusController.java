package com.higroup.Buda.api.business.buy.status;


import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@RestController
@CrossOrigin("*")
@RequestMapping("api/business/buy/status")
public class UpdateBuyOrderStatusController {
    private final UpdateBuyOrderStatusService updateBuyOrderStatusService;
    private final RequestUtil requestUtil;

    @Autowired
    public UpdateBuyOrderStatusController(UpdateBuyOrderStatusService updateBuyOrderStatusService, RequestUtil requestUtil) {
        this.updateBuyOrderStatusService = updateBuyOrderStatusService;
        this.requestUtil = requestUtil;
    }

    @PutMapping
    public ResponseEntity<?> printBuyOrder(HttpServletRequest httpServletRequest, @RequestBody UpdateBuyOrderStatusDTO updateBuyOrderStatusDTO) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.updateBuyOrderStatusService.updateBuyOrderStatus(userID, updateBuyOrderStatusDTO));
    }
}
