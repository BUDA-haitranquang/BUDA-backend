package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, UserRepository userRepository)
    {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }
    public ResponseEntity<?> registerNewProduct(Long userID, Product product)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (!user.isPresent())
        {
            return ResponseEntity.badRequest().body("User not found");
        }
        product.setUserID(userID);
        this.productRepository.save(product);
        return ResponseEntity.ok().body(product);
    }
    public List<Product> findAllProductByUserID(Long userID)
    {
        return this.productRepository.findAllProductByUserID(userID);
    }
    public ResponseEntity<?> findProductByProductID(Long productID)
    {
        Optional<Product> product = this.productRepository.findProductByProductID(productID);
        if (product.isPresent())
        {
            return ResponseEntity.ok().body(product.get().toString());
        }
        return ResponseEntity.badRequest().body("Not found");
    }
}
