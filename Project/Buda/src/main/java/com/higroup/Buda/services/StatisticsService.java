package com.higroup.Buda.services;

import java.util.List;

import com.higroup.Buda.customDTO.AgeGroupStatistics;
import com.higroup.Buda.customDTO.ExpenseByTimeStatistics;
import com.higroup.Buda.customDTO.GenderStatistics;
import com.higroup.Buda.customDTO.ProductStatistics;
import com.higroup.Buda.customDTO.RevenueByTimeStatistics;
import com.higroup.Buda.repositories.SellOrderItemRepository;
import com.higroup.Buda.repositories.SellOrderRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {
    private final UserRepository userRepository;
    private final SellOrderRepository sellOrderRepository;
    private final SellOrderItemRepository sellOrderItemRepository;
    @Autowired
    public StatisticsService(UserRepository userRepository, SellOrderRepository sellOrderRepository, SellOrderItemRepository sellOrderItemRepository)
    {
        this.sellOrderItemRepository = sellOrderItemRepository;
        this.userRepository = userRepository;
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
    public List<GenderStatistics> findTotalSpendOfGenderByUserID(Long userID)
    {
        return this.sellOrderRepository.findTotalSpendOfGenderByUserID(userID);
    }
    public List<GenderStatistics> findCurrentMonthSpendOfGenderByUserID(Long userID)
    {
        return this.sellOrderRepository.findCurrentMonthSpendOfGenderByUserID(userID);
    }
    public List<ProductStatistics> findTotalRevenueOfAllProductByUserID(Long userID)
    {
        return this.sellOrderItemRepository.findTotalRevenueOfAllProductByUserID(userID);
    }
    public List<RevenueByTimeStatistics> findRevenueGroupByMonth(Long userID)
    {
        return this.sellOrderRepository.findRevenueGroupByMonth(userID);
    }
    public List<RevenueByTimeStatistics> findRevenueGroupByWeek(Long userID)
    {
        return this.sellOrderRepository.findRevenueGroupByWeek(userID);
    }
    public List<RevenueByTimeStatistics> findRevenueGroupByWeekday(Long userID)
    {
        return this.sellOrderRepository.findRevenueGroupByWeekday(userID);
    }
    public List<RevenueByTimeStatistics> findRevenueAllDaysCurrentMonth(Long userID)
    {
        return this.sellOrderRepository.findRevenueAllDaysCurrentMonth(userID);
    }
}
