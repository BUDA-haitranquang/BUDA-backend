package com.higroup.Buda.api.statistics.expense.buyorder;

import java.util.List;

import com.higroup.Buda.customDTO.ExpenseByTimeStatistics;
import com.higroup.Buda.repositories.BuyOrderRepository;

import org.springframework.stereotype.Service;
@Service
public class BuyOrderExpenseService {
    private final BuyOrderRepository buyOrderRepository;
    public BuyOrderExpenseService(BuyOrderRepository buyOrderRepository)
    {
        this.buyOrderRepository = buyOrderRepository;
    }
    public List<ExpenseByTimeStatistics> findBuyOrderExpenseByWeek(Long userID)
    {
        return this.buyOrderRepository.findBuyOrderExpenseByWeek(userID);
    }

    public List<ExpenseByTimeStatistics> findBuyOrderExpenseCurrentMonth(Long userID)
    {
        return this.buyOrderRepository.findBuyOrderExpenseCurrentMonth(userID);
    }

    public List<ExpenseByTimeStatistics> findBuyOrderExpenseGroupByMonth(Long userID)
    {
        return this.buyOrderRepository.findBuyOrderExpenseGroupByMonth(userID);
    }
}
