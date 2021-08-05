package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }
    public ResponseEntity<?> registerNewProduct(Long userID, Product product)
    {
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
        return product.<ResponseEntity<?>>map(value -> ResponseEntity.ok().body(value.toString())).orElseGet(() -> ResponseEntity.badRequest().body("Not found"));
    }
}
