package com.higroup.Buda.api.product.group.addproduct;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product/group/add-product")
@CrossOrigin("*")
public class AddProductToGroupController {
    private final AddProductToGroupService addProductToGroupService;
    private final RequestUtil requestUtil;
    @Autowired
    public AddProductToGroupController(AddProductToGroupService addProductToGroupService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.addProductToGroupService = addProductToGroupService;
    }
    @PostMapping
    public ResponseEntity<?> addProductToGroup(HttpServletRequest httpServletRequest, 
    @RequestBody @Valid AddProductToGroupDTO addProductToGroupDTO){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.addProductToGroupService.addProductToProductGroup
        (userID, addProductToGroupDTO.getProductGroupID(), addProductToGroupDTO.getProductID()));
    }
}
