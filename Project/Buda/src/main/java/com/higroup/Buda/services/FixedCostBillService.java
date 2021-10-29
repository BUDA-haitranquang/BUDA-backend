package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.FixedCost;
import com.higroup.Buda.entities.FixedCostBill;
import com.higroup.Buda.repositories.FixedCostBillRepository;
import com.higroup.Buda.repositories.FixedCostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
    public List<FixedCostBill> findAllByFixedCostID(Long userID, Long fixedCostID)
    {
        Optional<FixedCost> fixedCost = this.fixedCostRepository.findFixedCostByFixedCostID(fixedCostID);
        if ((fixedCost.isPresent()) && (fixedCost.get().getUserID() == userID))
        {   
            return this.fixedCostBillRepository.findAllByFixedCost(fixedCost.get());
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fixed Cost ID does not exist");
    }
    public List<FixedCostBill> findAllByUserID(Long userID)
    {
        return fixedCostBillRepository.findAllByUserID(userID);
    }
}
