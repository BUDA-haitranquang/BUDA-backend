package com.higroup.Buda.api.statistics.gender;

import java.util.List;

import com.higroup.Buda.customDTO.GenderStatistics;
import com.higroup.Buda.repositories.SellOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderStatisticsService {
    private final SellOrderRepository sellOrderRepository;
    @Autowired
    public GenderStatisticsService(SellOrderRepository sellOrderRepository)
    {
        this.sellOrderRepository = sellOrderRepository;
    }
    public List<GenderStatistics> findTotalSpendOfGenderByUserID(Long userID)
    {
        return this.sellOrderRepository.findTotalSpendOfGenderByUserID(userID);
    }
    public List<GenderStatistics> findCurrentMonthSpendOfGenderByUserID(Long userID)
    {
        return this.sellOrderRepository.findCurrentMonthSpendOfGenderByUserID(userID);
    }
}
