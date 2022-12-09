package com.higroup.Buda.api.statistics.revenue.total;

import java.util.List;

import com.higroup.Buda.customDTO.PeriodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TotalRevenueService {
    private final TotalRevenueRepository totalRevenueRepository;
    @Autowired
    public TotalRevenueService(TotalRevenueRepository totalRevenueRepository){
        this.totalRevenueRepository = totalRevenueRepository;
    }
    public List<?> findTotalRevenueEveryDay(Long userID){
        return this.totalRevenueRepository.findTotalRevenueEveryDay(userID);
    }
    public List<?> findTotalRevenueEveryWeek(Long userID)
    {
        return this.totalRevenueRepository.findTotalRevenueEveryWeek(userID);
    }
    public List<?> findTotalRevenueEveryMonth(Long userID)
    {
        return this.totalRevenueRepository.findTotalRevenueEveryMonth(userID);
    }
    public List<?> findTotalRevenueEveryYear(Long userID)
    {
        return this.totalRevenueRepository.findTotalRevenueEveryYear(userID);
    }
    public List<?> findTotalRevenueLastXDays(Long userID, Long X){
        return this.totalRevenueRepository.findTotalRevenueLastXDays(userID, X);
    }
    public List<?> findTotalRevenuePeriod(Long userID, PeriodDTO periodDTO) {
        return this.totalRevenueRepository.findTotalRevenuePeriod(userID, periodDTO.getFrom(), periodDTO.getTo());
    }
}
