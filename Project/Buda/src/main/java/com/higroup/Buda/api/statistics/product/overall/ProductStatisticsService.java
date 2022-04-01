package com.higroup.Buda.api.statistics.product.overall;

import java.util.List;

import com.higroup.Buda.customDTO.ProductStatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductStatisticsService {
    private final ProductStatisticsRepository productStatisticsRepository;
    @Autowired
    public ProductStatisticsService(ProductStatisticsRepository productStatisticsRepository)
    {
        this.productStatisticsRepository = productStatisticsRepository;
    }
    public List<ProductStatistics> findTotalRevenueOfAllProductByUserID(Long userID)
    {
        return this.productStatisticsRepository.findTotalRevenueOfAllProductByUserID(userID);
    }
}
