package com.higroup.Buda.api.statistics.product.overall;

import java.util.List;

import com.higroup.Buda.customDTO.ProductStatistics;
import com.higroup.Buda.repositories.SellOrderItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductStatisticsService {
    private final SellOrderItemRepository sellOrderItemRepository;
    @Autowired
    public ProductStatisticsService(SellOrderItemRepository sellOrderItemRepository)
    {
        this.sellOrderItemRepository = sellOrderItemRepository;
    }
    public List<ProductStatistics> findTotalRevenueOfAllProductByUserID(Long userID)
    {
        return this.sellOrderItemRepository.findTotalRevenueOfAllProductByUserID(userID);
    }
}
