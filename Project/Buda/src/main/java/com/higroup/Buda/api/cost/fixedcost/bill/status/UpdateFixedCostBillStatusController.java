package com.higroup.Buda.api.cost.fixedcost.bill.status;


import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("api/cost/fixedcost/bill/status")
public class UpdateFixedCostBillStatusController {
    private final UpdateFixedCostBillStatusService updateFixedCostBillStatusService;
    private final RequestUtil requestUtil;
    
    @Autowired
    public UpdateFixedCostBillStatusController(UpdateFixedCostBillStatusService updateFixedCostBillStatusService, RequestUtil requestUtil) {
        this.updateFixedCostBillStatusService = updateFixedCostBillStatusService;
        this.requestUtil = requestUtil;
    }

    
    
    @PutMapping
    public ResponseEntity<?> printFixedCostBill(HttpServletRequest httpServletRequest, @RequestBody UpdateFixedCostBillStatusDTO updateFixedCostBillStatusDTO) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.updateFixedCostBillStatusService.updateFixedCostBillStatus(userID, updateFixedCostBillStatusDTO));
    }
}
