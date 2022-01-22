package com.higroup.Buda.api.business.payslip.delete;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/business/pay-slip/delete")
@CrossOrigin("*")
public class DeletePaySlipController {
    private final DeletePaySlipService deletePaySlipService;
    private final RequestUtil requestUtil;
    @Autowired
    public DeletePaySlipController(RequestUtil requestUtil, DeletePaySlipService deletePaySlipService){
        this.deletePaySlipService = deletePaySlipService;
        this.requestUtil = requestUtil;
    }
    @DeleteMapping(path = "id/{paySlipID}")
    public ResponseEntity<?> deletePaySlip(HttpServletRequest httpServletRequest, @PathVariable Long paySlipID){
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        this.deletePaySlipService.deletePaySlipByPaySlipID(userID, paySlipID);
        return ResponseEntity.ok().body("Delete pay slip successfully");
    }
}
