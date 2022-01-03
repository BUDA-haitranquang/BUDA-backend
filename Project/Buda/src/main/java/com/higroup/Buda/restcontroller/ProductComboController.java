package com.higroup.Buda.restcontroller;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.services.ProductComboService;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productComboService.findAllByUserID(userID));
    }
    @GetMapping(path = "/include/product/{productID}")
    public ResponseEntity<?> findAllProductComboIncludeProduct(HttpServletRequest httpServletRequest, @PathVariable Long productID)
    {
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productComboService.findAllProductComboIncludeProduct(userID, productID));
    }
}
