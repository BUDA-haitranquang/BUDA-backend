package com.higroup.Buda.api.product.group.view;

import java.util.List;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductGroup;
import com.higroup.Buda.repositories.ProductGroupRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    
    public List<Product> findAllProductByProductGroup(Long userID, Long productGroupID)
    {
        return this.productRepository.findAllProductByProductGroup(productGroupID, userID);
    }
}