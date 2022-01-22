package com.higroup.Buda.api.supplier.view;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/supplier/view")
public class ViewSupplierController {
    private final ViewSupplierService viewSupplierService;
    private final RequestUtil requestUtil;
    @Autowired
    public ViewSupplierController(RequestUtil requestUtil, ViewSupplierService viewSupplierService){
        this.requestUtil = requestUtil;
        this.viewSupplierService = viewSupplierService;
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllByUserID(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewSupplierService.findAllByUserID(userID));
    }
    @GetMapping(path = "id/{supplierID}")
    public ResponseEntity<?> findSupplierBySupplierID(HttpServletRequest httpServletRequest, @PathVariable Long supplierID)
    {
        Long userID = requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewSupplierService.findSupplierBySupplierID(userID, supplierID));
    }
}
