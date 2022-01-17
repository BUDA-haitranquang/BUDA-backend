package com.higroup.Buda.api.business.payslip.view;

import java.util.List;

import com.higroup.Buda.entities.PaySlip;
import com.higroup.Buda.repositories.PaySlipRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewPaySlipService {
    private final PaySlipRepository paySlipRepository;
    @Autowired
    public ViewPaySlipService(PaySlipRepository paySlipRepository){
        this.paySlipRepository = paySlipRepository;
    }
    public List<PaySlip> findAllPaySlipByUserID(Long userID){
        return this.paySlipRepository.findAllPaySlipByUserID(userID);
    }
}
