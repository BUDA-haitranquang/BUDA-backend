package com.higroup.Buda.restcontroller;

import java.util.List;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductGroup;
import com.higroup.Buda.services.ProductGroupService;

import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/product-group")
@CrossOrigin("*")
public class ProductGroupController {
    private final ProductGroupService productGroupService;
    private final RequestUtil requestUtil;

    @Autowired
    public ProductGroupController(ProductGroupService productGroupService, RequestUtil requestUtil)
    {
        this.productGroupService = productGroupService;
        this.requestUtil = requestUtil;
    }
    @GetMapping(path = "/all")
    public List<ProductGroup> findAllByUserID(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return this.productGroupService.findAllByUserID(userID);
    }
    @PostMapping(path = "/add")
    public ResponseEntity<?> createProductGroup(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return this.productGroupService.createProductGroup(userID);
    }
    @PostMapping(path = "/remove")
    public ResponseEntity<?> deleteProductGroup(HttpServletRequest httpServletRequest, Long productGroupID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.productGroupService.deleteProductGroup(userID, productGroupID);
        return ResponseEntity.ok().body("Delete successfully");
    }
    @PostMapping(path = "/{productGroupID}/add/{productID}")
    public ResponseEntity<?> addProductToProductGroup(HttpServletRequest httpServletRequest, @PathVariable Long productGroupID, @PathVariable Long productID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.productGroupService.addProductToProductGroup(userID, productGroupID, productID);
        return ResponseEntity.ok().body("Add Product to Product Group successfully");
    }
    @PostMapping(path = "/{productGroupID}/remove/{productID}")
    public ResponseEntity<?> removeProductFromProductGroup(HttpServletRequest httpServletRequest, @PathVariable Long productGroupID, @PathVariable Long productID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.productGroupService.removeProductFromProductGroup(userID, productGroupID, productID);
        return ResponseEntity.ok().body("Remove Product from Product Group successfully");
    }
}
