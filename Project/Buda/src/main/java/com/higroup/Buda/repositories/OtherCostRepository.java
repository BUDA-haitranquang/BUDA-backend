package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.OtherCost;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OtherCostRepository extends JpaRepository<OtherCost, Long>{
    OtherCost findOtherCostByOtherCostID(Long otherCostID);
    List<OtherCost> findAllByUserID(Long userID);
}
