package com.higroup.Buda.api.business.sell.status;


import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("api/business/sell/status")
public class UpdateSellOrderStatusController {
    private final UpdateSellOrderStatusService updateSellOrderStatusService;
    private final RequestUtil requestUtil;

    @Autowired
    public UpdateSellOrderStatusController(UpdateSellOrderStatusService updateSellOrderStatusService, RequestUtil requestUtil) {
        this.updateSellOrderStatusService = updateSellOrderStatusService;
        this.requestUtil = requestUtil;
    }

    @PutMapping
    public ResponseEntity<?> printBuyOrder(HttpServletRequest httpServletRequest, @RequestBody UpdateSellOrderStatusDTO updateSellOrderStatusDTO) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.updateSellOrderStatusService.updateSellOrderStatus(userID, updateSellOrderStatusDTO));
    }
}
