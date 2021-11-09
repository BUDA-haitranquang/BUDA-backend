package com.higroup.Buda.services;

import java.util.Collection;
import java.util.Collections;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public List<Product> findAllHiddenProductByUserID(Long userID)
    {
        return this.productRepository.findAllHiddenProductByUserID(userID);
    }
    public Product hideProductByProductID(Long userID, Long productID)
    {
        Product product = this.productRepository.findProductByProductID(productID);
        if ((product!=null) && (product.getUserID() == userID))
        {
            product.setVisible(false);
            this.productRepository.save(product);
            return product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
    }
    public Product findProductByProductID(Long userID, Long productID)
    {
        Product product = this.productRepository.findProductByProductID(productID);
        if ((product!=null) && (product.getUserID() == userID))
        {
            return product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
    }
    public void deleteProductByProductID(Long userID, Long productID)
    {
        Product product = this.productRepository.findProductByProductID(productID);
        if ((product!=null) && (product.getUserID() == userID))
        {
            if (product.getVisible() == true)
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not delete a visible product");
            }
            else
            {
                this.productRepository.delete(product);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product does not exists");
    }
    public List<Product> findAllProductByProductGroupID(Long userID, Long productGroupID)
    {
        Optional<ProductGroup> productGroup = this.productGroupRepository.findProductGroupByProductGroupID(productGroupID);
        if ((productGroup.isPresent()) && (productGroup.get().getUserID() == userID))
        {
            return this.productRepository.findAllProductByProductGroup(productGroup.get());
        }
        return Collections.emptyList();
    }
}
