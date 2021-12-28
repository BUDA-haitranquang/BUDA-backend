package com.higroup.Buda.restcontroller;

import com.higroup.Buda.services.ProductComboService;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/product-combo")
public class ProductComboController {
    private final ProductComboService productComboService;
    private final RequestUtil requestUtil;
    @Autowired
    public ProductComboController(ProductComboService productComboService, RequestUtil requestUtil)
    {
        this.productComboService = productComboService;
        this.requestUtil = requestUtil;
    }
}
