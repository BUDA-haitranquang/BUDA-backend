package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.FixedCost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FixedCostRepository extends JpaRepository<FixedCost, Long>{
    Optional<FixedCost> findFixedCostByFixedCostID(Long fixedCostID);
    @Query(value = "select f from FixedCost f where f.userID = :userID and f.visible = true")
    List<FixedCost> findAllByUserID(@Param("userID") Long userID);
    @Query(value = "select f from FixedCost f where f.userID = :userID and f.visible = false")
    List<FixedCost> findAllHiddenFixedCostByUserID(@Param("userID") Long userID);
}
