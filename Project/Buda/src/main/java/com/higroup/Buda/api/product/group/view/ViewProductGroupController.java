package com.higroup.Buda.api.product.group.view;

import java.util.List;

import com.higroup.Buda.entities.ProductGroup;

import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/product/group/view")
@CrossOrigin("*")
public class ViewProductGroupController {
    private final ViewProductGroupService viewProductGroupService;
    private final RequestUtil requestUtil;

    @Autowired
    public ViewProductGroupController(ViewProductGroupService productGroupService, RequestUtil requestUtil)
    {
        this.viewProductGroupService = productGroupService;
        this.requestUtil = requestUtil;
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllByUserID(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewProductGroupService.findAllByUserID(userID));
    }
    
    @GetMapping(path = "/productGroupID/{productGroupID}")
    public ResponseEntity<?> findProductGroupByID(HttpServletRequest httpServletRequest, @PathVariable Long productGroupID) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewProductGroupService.findProductGroupByProductGroupID(userID, productGroupID));
    }
   
    @GetMapping(path = "/{productGroupID}/products")
    public ResponseEntity<?> findAllProductByProductGroup(HttpServletRequest httpServletRequest, @PathVariable Long productGroupID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewProductGroupService.findAllProductByProductGroup(userID, productGroupID));
    }
}