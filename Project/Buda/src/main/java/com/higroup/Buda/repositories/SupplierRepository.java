package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Supplier;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{
    Optional<Supplier> findSupplierBySupplierID(Long supplierID);
    List<Supplier> findAllByUserID(Long userID);
    Optional<Supplier> findSupplierByUserIDAndPhoneNumber(Long userID, String phoneNumber);
}
