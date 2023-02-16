package com.higroup.Buda.api.statistics.product.productgroup;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.higroup.Buda.entities.ProductGroup;
import com.higroup.Buda.repositories.ProductGroupRepository;

@Service
public class ProductGroupStatisticsService {
    private final ProductGroupStatisticsRepository productGroupStatisticsRepository;
    private final ProductGroupRepository productGroupRepository;

    @Autowired

    public ProductGroupStatisticsService(ProductGroupStatisticsRepository productGroupStatisticsRepository, ProductGroupRepository productGroupRepository) {
        this.productGroupStatisticsRepository = productGroupStatisticsRepository;
        this.productGroupRepository = productGroupRepository;
    }


    public Double findTotalRevenueFromProductGroup(Long userID, Long productGroupID) {
        Optional<ProductGroup> productGroup = this.productGroupRepository.findProductGroupByProductGroupID(productGroupID);
        if (productGroup.isEmpty() || !productGroup.get().getUserID().equals(userID))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product group not found.");
        }
        return this.productGroupStatisticsRepository.findTotalRevenueFromProductGroup(userID, productGroupID);
    }
}
