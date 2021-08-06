package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.FixedCost;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FixedCostRepository extends JpaRepository<FixedCost, Long>{
    Optional<FixedCost> findFixedCostByFixedCostID(Long fixedCostID);
    List<FixedCost> findAllByUserID(Long userID);
}
