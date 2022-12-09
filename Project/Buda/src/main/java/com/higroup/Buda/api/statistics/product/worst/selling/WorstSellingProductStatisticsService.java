package com.higroup.Buda.api.statistics.product.worst.selling;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.api.statistics.product.worst.selling.WorstSellingProductStatisticsRepository.ProductRanking;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.entities.enumeration.PlanType;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WorstSellingProductStatisticsService {
    private final UserRepository userRepository;
    private final WorstSellingProductStatisticsRepository worstSellingProductStatisticsRepository;
    // private final SellOrderItemRepository sellOrderItemRepository;
    private Integer basicNumber = 3, proNumber = 10;

    @Autowired
    public WorstSellingProductStatisticsService(UserRepository userRepository, WorstSellingProductStatisticsRepository worstSellingProductStatisticsRepository){
        this.userRepository = userRepository;
        this.worstSellingProductStatisticsRepository = worstSellingProductStatisticsRepository;
    }

    public List<ProductRanking> findNProductsLeastProfit(Long userID){
        Integer number = 0;
        Optional<User> opUser = userRepository.findUserByUserID(userID);
        if(!opUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not exists");
        }
        if(opUser.get().getPlanType().equals(PlanType.BASIC)){
            number = basicNumber;
        }
        else number = proNumber;

        return this.worstSellingProductStatisticsRepository.findNProductsLeastProfit(userID, number);
    }

    public List<ProductRanking> findNProductsLeastRevenue(Long userID){
        Integer number = 0;
        Optional<User> opUser = userRepository.findUserByUserID(userID);
        if(!opUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not exists");
        }
        if(opUser.get().getPlanType().equals(PlanType.BASIC)){
            number = basicNumber;
        }
        else number = proNumber;

        return this.worstSellingProductStatisticsRepository.findNProductsLeastRevenue(userID, number);
    }

    public List<ProductRanking> findNProductsTopSellNumber(Long userID){
        Integer number = 0;
        Optional<User> opUser = userRepository.findUserByUserID(userID);
        if(!opUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not exists");
        }
        if(opUser.get().getPlanType().equals(PlanType.BASIC)){
            number = basicNumber;
        }
        else number = proNumber;

        return this.worstSellingProductStatisticsRepository.findNProductsLeastSellNumber(userID, number);
    }
}
