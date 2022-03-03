package com.higroup.Buda.api.statistics.product.worst.returning;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.User;
import com.higroup.Buda.entities.enumeration.PlanType;
import com.higroup.Buda.repositories.SellOrderItemRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.repositories.SellOrderItemRepository.ProductRanking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WorstReturnProductStatisticsService {
    private final UserRepository userRepository;
    private final SellOrderItemRepository sellOrderItemRepository;
    private Integer basicNumber = 3, proNumber = 10;

    @Autowired
    public WorstReturnProductStatisticsService(UserRepository userRepository, SellOrderItemRepository sellOrderItemRepository){
        this.userRepository = userRepository;
        this.sellOrderItemRepository = sellOrderItemRepository;
    }

    public List<ProductRanking> findNProductsMostReturnNumber(Long userID){
        Integer number = 0;
        Optional<User> opUser = userRepository.findUserByUserID(userID);
        if(!opUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not exists");
        }
        if(opUser.get().getPlanType().equals(PlanType.BASIC)){
            number = basicNumber;
        }
        else number = proNumber;

        return this.sellOrderItemRepository.findNProductsMostReturnNumber(userID, number);
    }

    public List<ProductRanking> findNProductsMostReturnPrice(Long userID){
        Integer number = 0;
        Optional<User> opUser = userRepository.findUserByUserID(userID);
        if(!opUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not exists");
        }
        if(opUser.get().getPlanType().equals(PlanType.BASIC)){
            number = basicNumber;
        }
        else number = proNumber;

        return this.sellOrderItemRepository.findNProductsMostReturnPrice(userID, number);
    }
}
