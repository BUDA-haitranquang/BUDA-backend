package com.higroup.Buda.api.business.payslip.create.paydebt;

import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/business/pay-slip/create/paydebt")
@CrossOrigin("*")
public class CreatePaySlipPayDebtController {
    private final CreatePaySlipPayDebtService createPaySlipPayDebtService;
    private final RequestUtil requestUtil;
    @Autowired
    public CreatePaySlipPayDebtController(CreatePaySlipPayDebtService createPaySlipPayDebtService, RequestUtil requestUtil){
        this.createPaySlipPayDebtService = createPaySlipPayDebtService;
        this.requestUtil = requestUtil;
    }
    @PostMapping
    public ResponseEntity<?> createNewPaySlipPayDebt(HttpServletRequest httpServletRequest,
                                              @RequestBody PaySlipDebtDTO paySlipDebtDTO){
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.createPaySlipPayDebtService.createPaySlipPayDebt(userID, paySlipDebtDTO.getBuyOrderIDs()));
    }
}
