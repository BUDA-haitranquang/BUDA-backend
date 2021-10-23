package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.repositories.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    @Autowired
    public SupplierService(SupplierRepository supplierRepository)
    {
        this.supplierRepository = supplierRepository;
    }
    
    public ResponseEntity<?> registerNewSupplier(Long userID, Supplier supplier)
    {
        Optional<Supplier> phoneSupplier = this.supplierRepository.findSupplierByUserIDAndPhoneNumber(userID, supplier.getPhoneNumber());
        if (phoneSupplier.isPresent())
        {
            return ResponseEntity.badRequest().body("Already exists another supplier with this phone number");
        }
        supplier.setUserID(userID);
        this.supplierRepository.save(supplier);
        return ResponseEntity.ok().body(supplier);
    }

    public ResponseEntity<?> findAllByUserID(Long userID)
    {
        return ResponseEntity.ok().body(this.supplierRepository.findAllByUserID(userID));
    }
    public ResponseEntity<?> findSupplierByUserIDAndPhoneNumber(Long userID, String phoneNumber)
    {
        Optional<Supplier> phoneSupplier = this.supplierRepository.findSupplierByUserIDAndPhoneNumber(userID, phoneNumber);
        return phoneSupplier.<ResponseEntity<?>>map(supplier -> ResponseEntity.ok().body(supplier)).orElseGet(() -> ResponseEntity.badRequest().body("Not found"));
    }
}
