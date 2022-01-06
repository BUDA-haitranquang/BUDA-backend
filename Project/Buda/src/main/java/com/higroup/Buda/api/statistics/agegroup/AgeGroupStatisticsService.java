package com.higroup.Buda.api.statistics.agegroup;

import java.util.List;

import com.higroup.Buda.customDTO.AgeGroupStatistics;
import com.higroup.Buda.repositories.SellOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgeGroupStatisticsService {
    private final SellOrderRepository sellOrderRepository;
    @Autowired
    public AgeGroupStatisticsService(SellOrderRepository sellOrderRepository)
    {
        this.sellOrderRepository = sellOrderRepository;
    }
    public List<AgeGroupStatistics> findTotalSpendOfAgeGroupByUserID(Long userID)
    {
        return this.sellOrderRepository.findTotalSpendOfAgeGroupByUserID(userID);
    }
    public List<AgeGroupStatistics> findCurrentMonthSpendOfAgeGroupByUserID(Long userID)
    {
        return this.sellOrderRepository.findCurrentMonthSpendOfAgeGroupByUserID(userID);
    }
}
