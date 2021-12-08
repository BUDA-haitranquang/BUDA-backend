package com.higroup.Buda.services;

import java.util.*;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductGroup;
import com.higroup.Buda.entities.ProductLeftLog;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.ProductGroupRepository;
import com.higroup.Buda.repositories.ProductRepository;

import com.higroup.Buda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductGroupService {
    private final ProductGroupRepository productGroupRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductGroupService(ProductGroupRepository productGroupRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.productGroupRepository = productGroupRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }
    public List<ProductGroup> findAllByUserID(Long userID)
    {
        return this.productGroupRepository.findAllByUserID(userID);
    }
    public ResponseEntity<?> createProductGroup(Long userID)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            return ResponseEntity.badRequest().body("User not found");
        }
        ProductGroup productGroup = new ProductGroup();
        productGroup.setUserID(userID);
        this.productGroupRepository.save(productGroup);
        return ResponseEntity.ok().body(productGroup);
    }
    public void deleteProductGroup(Long userID, Long productGroupID)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Optional<ProductGroup> productGroup = this.productGroupRepository.findProductGroupByProductGroupID(productGroupID);
        if (productGroup.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product group not found");
        }
        this.productGroupRepository.delete(productGroup.get());
    }
    public ProductGroup addProductToProductGroup(Long userID, Long productGroupID, Long productID)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Optional<ProductGroup> productGroup = this.productGroupRepository.findProductGroupByProductGroupID(productGroupID);
        if (productGroup.isPresent() && Objects.equals(productGroup.get().getUserID(), userID))
        {
            Product product = this.productRepository.findProductByProductID(productID);
            Set<Product> products = productGroup.get().getProducts();
            products.add(product);
            productGroup.get().setProducts(products);
            this.productGroupRepository.save(productGroup.get());
            return productGroup.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

    public void removeProductFromProductGroup(Long userID, Long productGroupID, Long productID)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Optional<ProductGroup> productGroup = this.productGroupRepository.findProductGroupByProductGroupID(productGroupID);
        if (productGroup.isPresent() && Objects.equals(productGroup.get().getUserID(), userID))
        {
            List<Product> products = this.productRepository.findAllProductByProductGroup(productGroup.get());
            for (Iterator<Product> iter = products.listIterator(); iter.hasNext(); )
            {
                Product product = iter.next();
                if (product.getProductID().equals(productID))
                {
                    iter.remove();
                    return;
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }
}
