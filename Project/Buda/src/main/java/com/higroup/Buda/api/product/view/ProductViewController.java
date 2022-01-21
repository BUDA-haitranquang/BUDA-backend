package com.higroup.Buda.api.product.view;


import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/product")
@CrossOrigin("*")
public class ProductViewController {
    private final RequestUtil requestUtil;
    private final ProductViewService productService;
    @Autowired
    public ProductViewController(ProductViewService productService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.productService = productService;
    }


    @GetMapping(path = "/productID/{productID}")
    public ResponseEntity<?> findProductByProductID(HttpServletRequest httpServletRequest, @PathVariable Long productID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productService.findProductByProductID(userID, productID));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllProductByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productService.findAllProductByUserID(userID));
    }

    @GetMapping(path = "/hidden/all")
    public ResponseEntity<?> findAllHiddenProduct(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productService.findAllHiddenProductByUserID(userID));
    }

    @GetMapping(path = "/hide/{productID}")
    public ResponseEntity<?> hideProductByProductID(HttpServletRequest httpServletRequest, @PathVariable Long productID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productService.hideProductByProductID(userID, productID));
    }

    @GetMapping(path = "/group/{productGroupID}/all")
    public ResponseEntity<?> findAllProductByProductGroupID(HttpServletRequest httpServletRequest, @PathVariable Long productGroupID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productService.findAllProductByProductGroupID(userID, productGroupID));
    }


    @GetMapping(path = "/alert")
    public ResponseEntity<?> findAlertAmountProduct(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productService.findAlertAmountProduct(userID));
    }

}
