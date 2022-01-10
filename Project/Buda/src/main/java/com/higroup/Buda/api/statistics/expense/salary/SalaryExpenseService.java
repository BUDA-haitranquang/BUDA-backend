package com.higroup.Buda.api.statistics.expense.salary;

import java.util.List;

import com.higroup.Buda.customDTO.ExpenseByTimeStatistics;
import com.higroup.Buda.repositories.SalaryLogRepository;

import org.springframework.stereotype.Service;

@Service
public class SalaryExpenseService {
    private final SalaryLogRepository salaryLogRepository;
    public SalaryExpenseService(SalaryLogRepository salaryLogRepository){
        this.salaryLogRepository = salaryLogRepository;
    }
    public List<ExpenseByTimeStatistics> findSalaryLogExpenseCurrentMonth(Long userID)
    {
        return this.salaryLogRepository.findSalaryLogExpenseCurrentMonth(userID);
    }
    public List<ExpenseByTimeStatistics> findSalaryLogExpenseGroupByMonth(Long userID)
    {
        return this.salaryLogRepository.findSalaryLogExpenseGroupByMonth(userID);
    }
}
