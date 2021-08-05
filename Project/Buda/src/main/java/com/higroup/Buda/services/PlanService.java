package com.higroup.Buda.services;

import com.higroup.Buda.entities.Plan;
import com.higroup.Buda.repositories.PlanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService {
    private final PlanRepository planRepository;
    @Autowired
    public PlanService(PlanRepository planRepository)
    {
        this.planRepository = planRepository;
    }
    public void addPlan(Plan plan)
    {
        this.planRepository.save(plan);
    }
    public void deletePlanByID(Long id)
    {
        this.planRepository.deleteById(id);
    }
}
