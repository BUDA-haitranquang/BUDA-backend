package com.higroup.Buda.api.business.payslip.delete;

import com.higroup.Buda.entities.PaySlip;
import com.higroup.Buda.repositories.PaySlipRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DeletePaySlipService {
    private final PaySlipRepository paySlipRepository;
    @Autowired
    public DeletePaySlipService(PaySlipRepository paySlipRepository){
        this.paySlipRepository = paySlipRepository;
    }
    public void deletePaySlipByPaySlipID(Long userID, Long paySlipID){
        PaySlip paySlip = this.paySlipRepository.findPaySlipByPaySlipID(paySlipID);
        if ((paySlip!=null) && (paySlip.getUserID().equals(userID))){
            this.paySlipRepository.delete(paySlip);
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pay Slip not found");
    }
}
