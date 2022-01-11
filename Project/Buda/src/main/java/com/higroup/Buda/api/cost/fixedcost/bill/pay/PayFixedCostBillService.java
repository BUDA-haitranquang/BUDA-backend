package com.higroup.Buda.api.cost.fixedcost.bill.pay;

import java.time.ZonedDateTime;
import java.util.Optional;

import com.higroup.Buda.entities.FixedCostBill;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.FixedCostBillRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PayFixedCostBillService {
    private final FixedCostBillRepository fixedCostBillRepository;
    public PayFixedCostBillService(FixedCostBillRepository fixedCostBillRepository){
        this.fixedCostBillRepository = fixedCostBillRepository;
    }
    public FixedCostBill payFixedCostBill(Long userID, Long fixedCostBillID)
    {
        Optional<FixedCostBill> fixedCostBillOptional = this.fixedCostBillRepository.findFixedCostBillByFixedCostBillID(fixedCostBillID);
        if ((fixedCostBillOptional.isPresent()) && (fixedCostBillOptional.get().getUserID().equals(userID))){
            FixedCostBill fixedCostBill = fixedCostBillOptional.get();
            if ((fixedCostBill.getStatus().equals(Status.FINISHED)) || (fixedCostBill.getStatus().equals(Status.CANCELLED))){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This fixed cost bill has already been paid");
            }
            fixedCostBill.setStatus(Status.FINISHED);
            fixedCostBill.setClosedTime(ZonedDateTime.now());
            this.fixedCostBillRepository.save(fixedCostBill);
            return fixedCostBill;
        } 
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fixed Cost Bill not found");
    }
}
