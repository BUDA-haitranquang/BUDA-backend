package com.higroup.Buda.restcontroller;

import com.higroup.Buda.customDTO.QuantityLog;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.services.ProductService;
import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("api/product")
@CrossOrigin("*")
public class ProductController {
    private final RequestUtil requestUtil;
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.productService = productService;
    }
    @PostMapping(path = "/new")
    public ResponseEntity<?> registerNewProduct(HttpServletRequest httpServletRequest, @Valid @RequestBody Product product)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return this.productService.registerNewProduct(userID, product);
    }

    @GetMapping(path = "/productID/{productID}")
    public ResponseEntity<?> findProductByProductID(HttpServletRequest httpServletRequest, @PathVariable Long productID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productService.findProductByProductID(userID, productID));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllProductByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productService.findAllProductByUserID(userID));                
    }

    @GetMapping(path = "/hidden/all")
    public ResponseEntity<?> findAllHiddenProduct(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productService.findAllHiddenProductByUserID(userID));
    }

    @GetMapping(path = "/hide/{productID}")
    public ResponseEntity<?> hideProductByProductID(HttpServletRequest httpServletRequest, @PathVariable Long productID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productService.hideProductByProductID(userID, productID));
    }

    @GetMapping(path = "/group/{productGroupID}/all")
    public ResponseEntity<?> findAllProductByProductGroupID(HttpServletRequest httpServletRequest, @PathVariable Long productGroupID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productService.findAllProductByProductGroupID(userID, productGroupID));
    }

    @DeleteMapping(path = "productID/{productID}")
    public ResponseEntity<?> deleteProductByProductID(HttpServletRequest httpServletRequest, @PathVariable Long productID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.productService.deleteProductByProductID(userID, productID);
        return ResponseEntity.ok().body("Delete successfully, this action can not be reversed");
    }
    @PostMapping(path = "/edit/quantity/{productID}")
    public ResponseEntity<?> editProductQuantity(HttpServletRequest httpServletRequest, @PathVariable Long productID, @RequestBody QuantityLog quantityLog)
    {
        Integer amountLeftChange = quantityLog.getAmountLeftChange();
        String message = quantityLog.getMessage();
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productService.editProductQuantity(userID, productID, amountLeftChange, message));
    }
    @GetMapping(path = "/alert")
    public ResponseEntity<?> findAlertAmountProduct(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productService.findAlertAmountProduct(userID));
    }
    @PutMapping(path = "/edit/{productID}")
    public ResponseEntity<?> editProduct(HttpServletRequest httpServletRequest, @PathVariable Long productID, @RequestBody Product product) throws InvocationTargetException, IllegalAccessException {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productService.editProduct(userID, productID, product));
    }
}
