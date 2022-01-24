package com.higroup.Buda.api.product.packaging.group.delete;

import java.util.List;

import com.higroup.Buda.entities.ProductGroup;

import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/product/packaging/group/delete")
@CrossOrigin("*")
public class ProductGroupDeleteController {
    private final ProductGroupDeleteService productGroupService;
    private final RequestUtil requestUtil;

    @Autowired
    public ProductGroupDeleteController(ProductGroupDeleteService productGroupService, RequestUtil requestUtil)
    {
        this.productGroupService = productGroupService;
        this.requestUtil = requestUtil;
    }
    @GetMapping(path = "/all")
    @DeleteMapping(path = "/{productGroupID}")
    public ResponseEntity<?> deleteProductGroup(HttpServletRequest httpServletRequest, @PathVariable Long productGroupID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.productGroupService.deleteProductGroup(userID, productGroupID);
        return ResponseEntity.ok().body("Delete successfully");
    }
}