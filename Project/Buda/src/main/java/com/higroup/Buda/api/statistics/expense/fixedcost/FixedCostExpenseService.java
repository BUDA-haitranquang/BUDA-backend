package com.higroup.Buda.api.statistics.expense.fixedcost;

import java.util.List;

import com.higroup.Buda.customDTO.ExpenseByTimeStatistics;
import com.higroup.Buda.repositories.FixedCostBillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class FixedCostExpenseService {
    private final FixedCostBillRepository fixedCostBillRepository;
    @Autowired
    public FixedCostExpenseService(FixedCostBillRepository fixedCostBillRepository)
    {
        this.fixedCostBillRepository = fixedCostBillRepository;
    }
   
    public List<ExpenseByTimeStatistics> findFixedCostBillExpenseByWeek(Long userID)
    {
        return this.fixedCostBillRepository.findFixedCostBillExpenseByWeek(userID);
    }
    public List<ExpenseByTimeStatistics> findFixedCostBillExpenseCurrentMonth(Long userID)
    {
        return this.fixedCostBillRepository.findFixedCostBillExpenseCurrentMonth(userID);
    }
    public List<ExpenseByTimeStatistics> findFixedCostBillExpenseGroupByMonth(Long userID)
    {
        return this.fixedCostBillRepository.findFixedCostBillExpenseGroupByMonth(userID);
    }
}
