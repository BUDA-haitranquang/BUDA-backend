package com.higroup.Buda.api.product.create.retail;

import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("api/product/create/retail")
@CrossOrigin("*")
public class RetailCreateController {
    private final RequestUtil requestUtil;
    private final RetailCreateService retailCreateService;
    @Autowired
    public RetailCreateController(RetailCreateService retailCreateService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.retailCreateService = retailCreateService;
    }
    @PostMapping
    public ResponseEntity<?> createNewRetailProduct(HttpServletRequest httpServletRequest, @Valid @RequestBody RetailCreateDTO retailCreateDTO)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.retailCreateService.createNewRetailProduct(userID, retailCreateDTO));
    }
}
