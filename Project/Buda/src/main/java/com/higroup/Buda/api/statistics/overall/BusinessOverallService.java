package com.higroup.Buda.api.statistics.overall;

import com.higroup.Buda.customDTO.PeriodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessOverallService {
    private final BusinessOverallRepository totalProfitRepository;
    @Autowired
    public BusinessOverallService(BusinessOverallRepository totalProfitRepository){
        this.totalProfitRepository = totalProfitRepository;
    }
    public List<?> findTotalProfitEveryDay(Long userID){
        return this.totalProfitRepository.findTotalProfitEveryDay(userID);
    }
    public List<?> findTotalProfitEveryWeek(Long userID)
    {
        return this.totalProfitRepository.findTotalProfitEveryWeek(userID);
    }
    public List<?> findTotalProfitEveryMonth(Long userID)
    {
        return this.totalProfitRepository.findTotalProfitEveryMonth(userID);
    }
    public List<?> findTotalProfitEveryYear(Long userID)
    {
        return this.totalProfitRepository.findTotalProfitEveryYear(userID);
    }
    public List<?> findTotalProfitLastXDays(Long userID, Long X){
        return this.totalProfitRepository.findTotalProfitLastXDays(userID, X);
    }
    public List<?> findTotalProfitPeriod(Long userID, PeriodDTO periodDTO) {
        return this.totalProfitRepository.findTotalProfitPeriod(userID, periodDTO.getFrom(), periodDTO.getTo());
    }
}
