package com.higroup.Buda.api.business.receipt.create;

import java.time.ZonedDateTime;

import com.higroup.Buda.entities.Receipt;
import com.higroup.Buda.repositories.ReceiptRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateReceiptService {
    private final ReceiptRepository receiptRepository;
    @Autowired
    public CreateReceiptService(ReceiptRepository receiptRepository){
        this.receiptRepository = receiptRepository;
    }
    public Receipt createNewReceipt(Long userID, Receipt receipt){
        receipt.setUserID(userID);
        receipt.setCreationTime(ZonedDateTime.now());
        this.receiptRepository.save(receipt);
        return receipt;
    }
}
