package com.higroup.Buda.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.services.ProductService;
import com.higroup.Buda.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private final JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    private final ProductService productService;
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
    public ResponseEntity<?> findProductByProductID(HttpServletRequest request, @PathVariable Long productID)
    {
        final String token = request.getHeader("Authorization").substring(7);

        Long userID = jwtTokenUtil.getUserIDFromToken(token);
        Product product = (Product)this.productService.findProductByProductID(productID).getBody();
        // if userid match ingredientID
        if(userID == product.getUserID()){
            return ResponseEntity.ok(product);
        }
        // if not return unauthorized
        else{
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(path = "/userID/{userID}/all")
    public List<Product> findAllProductByUserID(@PathVariable Long userID)
    {
        return this.productService.findAllProductByUserID(userID);
    }
    @GetMapping(path = "/product-groupID/{productGroupID}/all")
    public List<Product> findAllProductByProductGroupID(@PathVariable Long productGroupID)
    {
        return null;
    }
}
