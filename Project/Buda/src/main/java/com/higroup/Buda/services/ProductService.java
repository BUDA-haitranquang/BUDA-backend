package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductGroup;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.ProductGroupRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.util.Checker.PresentChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductGroupRepository productGroupRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, ProductGroupRepository productGroupRepository, UserRepository userRepository)
    {
        this.productGroupRepository = productGroupRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }
    @Autowired
    private PresentChecker presentChecker;

    public ResponseEntity<?> registerNewProduct(Long userID, Product product)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
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
    public Product findProductByProductID(Long productID)
    {
        presentChecker.checkIdAndRepository(productID, productRepository);
        Product product = this.productRepository.findProductByProductID(productID);
        return product;
    }
    public List<Product> findAllProductByProductGroupID(Long productGroupID)
    {
        Optional<ProductGroup> productGroup = this.productGroupRepository.findProductGroupByProductGroupID(productGroupID);
        return productGroup.map(this.productRepository::findAllProductByProductGroup).orElse(null);
    }
}
