package com.higroup.Buda.api.product.packaging.component.remove;

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
@RequestMapping("api/product/packaging/component/remove")
@CrossOrigin("*")
public class RemoveProductComponentController {
    private final RemoveProductComponentService removeProductComponentService;
    private final RequestUtil requestUtil;
    @Autowired
    public RemoveProductComponentController(RemoveProductComponentService removeProductComponentService,
    RequestUtil requestUtil){
        this.removeProductComponentService = removeProductComponentService;
        this.requestUtil = requestUtil;
    }
    @DeleteMapping
    public ResponseEntity<?> removeProductComponent(HttpServletRequest httpServletRequest, @RequestBody @Valid RemoveProductComponentDTO removeProductComponentDTO){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.removeProductComponentService.removeProductComponent(userID, removeProductComponentDTO);
        return ResponseEntity.ok().body("Delete successfully");
    }
}
