package com.higroup.Buda.api.statistics.customer.retention;

import com.higroup.Buda.repositories.SellOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetentionCustomerRateStatsService {
    private final SellOrderRepository sellOrderRepository;
    @Autowired
    public RetentionCustomerRateStatsService(SellOrderRepository sellOrderRepository)
    {
        this.sellOrderRepository = sellOrderRepository;
    }
    public Double findRepeatRateGroupByWeek(Long userID)
    {
        Long total = this.sellOrderRepository.findTotalCountSellOrderByUserID(userID);
        Long regular = this.sellOrderRepository.findCurrentWeekCountSellOrderByUserID(userID);
        return Double.valueOf(regular) / Double.valueOf(total);
    }
    public Double findRepeatRateGroupByMonth(Long userID)
    {
        Long total = this.sellOrderRepository.findTotalCountSellOrderByUserID(userID);
        Long regular = this.sellOrderRepository.findCurrentMonthCountSellOrderByUserID(userID);
        return Double.valueOf(regular) / Double.valueOf(total);
    }
}
