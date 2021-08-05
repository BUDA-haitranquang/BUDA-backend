package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductLeftLog;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.ProductLeftLogRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductLeftLogService {
    private final ProductLeftLogRepository productLeftLogRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    @Autowired
    public ProductLeftLogService(ProductLeftLogRepository productLeftLogRepository, UserRepository userRepository, ProductRepository productRepository)
    {
        this.userRepository = userRepository;
        this.productLeftLogRepository = productLeftLogRepository;
        this.productRepository = productRepository;
    }
    public ResponseEntity<?> findProductLeftLogByProductLeftLogID(Long productLeftLogID)
    {
        Optional<ProductLeftLog> productLeftLog = this.productLeftLogRepository.findProductLeftLogByProductLeftLogID(productLeftLogID);
        if (productLeftLog.isPresent())
        {
            return ResponseEntity.ok().body(productLeftLog.get().toString());
        }
        return ResponseEntity.badRequest().body("Not found");
    }
    public ResponseEntity<?> registerNewProductLeftLog(Long userID, ProductLeftLog productLeftLog)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (!user.isPresent())
        {
            return ResponseEntity.badRequest().body("User not found");
        }
        productLeftLog.setUserID(userID);
        this.productLeftLogRepository.save(productLeftLog);
        return ResponseEntity.ok().body(productLeftLog.toString());
    }
    public List<ProductLeftLog> findAllProductLeftLogByProduct(Long productID)
    {
        Optional<Product> product = this.productRepository.findProductByProductID(productID);
        if (!product.isPresent())
        {
            return null;
        }
        return this.productLeftLogRepository.findAllProductLeftLogByProduct(product.get());
    }
    public List<ProductLeftLog> findAllProductLeftLogByStaffID(Long staffID)
    {
        return this.productLeftLogRepository.findAllProductLeftLogByStaffID(staffID);
    }
    public List<ProductLeftLog> findAllProductLeftLogByUserID(Long userID)
    {
        return this.productLeftLogRepository.findAllProductLeftLogByUserID(userID);
    }
}
