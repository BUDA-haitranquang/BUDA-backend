package com.higroup.Buda.api.business.payslip.view;

import java.util.List;

import com.higroup.Buda.entities.PaySlip;
import com.higroup.Buda.repositories.PaySlipRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public PaySlip findPaySlipByPaySlipID(Long userID, Long paySlipID){
        PaySlip paySlip = this.paySlipRepository.findPaySlipByPaySlipID(paySlipID);
        if ((paySlip!=null) && (paySlip.getUserID().equals(userID))){
            return paySlip;
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pay Slip not found");
    }
}
