package com.higroup.Buda.api.statistics.expense.payslip;

import java.util.List;

import com.higroup.Buda.customDTO.ExpenseByTimeStatistics;
import com.higroup.Buda.repositories.PaySlipRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaySlipExpenseService {
    private final PaySlipRepository paySlipRepository;
    @Autowired
    public PaySlipExpenseService(PaySlipRepository paySlipRepository){
        this.paySlipRepository = paySlipRepository;
    }
    public List<ExpenseByTimeStatistics> findPaySlipExpenseByWeek(Long userID){
        return this.paySlipRepository.findPaySlipExpenseByWeek(userID);
    }
    public List<ExpenseByTimeStatistics> findPaySlipExpenseCurrentMonth(Long userID){
        return this.paySlipRepository.findPaySlipExpenseCurrentMonth(userID);
    }
    public List<ExpenseByTimeStatistics> findPaySlipExpenseGroupByMonth(Long userID){
        return this.paySlipRepository.findPaySlipExpenseGroupByMonth(userID);
    }
}
