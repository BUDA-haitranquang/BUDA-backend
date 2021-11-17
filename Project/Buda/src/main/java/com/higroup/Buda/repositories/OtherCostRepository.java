package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.OtherCost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OtherCostRepository extends JpaRepository<OtherCost, Long>{
    OtherCost findOtherCostByOtherCostID(Long otherCostID);
    List<OtherCost> findAllByUserID(Long userID);
    @Query(value = "select * from other_cost o where o.user_id = :userID and o.status != 'FINISHED' and o.status != 'CANCELLED'", nativeQuery = true)
    List<OtherCost> findAllIncompletedOtherCostByUserID(Long userID);
    @Query(value = "select * from other_cost o where o.user_id = :userID and (o.creation_time BETWEEN NOW() - INTERVAL :X DAY and NOW())", nativeQuery = true)
    List<OtherCost> findAllOtherCostByUserIDLastXDays(@Param("userID") Long userID, @Param("X") Long X);
}
