package com.higroup.Buda.api.business.receipt.delete;

import com.higroup.Buda.entities.Receipt;
import com.higroup.Buda.repositories.ReceiptRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DeleteReceiptService {
    private final ReceiptRepository receiptRepository;
    @Autowired
    public DeleteReceiptService(ReceiptRepository receiptRepository){
        this.receiptRepository = receiptRepository;
    }
    public void deleteReceiptByReceiptID(Long userID, Long receiptID){
        Receipt receipt = this.receiptRepository.findReceiptByReceiptID(receiptID);
        if ((receipt!=null) && (receipt.getUserID().equals(userID))){
            this.receiptRepository.delete(receipt);
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Receipt not found");
    }
}
