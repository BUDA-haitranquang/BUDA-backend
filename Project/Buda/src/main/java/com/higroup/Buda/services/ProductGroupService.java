package com.higroup.Buda.services;

import java.util.List;

import com.higroup.Buda.entities.ProductGroup;
import com.higroup.Buda.repositories.ProductGroupRepository;
import com.higroup.Buda.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupService {
    private final ProductGroupRepository productGroupRepository;
    private final ProductRepository productRepository;
    @Autowired
    public ProductGroupService(ProductGroupRepository productGroupRepository, ProductRepository productRepository) {
        this.productGroupRepository = productGroupRepository;
        this.productRepository = productRepository;
    }
    public List<ProductGroup> findAllByUserID(Long userID)
    {
        return this.productGroupRepository.findAllByUserID(userID);
    }
}
