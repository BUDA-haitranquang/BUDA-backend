package com.higroup.Buda.api.product.create;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("api/product/create")
@CrossOrigin("*")
public class ProductCreateController {
    private final RequestUtil requestUtil;
    private final ProductCreateService productCreateService;
    @Autowired
    public ProductCreateController(ProductCreateService productCreateService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.productCreateService = productCreateService;
    }
    @PostMapping
    public ResponseEntity<?> createNewProduct(HttpServletRequest httpServletRequest, @Valid @RequestBody Product product)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productCreateService.createNewProduct(userID, product));
    }
}
