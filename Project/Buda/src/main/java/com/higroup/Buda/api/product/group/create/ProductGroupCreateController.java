package com.higroup.Buda.api.product.group.create;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.higroup.Buda.entities.ProductGroup;
import com.higroup.Buda.util.Checker.RequestUtil;

@RestController
@RequestMapping("api/product/group/create")
@CrossOrigin("*")
public class ProductGroupCreateController {
    private final ProductGroupCreateService productGroupCreateService;
    private final RequestUtil requestUtil;

    @Autowired
    public ProductGroupCreateController(ProductGroupCreateService productGroupCreateService, RequestUtil requestUtil)
    {
        this.productGroupCreateService = productGroupCreateService;
        this.requestUtil = requestUtil;
    }
    @PostMapping
    public ResponseEntity<?> createProductGroup(HttpServletRequest httpServletRequest, @RequestBody ProductGroup productGroup)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productGroupCreateService.createProductGroup(userID, productGroup));
    }
}
