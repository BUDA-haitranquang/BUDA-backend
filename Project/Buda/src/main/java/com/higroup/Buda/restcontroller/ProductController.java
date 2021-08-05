package com.higroup.Buda.restcontroller;

import java.util.List;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product")
@CrossOrigin("*")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }
    @PostMapping(path = "new/userID/{userID}")
    public ResponseEntity<?> registerNewProduct(@PathVariable Long userID, @RequestBody Product product)
    {
        return this.productService.registerNewProduct(userID, product);
    }
    @GetMapping(path = "/productID/{productID}")
    public ResponseEntity<?> findProductByProductID(@PathVariable Long productID)
    {
        return this.productService.findProductByProductID(productID);
    }
    @GetMapping(path = "/userID/{userID}/all")
    public List<Product> findAllProductByUserID(@PathVariable Long userID)
    {
        return this.productService.findAllProductByUserID(userID);
    }
}
