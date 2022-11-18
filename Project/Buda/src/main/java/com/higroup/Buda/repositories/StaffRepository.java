package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface StaffRepository extends JpaRepository<Staff, Long>{
    Optional<Staff> findStaffByStaffUUID(@Param("staffUUID") String staffUUID);
    Optional<Staff> findStaffByStaffID(@Param("staffID") Long staffID);
    List<Staff> findAllByUserID(@Param("userID") Long userID);
    Optional<Staff> findStaffByAccount(@Param("account") String account);
    
}
