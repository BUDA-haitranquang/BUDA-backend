package com.higroup.Buda.api.product.packaging.component.view;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product/packaging/component/view")
@CrossOrigin("*")
public class ViewProductComponentController {
    private final ViewProductComponentService viewProductComponentService;
    private final RequestUtil requestUtil;
    @Autowired
    public ViewProductComponentController(ViewProductComponentService viewProductComponentService,
    RequestUtil requestUtil){
        this.viewProductComponentService = viewProductComponentService;
        this.requestUtil = requestUtil;
    }
    @GetMapping(path = "/productID/{productID}")
    public ResponseEntity<?> findAllComponentByProductID(HttpServletRequest httpServletRequest,
    @PathVariable Long productID){
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewProductComponentService.findAllProductComponentByProductID(userID, productID));
    }
}
