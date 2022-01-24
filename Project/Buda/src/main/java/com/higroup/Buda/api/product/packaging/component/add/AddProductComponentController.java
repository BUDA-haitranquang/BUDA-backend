package com.higroup.Buda.api.product.packaging.component.add;

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
@RequestMapping("api/product/packaging/component/add")
@CrossOrigin("*")
public class AddProductComponentController {
    private final AddProductComponentService addProductComponentService;
    private final RequestUtil requestUtil;
    @Autowired
    public AddProductComponentController(AddProductComponentService addProductComponentService,
    RequestUtil requestUtil){
        this.addProductComponentService = addProductComponentService;
        this.requestUtil = requestUtil;
    }
    @PostMapping
    public ResponseEntity<?> addProductComponent(HttpServletRequest httpServletRequest, 
    @RequestBody @Valid AddProductComponentDTO addProductComponentDTO){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.addProductComponentService.addProductComponent(userID, addProductComponentDTO));
    }
}
