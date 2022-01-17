package com.higroup.Buda.api.cost.fixedcost.bill.pay;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cost/fixed-cost/bill/pay")
@CrossOrigin("*")
public class PayFixedCostBillController {
    private final PayFixedCostBillService payFixedCostBillService;
    private final RequestUtil requestUtil;
    public PayFixedCostBillController(RequestUtil requestUtil, PayFixedCostBillService payFixedCostBillService){
        this.requestUtil = requestUtil;
        this.payFixedCostBillService = payFixedCostBillService;
    }
    @PutMapping("id/{fixedCostBillID}")
    public ResponseEntity<?> payFixedCostBill(HttpServletRequest httpServletRequest, @PathVariable Long fixedCostBillID){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.payFixedCostBillService.payFixedCostBill(userID, fixedCostBillID));
    }
}
