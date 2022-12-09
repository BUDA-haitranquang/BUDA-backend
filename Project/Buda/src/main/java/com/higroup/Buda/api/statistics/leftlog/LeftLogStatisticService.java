package com.higroup.Buda.api.statistics.leftlog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.higroup.Buda.repositories.IngredientLeftLogRepository;
import com.higroup.Buda.repositories.ProductLeftLogRepository;
import com.higroup.Buda.repositories.IngredientLeftLogRepository.IngredientLeftLogRemoveAmount;
import com.higroup.Buda.repositories.ProductLeftLogRepository.ProductLeftLogRemoveAmount;

@Service
public class LeftLogStatisticService {

    private ProductLeftLogRepository productLeftLogRepository;
    private IngredientLeftLogRepository ingredientLeftLogRepository;

    @Autowired
    public LeftLogStatisticService(ProductLeftLogRepository productLeftLogRepository, IngredientLeftLogRepository ingredientLeftLogRepository){
        this.productLeftLogRepository = productLeftLogRepository; 
        this.ingredientLeftLogRepository = ingredientLeftLogRepository;
    }

    public List<ProductLeftLogRemoveAmount> getMostRemovedProduct(Long userID){
        return this.productLeftLogRepository.getMostRemovedProduct(userID);
    }

    public List<IngredientLeftLogRemoveAmount> getMostRemovedIngredient(Long userID){
        return this.ingredientLeftLogRepository.getMostRemovedIngredient(userID);
    }
}
