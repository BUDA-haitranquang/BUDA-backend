package com.higroup.Buda.repositories;

import java.util.List;

import com.higroup.Buda.entities.PaySlip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaySlipRepository extends JpaRepository<PaySlip, Long>{
    @Query("select p from PaySlip p where userID = :userID")
    List<PaySlip> findAllPaySlipByUserID(@Param("userID") Long userID);
}
