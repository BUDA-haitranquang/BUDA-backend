package com.higroup.Buda.api.product.delete;

import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/product")
@CrossOrigin("*")
public class ProductDeleteController {
    private final RequestUtil requestUtil;
    private final ProductDeleteService productDeleteService;
    @Autowired
    public ProductDeleteController(ProductDeleteService productDeleteService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.productDeleteService = productDeleteService;
    }
    @DeleteMapping(path = "productID/{productID}")
    public ResponseEntity<?> deleteProductByProductID(HttpServletRequest httpServletRequest, @PathVariable Long productID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.productDeleteService.deleteProductByProductID(userID, productID);
        return ResponseEntity.ok().body("Delete successfully, this action can not be reversed");
    }
}
