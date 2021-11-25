package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{
    Optional<Supplier> findSupplierBySupplierID(Long supplierID);
    @Query(value = "select s from Supplier s where s.userID = :userID and s.visible = true")
    List<Supplier> findAllByUserID(@Param("userID") Long userID);
    Optional<Supplier> findSupplierByUserIDAndPhoneNumber(Long userID, String phoneNumber);
}
