package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.FixedCost;
import com.higroup.Buda.repositories.FixedCostRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.util.Checker.PresentChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties.Fixed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FixedCostService {
    private final FixedCostRepository fixedCostRepository;
    private final UserRepository userRepository;
    private final PresentChecker presentChecker;
    @Autowired
    public FixedCostService(FixedCostRepository fixedCostRepository, UserRepository userRepository, PresentChecker presentChecker)
    {
        this.presentChecker = presentChecker;
        this.userRepository = userRepository;
        this.fixedCostRepository = fixedCostRepository;
    }
    public FixedCost findFixedCostByFixedCostID(Long userID, Long fixedCostID)
    {
        Optional<FixedCost> fixedCost = this.fixedCostRepository.findFixedCostByFixedCostID(fixedCostID);
        if ((fixedCost.isPresent()) && (fixedCost.get().getUserID() == userID))
        {
            return fixedCost.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fixed Cost not found");
    }
    public List<FixedCost> findAllByUserID(Long userID)
    {
        return this.fixedCostRepository.findAllByUserID(userID);
    }
    public FixedCost createNewFixedCost(Long userID, FixedCost fixedCost)
    {
        presentChecker.checkIdAndRepository(userID, this.userRepository);
        fixedCost.setUserID(userID);
        this.fixedCostRepository.save(fixedCost);
        return fixedCost;
    }
}
