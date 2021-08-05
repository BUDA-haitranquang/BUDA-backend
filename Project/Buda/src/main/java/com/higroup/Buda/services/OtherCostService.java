package com.higroup.Buda.services;

import com.higroup.Buda.repositories.OtherCostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtherCostService {
    private final OtherCostRepository otherCostRepository;
    @Autowired
    public OtherCostService(OtherCostRepository otherCostRepository)
    {
        this.otherCostRepository = otherCostRepository;
    }
}
