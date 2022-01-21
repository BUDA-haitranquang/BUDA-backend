package com.higroup.Buda.api.business.payslip.view;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/business/pay-slip/view")
public class ViewPaySlipController {
    private final ViewPaySlipService viewPaySlipService;
    private final RequestUtil requestUtil;
    @Autowired
    public ViewPaySlipController(ViewPaySlipService viewPaySlipService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.viewPaySlipService = viewPaySlipService;
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAllPaySlip(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewPaySlipService.findAllPaySlipByUserID(userID));
    }
    @GetMapping("/id/{paySlipID}")
    public ResponseEntity<?> findPaySlipByPaySlipID(HttpServletRequest httpServletRequest, @PathVariable Long paySlipID){
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewPaySlipService.findPaySlipByPaySlipID(userID, paySlipID));
    }
}
