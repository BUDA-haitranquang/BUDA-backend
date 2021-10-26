package com.higroup.Buda.services;

import java.time.ZonedDateTime;
import java.util.List;

import javax.print.attribute.standard.MediaSize.Other;

import com.higroup.Buda.entities.OtherCost;
import com.higroup.Buda.repositories.OtherCostRepository;
import com.higroup.Buda.util.Checker.PresentChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtherCostService {
    private final OtherCostRepository otherCostRepository;
    
    @Autowired
    public OtherCostService(OtherCostRepository otherCostRepository)
    {
        this.otherCostRepository = otherCostRepository;
    }
    @Autowired
    private PresentChecker presentChecker;
    public List<OtherCost> findAllOtherCostByUserID(Long userID)
    {
        return this.otherCostRepository.findAllByUserID(userID);
    }
    public OtherCost createNewOtherCost(Long userID, OtherCost otherCost)
    {
        otherCost.setUserID(userID);
        otherCost.setCreationTime(ZonedDateTime.now());
        this.otherCostRepository.save(otherCost);
        return otherCost;
    }
    public OtherCost findOtherCostByOtherCostID(Long otherCostID)
    {
        presentChecker.checkIdAndRepository(otherCostID, otherCostRepository);
        return this.otherCostRepository.findOtherCostByOtherCostID(otherCostID);
    }
    public OtherCost updateOtherCost(Long otherCostID, OtherCost otherCost)
    {
        presentChecker.checkIdAndRepository(otherCostID, otherCostRepository);
        this.otherCostRepository.save(otherCost);
        return otherCost;
    }
    public void deleteOtherCostByOtherCostID(Long otherCostID)
    {
        presentChecker.checkIdAndRepository(otherCostID, otherCostRepository);
        this.otherCostRepository.deleteById(otherCostID);
    }
}
