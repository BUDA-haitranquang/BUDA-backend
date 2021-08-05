package com.higroup.Buda.services;

import com.higroup.Buda.repositories.SalaryLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryLogService {
    private SalaryLogRepository salaryLogRepository;
    @Autowired
    public SalaryLogService(SalaryLogRepository salaryLogRepository)
    {
        this.salaryLogRepository = salaryLogRepository;
    }
}
