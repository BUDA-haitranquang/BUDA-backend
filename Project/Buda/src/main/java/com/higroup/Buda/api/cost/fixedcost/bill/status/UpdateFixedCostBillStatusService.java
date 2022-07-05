package com.higroup.Buda.api.cost.fixedcost.bill.status;

import com.higroup.Buda.entities.FixedCostBill;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.FixedCostBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UpdateFixedCostBillStatusService {
    private final FixedCostBillRepository fixedCostBillRepository;
    
    @Autowired
    public UpdateFixedCostBillStatusService(FixedCostBillRepository fixedCostBillRepository) {
        this.fixedCostBillRepository = fixedCostBillRepository;
    }


    public FixedCostBill updateFixedCostBillStatus(Long userID, UpdateFixedCostBillStatusDTO updateFixedCostBillStatusDTO) {
        Optional<FixedCostBill> fixedCostBill = this.fixedCostBillRepository.findFixedCostBillByFixedCostBillID(updateFixedCostBillStatusDTO.getFixedCostBillID());
        if (fixedCostBill.isEmpty() || !fixedCostBill.get().getUserID().equals(userID))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fixed cost bill not found");
        }
        if (fixedCostBill.get().getStatus().equals(Status.CANCELLED))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cancelled bill can not be modified");
        }
        fixedCostBill.get().setStatus(updateFixedCostBillStatusDTO.getStatus());
        fixedCostBillRepository.save(fixedCostBill.get());
        return fixedCostBill.get();
    }
}
