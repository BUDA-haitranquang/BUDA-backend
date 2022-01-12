package com.higroup.Buda.api.cost.fixedcost.bill.delay;

import java.time.ZonedDateTime;
import java.util.Optional;

import com.higroup.Buda.entities.FixedCostBill;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.FixedCostBillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DelayFixedCostBillService {
    private final FixedCostBillRepository fixedCostBillRepository;

    @Autowired
    public DelayFixedCostBillService(FixedCostBillRepository fixedCostBillRepository) {
        this.fixedCostBillRepository = fixedCostBillRepository;
    }

    public FixedCostBill delayFixedCostBill(Long userID, Long fixedCostBillID) {
        Optional<FixedCostBill> fixedCostBillOptional = this.fixedCostBillRepository
                .findFixedCostBillByFixedCostBillID(fixedCostBillID);
        if ((fixedCostBillOptional.isPresent()) && (fixedCostBillOptional.get().getUserID().equals(userID))) {
            FixedCostBill fixedCostBill = fixedCostBillOptional.get();
            if ((fixedCostBill.getStatus().equals(Status.CANCELLED)) ||
                    (fixedCostBill.getStatus().equals(Status.FINISHED))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not delay a finished bill");
            }
            if (fixedCostBill.getDueTime().isAfter(ZonedDateTime.now())) {
                fixedCostBill.setDueTime(fixedCostBill.getDueTime().plusDays(1));
            } else {
                fixedCostBill.setDueTime(ZonedDateTime.now().plusDays(1));
            }
            fixedCostBill.setStatus(Status.DELAYING);
            this.fixedCostBillRepository.save(fixedCostBill);
            return fixedCostBill;
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fixed cost bill not found");
    }
}
