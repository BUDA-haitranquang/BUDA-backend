package com.higroup.Buda.api.cost.othercost.crud;

import java.time.ZonedDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.higroup.Buda.entities.OtherCost;
import com.higroup.Buda.repositories.OtherCostRepository;
import com.higroup.Buda.util.Checker.PresentChecker;

@Service
public class CRUDOtherCostService {
    private final OtherCostRepository otherCostRepository;
    
    @Autowired
    public CRUDOtherCostService(OtherCostRepository otherCostRepository)
    {
        this.otherCostRepository = otherCostRepository;
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
    public OtherCost updateOtherCost(Long userID, OtherCost otherCost)
    {
        OtherCost oldOtherCost = this.otherCostRepository.findOtherCostByOtherCostID(otherCost.getOtherCostID());
        if ((oldOtherCost!=null) && (oldOtherCost.getUserID().equals(userID)))
        {
            otherCost.setUserID(userID);
            this.otherCostRepository.save(otherCost);
            return otherCost;
        }
        else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }
    @Transactional
    public OtherCost hideOtherCost(Long userID, Long otherCostID)
    {
        OtherCost otherCost = this.otherCostRepository.findOtherCostByOtherCostID(otherCostID);
        if ((otherCost!=null) && (otherCost.getUserID().equals(userID)))
        {
            otherCost.setVisible(false);
            this.otherCostRepository.save(otherCost);
            return otherCost;
        }
        else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }
    @Transactional
    public void deleteOtherCostByOtherCostID(Long userID, Long otherCostID)
    {
        OtherCost otherCost = this.otherCostRepository.findOtherCostByOtherCostID(otherCostID);
        if ((otherCost!=null) && (otherCost.getUserID().equals(userID)))
        {
            if ((otherCost.getVisible().equals(Boolean.TRUE)))
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Other Cost has not been moved to trash can");
            }
            this.otherCostRepository.delete(otherCost);
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Other cost does not found");
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
    public List<OtherCost> findAllHiddenOtherCostByUserID(Long userID)
    {
        return this.otherCostRepository.findAllHiddenOtherCostByUserID(userID);
    }
}
