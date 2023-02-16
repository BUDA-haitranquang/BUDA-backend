package com.higroup.Buda.api.product.group.delete;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.higroup.Buda.entities.ProductGroup;
import com.higroup.Buda.repositories.ProductGroupRepository;

@Service
public class ProductGroupDeleteService {
    private final ProductGroupRepository productGroupRepository;

    @Autowired
    public ProductGroupDeleteService(ProductGroupRepository productGroupRepository) {
        this.productGroupRepository = productGroupRepository;
    }
    @Transactional
    public void deleteProductGroup(Long userID, Long productGroupID)
    {
        Optional<ProductGroup> productGroup = this.productGroupRepository.findProductGroupByProductGroupID(productGroupID);
        if (productGroup.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product group not found");
        }
        this.productGroupRepository.delete(productGroup.get());
    }


}