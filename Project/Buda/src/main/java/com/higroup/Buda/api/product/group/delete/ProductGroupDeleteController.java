package com.higroup.Buda.api.product.group.delete;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product/group/delete")
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
    @DeleteMapping(path = "id/{productGroupID}")
    public ResponseEntity<?> deleteProductGroup(HttpServletRequest httpServletRequest, @PathVariable Long productGroupID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.productGroupService.deleteProductGroup(userID, productGroupID);
        return ResponseEntity.ok().body("Delete successfully");
    }
}