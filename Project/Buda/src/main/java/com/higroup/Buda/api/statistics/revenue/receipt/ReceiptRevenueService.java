package com.higroup.Buda.api.statistics.revenue.receipt;

import com.higroup.Buda.repositories.ReceiptRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptRevenueService {
    private final ReceiptRepository receiptRepository;
    @Autowired
    public ReceiptRevenueService(ReceiptRepository receiptRepository){
        this.receiptRepository = receiptRepository;
    }
}
