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
    public ProductGroup createProductGroup(Long userID, ProductGroup productGroup)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        productGroup.setUserID(userID);
        this.productGroupRepository.save(productGroup);
        return productGroup;
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
            Set<Product> products = productGroup.get().getProducts();
            for (Product product : products) {
                if (product.getProductID().equals(productID)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product's already in the group.");
                }
            }
            Product product = this.productRepository.findProductByProductID(productID);
            if (!product.getUserID().equals(userID))
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
            }
            Set<ProductGroup> productGroups = product.getProductGroups();
            productGroups.add(productGroup.get());
            product.setProductGroups(productGroups);
            products.add(product);
            productGroup.get().setProducts(products);
            this.productRepository.save(product);
            this.productGroupRepository.save(productGroup.get());
            return productGroup.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product group not found");
    }

    public void removeProductFromProductGroup(Long userID, Long productGroupID, Long productID)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Product product = this.productRepository.findProductByProductID(productID);
        if (!product.getUserID().equals(userID))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Optional<ProductGroup> productGroup = this.productGroupRepository.findProductGroupByProductGroupID(productGroupID);
        if (productGroup.isPresent() && Objects.equals(productGroup.get().getUserID(), userID))
        {
            Set<ProductGroup> productGroups = product.getProductGroups();
            if (productGroups.removeIf(productGroup1 -> productGroup1.getProductGroupID().equals(productGroupID)))
            {
                product.setProductGroups(productGroups);
                this.productRepository.save(product);
                Set<Product> products = productGroup.get().getProducts();
                products.removeIf(product1 -> product1.getProductID().equals(productID));
                productGroup.get().setProducts(products);
                this.productGroupRepository.save(productGroup.get());
                return;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }
}
