package com.higroup.Buda.api.business.receipt.view;

import java.util.List;

import com.higroup.Buda.entities.Receipt;
import com.higroup.Buda.repositories.ReceiptRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewReceiptService {
    private final ReceiptRepository receiptRepository;
    @Autowired
    public ViewReceiptService(ReceiptRepository receiptRepository){
        this.receiptRepository = receiptRepository;
    }
    public List<Receipt> findAllReceiptByUserID(Long userID){
        return this.receiptRepository.findAllByUserID(userID);
    }
}
