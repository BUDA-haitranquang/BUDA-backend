package com.higroup.Buda.api.product.update;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("api/product/edit")
@CrossOrigin("*")
public class ProductUpdateController {
    private final RequestUtil requestUtil;
    private final ProductUpdateService productUpdateService;
    @Autowired
    public ProductUpdateController(ProductUpdateService productUpdateService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.productUpdateService = productUpdateService;
    }
    @PutMapping(path = "/{productID}")
    public ResponseEntity<?> editProduct(HttpServletRequest httpServletRequest, @PathVariable Long productID, @RequestBody Product product) throws InvocationTargetException, IllegalAccessException {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productUpdateService.editProduct(userID, productID, product));
    }
}
