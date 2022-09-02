package com.higroup.Buda.api.product.update.quantity;

import com.higroup.Buda.customDTO.QuantityLog;
import com.higroup.Buda.entities.enumeration.LeftLogType;
import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/product/update/quantity")
@CrossOrigin("*")
public class ProductUpdateQuantityController {
    private final RequestUtil requestUtil;
    private final ProductUpdateQuantityService productUpdateQuantityService;
    @Autowired
    public ProductUpdateQuantityController(ProductUpdateQuantityService productUpdateQuantityService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.productUpdateQuantityService = productUpdateQuantityService;
    }
    @PutMapping(path = "/{productID}")
    public ResponseEntity<?> editProductQuantity(HttpServletRequest httpServletRequest, @PathVariable Long productID, @RequestBody QuantityLog quantityLog)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productUpdateQuantityService.editProductQuantity(userID, productID, quantityLog));
    }
}
