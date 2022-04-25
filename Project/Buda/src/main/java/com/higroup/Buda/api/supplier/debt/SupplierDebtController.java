package com.higroup.Buda.api.supplier.debt;

import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("api/supplier/debt")
public class SupplierDebtController {
    private final RequestUtil requestUtil;
    private final SupplierDebtService supplierDebtService;
    @Autowired

    public SupplierDebtController(RequestUtil requestUtil, SupplierDebtService supplierDebtService) {
        this.requestUtil = requestUtil;
        this.supplierDebtService = supplierDebtService;
    }

    @GetMapping(path = "/list/{supplierID}")
    public ResponseEntity<?> findDelayBuyOrderBySupplierID(HttpServletRequest httpServletRequest, @PathVariable Long supplierID) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.supplierDebtService.findDelayBuyOrderBySupplierID(userID, supplierID));
    }

    @GetMapping(path = "/total/{supplierID}")
    public ResponseEntity<?> findDebtBySupplierID(HttpServletRequest httpServletRequest, @PathVariable Long supplierID) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.supplierDebtService.findDebtBySupplierID(userID, supplierID));
    }
}
