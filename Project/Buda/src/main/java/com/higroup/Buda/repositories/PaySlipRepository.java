package com.higroup.Buda.repositories;

import com.higroup.Buda.entities.PaySlip;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaySlipRepository extends JpaRepository<PaySlip, Long>{
    
}
