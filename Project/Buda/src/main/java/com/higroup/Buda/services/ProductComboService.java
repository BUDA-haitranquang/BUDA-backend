package com.higroup.Buda.services;

import java.util.List;

import com.higroup.Buda.entities.ProductCombo;
import com.higroup.Buda.repositories.ProductComboRepository;
import com.higroup.Buda.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductComboService {
    private final ProductComboRepository productComboRepository;
    private final ProductRepository productRepository;
    @Autowired
    public ProductComboService(ProductComboRepository productComboRepository, ProductRepository productRepository)
    {
        this.productComboRepository = productComboRepository;
        this.productRepository = productRepository;
    }
    public List<ProductCombo> findAllByUserID(Long userID)
    {
        return this.productComboRepository.findAllByUserID(userID);
    }
    public List<ProductCombo> findAllProductComboIncludeProduct(Long userID, Long productID)
    {
        return this.productComboRepository.findAllProductComboIncludeProduct(userID, productID);
    }
}
