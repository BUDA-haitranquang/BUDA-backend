package com.higroup.Buda.api.product.group.view;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductGroup;
import com.higroup.Buda.repositories.ProductGroupRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ViewProductGroupService {
    private final ProductGroupRepository productGroupRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ViewProductGroupService(ProductGroupRepository productGroupRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.productGroupRepository = productGroupRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }
    public List<ProductGroup> findAllByUserID(Long userID)
    {
        return this.productGroupRepository.findAllByUserID(userID);
    }
    
    public ProductGroup findProductGroupByProductGroupID(Long userID, Long productGroupID) {
        Optional<ProductGroup> productGroupOptional = this.productGroupRepository.findProductGroupByProductGroupID(productGroupID);
        if ((productGroupOptional.isPresent()) && (productGroupOptional.get().getUserID().equals(userID))) {
            return productGroupOptional.get();
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product Group not found");
    }
    public List<Product> findAllProductByProductGroup(Long userID, Long productGroupID)
    {
        return this.productRepository.findAllProductByProductGroup(productGroupID, userID);
    }
    public List<ProductGroup> findAllProductGroupByProductID(Long userID, Long productID){
        return this.productGroupRepository.findAllByProduct(userID, productID);
    }
}