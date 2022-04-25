package com.higroup.Buda.api.statistics.product.productgroup;

import com.higroup.Buda.entities.ProductGroup;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.ProductGroupRepository;
import com.higroup.Buda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProductGroupStatisticsService {
    private final ProductGroupStatisticsRepository productGroupStatisticsRepository;
    private final ProductGroupRepository productGroupRepository;
    private final UserRepository userRepository;

    @Autowired

    public ProductGroupStatisticsService(ProductGroupStatisticsRepository productGroupStatisticsRepository, ProductGroupRepository productGroupRepository, UserRepository userRepository) {
        this.productGroupStatisticsRepository = productGroupStatisticsRepository;
        this.productGroupRepository = productGroupRepository;
        this.userRepository = userRepository;
    }


    public Double findTotalRevenueFromProductGroup(Long userID, Long productGroupID) {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Optional<ProductGroup> productGroup = this.productGroupRepository.findProductGroupByProductGroupID(productGroupID);
        if (productGroup.isEmpty() || !productGroup.get().getUserID().equals(userID))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product group not found.");
        }
        return this.productGroupStatisticsRepository.findTotalRevenueFromProductGroup(userID, productGroupID);
    }
}
