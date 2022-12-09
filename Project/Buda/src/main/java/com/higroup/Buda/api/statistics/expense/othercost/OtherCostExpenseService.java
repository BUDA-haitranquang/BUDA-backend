package com.higroup.Buda.api.statistics.expense.othercost;

import java.util.List;

import com.higroup.Buda.customDTO.ExpenseByTimeStatistics;
import com.higroup.Buda.repositories.OtherCostRepository;

import org.springframework.stereotype.Service;

@Service
public class OtherCostExpenseService {
    private final OtherCostRepository otherCostRepository;
    public OtherCostExpenseService(OtherCostRepository otherCostRepository){
        this.otherCostRepository = otherCostRepository;
    }
    public List<ExpenseByTimeStatistics> findOtherCostExpenseByWeek(Long userID)
    {
        return this.otherCostRepository.findOtherCostExpenseByWeek(userID);
    }
    public List<ExpenseByTimeStatistics> findOtherCostExpenseCurrentMonth(Long userID)
    {
        return this.otherCostRepository.findOtherCostExpenseCurrentMonth(userID);
    }
    public List<ExpenseByTimeStatistics> findOtherCostExpenseGroupByMonth(Long userID)
    {
        return this.otherCostRepository.findOtherCostExpenseGroupByMonth(userID);
    }
}
