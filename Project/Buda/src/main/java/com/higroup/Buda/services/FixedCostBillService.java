package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.FixedCost;
import com.higroup.Buda.entities.FixedCostBill;
import com.higroup.Buda.repositories.FixedCostBillRepository;
import com.higroup.Buda.repositories.FixedCostRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.util.Checker.PresentChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Service
public class FixedCostBillService {
    private final FixedCostBillRepository fixedCostBillRepository;
    private final FixedCostRepository fixedCostRepository;
    private final PresentChecker presentChecker;
    private final UserRepository userRepository;
    @Autowired
    public FixedCostBillService(FixedCostBillRepository fixedCostBillRepository, FixedCostRepository fixedCostRepository, PresentChecker presentChecker, UserRepository userRepository)
    {
        this.presentChecker = presentChecker;
        this.userRepository = userRepository;
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
        presentChecker.checkIdAndRepository(userID, userRepository);
        return fixedCostBillRepository.findAllByUserID(userID);
    }
    public List<FixedCostBill> findAllFixedCostBillByUserIDLastXDays(Long userID, Long X)
    {
        presentChecker.checkIdAndRepository(userID, userRepository);
        return fixedCostBillRepository.findAllFixedCostBillByUserIDLastXDays(userID, X);
    }
    public List<FixedCostBill> findAllIncompletedFixedCostBillByUserID(Long userID)
    {
        presentChecker.checkIdAndRepository(userID, userRepository);
        return fixedCostBillRepository.findAllIncompletedFixedCostBillByUser(userID);
    }
}
