package com.higroup.Buda.api.statistics.product.best.selling;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.api.statistics.product.best.selling.BestSellingProductStatisticsRepository.ProductRanking;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.entities.enumeration.PlanType;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BestSellingProductStatisticsService {
    private final UserRepository userRepository;
    private final BestSellingProductStatisticsRepository bestSellingProductStatisticsRepository;
    private Integer basicNumber = 3, proNumber = 10;

    @Autowired
    public BestSellingProductStatisticsService(UserRepository userRepository, BestSellingProductStatisticsRepository bestSellingProductStatisticsRepository){
        this.userRepository = userRepository;
        this.bestSellingProductStatisticsRepository = bestSellingProductStatisticsRepository;
    }

    public List<ProductRanking> findNProductsTopProfit(Long userID){
        Integer number = 0;
        Optional<User> opUser = userRepository.findUserByUserID(userID);
        if(!opUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not exists");
        }
        if(opUser.get().getPlanType().equals(PlanType.BASIC)){
            number = basicNumber;
        }
        else number = proNumber;
        
        return this.bestSellingProductStatisticsRepository.findNProductsTopProfit(userID, number);
    }

    public List<ProductRanking> findNProductsTopRevenue(Long userID){
        Integer number = 0;
        Optional<User> opUser = userRepository.findUserByUserID(userID);
        if(!opUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not exists");
        }
        if(opUser.get().getPlanType().equals(PlanType.BASIC)){
            number = basicNumber;
        }
        else number = proNumber;

        return this.bestSellingProductStatisticsRepository.findNProductsTopRevenue(userID, number);
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

        return this.bestSellingProductStatisticsRepository.findNProductsTopSellNumber(userID, number);
    }


    
}
