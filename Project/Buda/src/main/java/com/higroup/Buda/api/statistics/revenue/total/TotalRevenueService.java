package com.higroup.Buda.api.statistics.revenue.total;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TotalRevenueService {
    private TotalRevenueRepository totalRevenueRepository;
    @Autowired
    public TotalRevenueService(TotalRevenueRepository totalRevenueRepository){
        this.totalRevenueRepository = totalRevenueRepository;
    }
    public List<?> findTotalRevenueEveryDay(Long userID){
        return this.totalRevenueRepository.findTotalRevenueEveryDay(userID);
    }
    public List<?> findTotalRevenueLastXDays(Long userID, Long X){
        return this.totalRevenueRepository.findTotalRevenueLastXDays(userID, X);
    }
}
