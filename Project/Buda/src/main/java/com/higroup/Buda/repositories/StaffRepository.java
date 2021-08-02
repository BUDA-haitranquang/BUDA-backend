package com.higroup.Buda.repositories;

import java.util.Optional;

import com.higroup.Buda.entities.Staff;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long>{
    Optional<Staff> findStaffByStaffID(Long staffID);
}
