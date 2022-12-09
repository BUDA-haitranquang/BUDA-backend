package com.higroup.Buda.api.business.sell.warranty.create;

import com.higroup.Buda.customDTO.RegisterWarrantyOrder;
import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/business/sell/warranty/create")
public class WarrantyOrderCreateController {
    private final WarrantyOrderCreateService warrantyOrderCreateService;
    private final RequestUtil requestUtil;
    @Autowired
    public WarrantyOrderCreateController(WarrantyOrderCreateService warrantyOrderCreateService, RequestUtil requestUtil)
    {
        this.warrantyOrderCreateService = warrantyOrderCreateService;
        this.requestUtil = requestUtil;
    }
    @PostMapping
    public ResponseEntity<?> createNewWarrantyOrder(HttpServletRequest httpServletRequest, @RequestBody RegisterWarrantyOrder registerWarrantyOrder)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.warrantyOrderCreateService.createNewWarrantyOrder(userID, registerWarrantyOrder));
    }
}
