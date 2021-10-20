package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.FixedCost;
import com.higroup.Buda.repositories.FixedCostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties.Fixed;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FixedCostService {
    private final FixedCostRepository fixedCostRepository;
    @Autowired
    public FixedCostService(FixedCostRepository fixedCostRepository)
    {
        this.fixedCostRepository = fixedCostRepository;
    }
    public ResponseEntity<?> findFixedCostByFixedCostID(Long fixedCostID)
    {
        Optional<FixedCost> fixedCost = this.fixedCostRepository.findFixedCostByFixedCostID(fixedCostID);
        return fixedCost.<ResponseEntity<?>>map(cost -> ResponseEntity.ok().body(cost)).orElseGet(() -> ResponseEntity.badRequest().body("FixedCost not found"));
    }
    public ResponseEntity<?> findAllByUserID(Long userID)
    {
        return ResponseEntity.ok().body(this.fixedCostRepository.findAllByUserID(userID));
    }
}
