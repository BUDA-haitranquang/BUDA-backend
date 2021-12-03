package com.higroup.Buda.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.services.ProductService;
import com.higroup.Buda.util.JwtTokenUtil;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return this.productService.registerNewProduct(userID, product);
    }

    @GetMapping(path = "/productID/{productID}")
    public ResponseEntity<?> findProductByProductID(HttpServletRequest httpServletRequest, @PathVariable Long productID)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.productService.findProductByProductID(userID, productID));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllProductByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.productService.findAllProductByUserID(userID));                
    }

    @GetMapping(path = "/hidden/all")
    public ResponseEntity<?> findAllHiddenProduct(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.productService.findAllHiddenProductByUserID(userID));
    }

    @GetMapping(path = "/hide/{productID}")
    public ResponseEntity<?> hideProductByProductID(HttpServletRequest httpServletRequest, @PathVariable Long productID)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.productService.hideProductByProductID(userID, productID));
    }

    @GetMapping(path = "/group/{productGroupID}/all")
    public ResponseEntity<?> findAllProductByProductGroupID(HttpServletRequest httpServletRequest, @PathVariable Long productGroupID)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.productService.findAllProductByProductGroupID(userID, productGroupID));
    }

    @DeleteMapping(path = "productID/{productID}")
    public ResponseEntity<?> deleteProductByProductID(HttpServletRequest httpServletRequest, @PathVariable Long productID)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        this.productService.deleteProductByProductID(userID, productID);
        return ResponseEntity.ok().body("Delete successfully, this action can not be reversed");
    }
}
