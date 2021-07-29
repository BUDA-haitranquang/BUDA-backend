package com.higroup.Buda.repositories;

import java.util.Optional;

import com.higroup.Buda.entities.Plan;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long>{
    Optional<Plan> findPlanByPlanID(Long planID);
}
