package com.higroup.Buda.services;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import javax.print.attribute.standard.MediaSize.Other;
import javax.transaction.Transactional;

import com.higroup.Buda.entities.OtherCost;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.OtherCostRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.util.Checker.PresentChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OtherCostService {
    private final OtherCostRepository otherCostRepository;
    private final UserRepository userRepository;
    
    @Autowired
    public OtherCostService(OtherCostRepository otherCostRepository, UserRepository userRepository)
    {
        this.otherCostRepository = otherCostRepository;
        this.userRepository = userRepository;
    }
    @Autowired
    private PresentChecker presentChecker;
    public List<OtherCost> findAllOtherCostByUserID(Long userID)
    {
        return this.otherCostRepository.findAllByUserID(userID);
    }
    @Transactional
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
    @Transactional
    public OtherCost updateOtherCost(Long otherCostID, OtherCost otherCost)
    {
        presentChecker.checkIdAndRepository(otherCostID, otherCostRepository);
        this.otherCostRepository.save(otherCost);
        return otherCost;
    }
    @Transactional
    public void deleteOtherCostByOtherCostID(Long userID, Long otherCostID)
    {
        OtherCost otherCost = this.otherCostRepository.findOtherCostByOtherCostID(otherCostID);
        if ((otherCost!=null) && (otherCost.getUserID() == userID))
        {
            this.otherCostRepository.delete(otherCost);
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Other cost not found");
        }
    }
    public List<OtherCost> findAllOtherCostByUserIDLastXDays(Long userID, Long X)
    {
        return this.otherCostRepository.findAllOtherCostByUserIDLastXDays(userID, X);
    }
    public List<OtherCost> findAllIncompletedOtherCostByUserID(Long userID)
    {
        return this.otherCostRepository.findAllIncompletedOtherCostByUserID(userID);
    }
}
