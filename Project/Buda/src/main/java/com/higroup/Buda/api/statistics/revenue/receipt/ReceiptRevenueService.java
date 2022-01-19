package com.higroup.Buda.api.statistics.revenue.receipt;

import java.util.List;

import com.higroup.Buda.customDTO.RevenueByTimeStatistics;
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
    public List<RevenueByTimeStatistics> findReceiptRevenueCurrentMonth(Long userID){
        return this.receiptRepository.findReceiptRevenueCurrentMonth(userID);
    }
    public List<RevenueByTimeStatistics> findReceiptRevenueGroupByMonth(Long userID){
        return this.receiptRepository.findReceiptRevenueGroupByMonth(userID);
    }
    public List<RevenueByTimeStatistics> findReceiptRevenueGroupByWeek(Long userID){
        return this.receiptRepository.findReceiptRevenueByWeek(userID);
    }
}
