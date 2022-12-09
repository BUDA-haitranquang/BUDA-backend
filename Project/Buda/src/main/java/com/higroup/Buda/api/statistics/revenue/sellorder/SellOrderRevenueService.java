package com.higroup.Buda.api.statistics.revenue.sellorder;

import java.time.ZonedDateTime;
import java.util.List;

import com.higroup.Buda.customDTO.PeriodDTO;
import com.higroup.Buda.customDTO.RevenueByTimeStatistics;
import com.higroup.Buda.repositories.SellOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellOrderRevenueService {
    private final SellOrderRepository sellOrderRepository;
    @Autowired
    public SellOrderRevenueService(SellOrderRepository sellOrderRepository)
    {
        this.sellOrderRepository = sellOrderRepository;
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
    public List<RevenueByTimeStatistics> findRevenueByPeriod(Long userID, PeriodDTO periodDTO){
        ZonedDateTime from = periodDTO.getFrom().withHour(0).withMinute(0).withSecond(0);
        ZonedDateTime to = periodDTO.getTo().withHour(0).withMinute(0).withSecond(0);
        return this.sellOrderRepository.findRevenueInPeriod(userID, from, to);
    }
}
