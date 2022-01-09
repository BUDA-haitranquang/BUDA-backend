package com.higroup.Buda.api.cost.fixedcost.bill;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.customDTO.ExpenseByTimeStatistics;
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
        if ((fixedCost.isPresent()) && (fixedCost.get().getUserID().equals(userID)))
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
    @Transactional
    public FixedCostBill createNewFixedCostBill(Long userID, FixedCostBill fixedCostBill)
    {
        presentChecker.checkIdAndRepository(userID, userRepository);
        //presentChecker.checkIdAndRepository(fixedCostBill.getFixedCost().getFixedCostID(), fixedCostRepository);
        Optional<FixedCost> fixedCost = this.fixedCostRepository.findFixedCostByFixedCostID(fixedCostBill.getFixedCost().getFixedCostID());
        if ((fixedCost.isPresent()) && (fixedCost.get().getUserID().equals(userID)))
        {
            fixedCostBill.setUserID(userID);
            fixedCostBill.setFixedCost(fixedCost.get());
            fixedCostBill.setCreationTime(ZonedDateTime.now());
            this.fixedCostBillRepository.save(fixedCostBill);
            return fixedCostBill;
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fixed Cost not found");
    }
}
