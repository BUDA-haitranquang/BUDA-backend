package com.higroup.Buda.api.business.payslip.create;

import java.time.ZonedDateTime;

import com.higroup.Buda.entities.PaySlip;
import com.higroup.Buda.repositories.PaySlipRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePaySlipService {
    private final PaySlipRepository paySlipRepository;
    @Autowired
    public CreatePaySlipService(PaySlipRepository paySlipRepository){
        this.paySlipRepository = paySlipRepository;
    }
    public PaySlip createPaySlip(Long userID, PaySlip paySlip){
        paySlip.setUserID(userID);
        paySlip.setCreationTime(ZonedDateTime.now());
        this.paySlipRepository.save(paySlip);
        return paySlip;
    }
}
