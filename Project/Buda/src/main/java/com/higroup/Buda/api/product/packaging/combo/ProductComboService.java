package com.higroup.Buda.api.product.packaging.combo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.higroup.Buda.entities.ProductCombo;
import com.higroup.Buda.repositories.ProductComboRepository;

@Service
public class ProductComboService {
    private final ProductComboRepository productComboRepository;
    @Autowired
    public ProductComboService(ProductComboRepository productComboRepository)
    {
        this.productComboRepository = productComboRepository;
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
