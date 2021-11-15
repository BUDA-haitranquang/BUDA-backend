package com.higroup.Buda.services;

import java.util.List;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Plan;
import com.higroup.Buda.repositories.PlanRepository;
import com.higroup.Buda.util.Checker.PresentChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService {
    private final PlanRepository planRepository;
    private final PresentChecker presentChecker;
    @Autowired
    public PlanService(PlanRepository planRepository, PresentChecker presentChecker)
    {
        this.presentChecker = presentChecker;
        this.planRepository = planRepository;
    }
    public List<Plan> findAllPlan()
    {
        return this.planRepository.findAll();
    }
    @Transactional
    public Plan createNewPlan(Plan plan)
    {
        this.planRepository.save(plan);
        return plan;
    }
    @Transactional
    public void deletePlanByID(Long id)
    {
        this.presentChecker.checkIdAndRepository(id, this.planRepository);
        this.planRepository.deleteById(id);
    }
}
