package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Staff;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long>{
    Optional<Staff> findStaffByStaffUUID(String staffUUID);
    Optional<Staff> findStaffByStaffID(Long staffID);
    List<Staff> findAllByUserID(Long userID);
}
