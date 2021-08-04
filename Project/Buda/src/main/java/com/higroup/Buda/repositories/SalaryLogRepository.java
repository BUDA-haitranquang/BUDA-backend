package com.higroup.Buda.repositories;

import java.util.Optional;

import com.higroup.Buda.entities.SalaryLog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryLogRepository extends JpaRepository<SalaryLog, Long>{
    Optional<SalaryLog> findSalaryLogBySalaryLogID(Long salaryLogID);
}
