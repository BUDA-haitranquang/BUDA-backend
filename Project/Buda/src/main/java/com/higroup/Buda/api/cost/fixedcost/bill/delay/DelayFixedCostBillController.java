package com.higroup.Buda.api.cost.fixedcost.bill.delay;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cost/fixed-cost/bill/delay")
@CrossOrigin("*")
public class DelayFixedCostBillController {
    private final DelayFixedCostBillService delayFixedCostBillService;
    private final RequestUtil requestUtil;
    @Autowired
    public DelayFixedCostBillController(DelayFixedCostBillService delayFixedCostBillService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.delayFixedCostBillService = delayFixedCostBillService;
    }
    @PutMapping(path = "id/{fixedCostBillID}")
    public ResponseEntity<?> delayFixedCostBill(HttpServletRequest httpServletRequest, @PathVariable Long fixedCostBillID){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.delayFixedCostBillService.delayFixedCostBill(userID, fixedCostBillID));
    }
}
