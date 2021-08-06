package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.FixedCost;
import com.higroup.Buda.entities.FixedCostBill;
import com.higroup.Buda.repositories.FixedCostBillRepository;
import com.higroup.Buda.repositories.FixedCostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class FixedCostBillService {
    private final FixedCostBillRepository fixedCostBillRepository;
    private final FixedCostRepository fixedCostRepository;
    @Autowired
    public FixedCostBillService(FixedCostBillRepository fixedCostBillRepository, FixedCostRepository fixedCostRepository)
    {
        this.fixedCostRepository = fixedCostRepository;
        this.fixedCostBillRepository = fixedCostBillRepository;
    }
    public List<FixedCostBill> findAllByFixedCostID(Long fixedCostID)
    {
        Optional<FixedCost> fixedCost = this.fixedCostRepository.findFixedCostByFixedCostID(fixedCostID);
        if (fixedCost.isPresent())
        {
            return this.fixedCostBillRepository.findAllByFixedCost(fixedCost.get());
        }
        return null;
    }
    public List<FixedCostBill> findAllByUserID(Long userID)
    {
        return this.findAllByUserID(userID);
    }
}
