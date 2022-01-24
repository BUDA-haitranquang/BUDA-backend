package com.higroup.Buda.api.product.packaging.group.create;

import java.util.List;

import com.higroup.Buda.entities.ProductGroup;

import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/product/packaging/group/create")
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
