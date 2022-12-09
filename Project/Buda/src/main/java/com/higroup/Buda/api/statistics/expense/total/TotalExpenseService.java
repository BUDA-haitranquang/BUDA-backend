package com.higroup.Buda.api.statistics.expense.total;

import com.higroup.Buda.customDTO.PeriodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TotalExpenseService {
    private final TotalExpenseRepository totalExpenseRepository;
    @Autowired
    public TotalExpenseService(TotalExpenseRepository totalExpenseRepository){
        this.totalExpenseRepository = totalExpenseRepository;
    }
    public List<?> findTotalExpenseEveryDay(Long userID){
        return this.totalExpenseRepository.findTotalExpenseEveryDay(userID);
    }
    public List<?> findTotalExpenseEveryWeek(Long userID)
    {
        return this.totalExpenseRepository.findTotalExpenseEveryWeek(userID);
    }
    public List<?> findTotalExpenseEveryMonth(Long userID)
    {
        return this.totalExpenseRepository.findTotalExpenseEveryMonth(userID);
    }
    public List<?> findTotalExpenseEveryYear(Long userID)
    {
        return this.totalExpenseRepository.findTotalExpenseEveryYear(userID);
    }
    public List<?> findTotalExpenseLastXDays(Long userID, Long X){
        return this.totalExpenseRepository.findTotalExpenseLastXDays(userID, X);
    }
    public List<?> findTotalExpensePeriod(Long userID, PeriodDTO periodDTO) {
        return this.totalExpenseRepository.findTotalExpensePeriod(userID, periodDTO.getFrom(), periodDTO.getTo());
    }
}
