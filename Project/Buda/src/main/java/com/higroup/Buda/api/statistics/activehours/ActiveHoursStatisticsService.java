package com.higroup.Buda.api.statistics.activehours;

import com.higroup.Buda.customDTO.ActiveHoursStatistics;
import com.higroup.Buda.repositories.SellOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiveHoursStatisticsService {
    private final SellOrderRepository sellOrderRepository;
    @Autowired
    public ActiveHoursStatisticsService(SellOrderRepository sellOrderRepository)
    {
        this.sellOrderRepository = sellOrderRepository;
    }
    public List<ActiveHoursStatistics> findTotalCountGroupByHours(Long userID)
    {
        return this.sellOrderRepository.findTotalCountGroupByHours(userID);
    }
    public List<ActiveHoursStatistics> findCurrentMonthCountGroupByHours(Long userID)
    {
        return this.sellOrderRepository.findCurrentMonthCountGroupByHours(userID);
    }
}
