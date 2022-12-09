package com.higroup.Buda.api.business.receipt.view;

import java.util.List;

import com.higroup.Buda.entities.Receipt;
import com.higroup.Buda.repositories.ReceiptRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public Receipt findReceiptByReceiptID(Long userID, Long receiptID){
        Receipt receipt = this.receiptRepository.findReceiptByReceiptID(receiptID);
        if ((receipt!=null) && (receipt.getUserID().equals(userID))){
            return receipt;
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Receipt not found");
    }
}
