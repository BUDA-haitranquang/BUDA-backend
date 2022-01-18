package com.higroup.Buda.api.statistics.expense.payslip;

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
}
