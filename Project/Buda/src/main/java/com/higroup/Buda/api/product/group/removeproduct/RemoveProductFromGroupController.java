package com.higroup.Buda.api.product.group.removeproduct;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product/group/remove-product")
@CrossOrigin("*")
public class RemoveProductFromGroupController {
    private final RemoveProductFromGroupService removeProductFromGroupService;
    private final RequestUtil requestUtil;
    @Autowired
    public RemoveProductFromGroupController(RemoveProductFromGroupService removeProductFromGroupService,
    RequestUtil requestUtil){
        this.removeProductFromGroupService = removeProductFromGroupService;
        this.requestUtil = requestUtil;
    }
    @DeleteMapping
    public ResponseEntity<?> removeProductFromProductGroup(HttpServletRequest httpServletRequest,
    @RequestBody @Valid RemoveProductFromGroupDTO removeProductFromGroupDTO){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.removeProductFromGroupService.removeProductFromProductGroup(userID, removeProductFromGroupDTO.getProductGroupID(), removeProductFromGroupDTO.getProductID());
        return ResponseEntity.ok().body("Remove Product from Product Group successfully");
    }
}
