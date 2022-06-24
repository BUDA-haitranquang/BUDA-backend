package com.higroup.Buda.api.cost.othercost.status;

import com.higroup.Buda.entities.OtherCost;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.OtherCostRepository;
import com.higroup.Buda.util.Checker.PresentChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateOtherCostStatusService {
    private final OtherCostRepository otherCostRepository;
    
    @Autowired
    public UpdateOtherCostStatusService(OtherCostRepository otherCostRepository) {
        this.otherCostRepository = otherCostRepository;
    }
    @Autowired
    private PresentChecker presentChecker;

    public OtherCost updateOtherCostStatus(Long userID, UpdateOtherCostStatusDTO updateOtherCostStatusDTO) {
        OtherCost otherCost = this.otherCostRepository.findOtherCostByOtherCostID(updateOtherCostStatusDTO.getOtherCostID());
        
        presentChecker.checkIdAndRepository(updateOtherCostStatusDTO.getOtherCostID(), otherCostRepository);
        if (!otherCost.getUserID().equals(userID))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Other cost bill not found");
        }
        if (otherCost.getStatus().equals(Status.CANCELLED))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cancelled bill can not be modified");
        }
        otherCost.setStatus(updateOtherCostStatusDTO.getStatus());
        otherCostRepository.save(otherCost);
        return otherCost;
    }
}
