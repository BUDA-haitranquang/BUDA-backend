package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.repositories.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    @Autowired
    public SupplierService(SupplierRepository supplierRepository)
    {
        this.supplierRepository = supplierRepository;
    }
    
    public Supplier registerNewSupplier(Long userID, Supplier supplier)
    {
        Optional<Supplier> phoneSupplier = this.supplierRepository.findSupplierByUserIDAndPhoneNumber(userID, supplier.getPhoneNumber());
        if (phoneSupplier.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Supplier with this phoneNumber already exists");
        }
        supplier.setUserID(userID);
        this.supplierRepository.save(supplier);
        return supplier;
    }

    public List<Supplier> findAllByUserID(Long userID)
    {
        return this.supplierRepository.findAllByUserID(userID);
    }
    public Supplier findSupplierByUserIDAndPhoneNumber(Long userID, String phoneNumber)
    {
        Optional<Supplier> phoneSupplier = this.supplierRepository.findSupplierByUserIDAndPhoneNumber(userID, phoneNumber);
        if (phoneSupplier.isPresent())
        {
            return phoneSupplier.get();
        }
        else return null;
        //return phoneSupplier.<ResponseEntity<?>>map(supplier -> ResponseEntity.ok().body(supplier)).orElseGet(() -> ResponseEntity.badRequest().body("Not found"));
    }

    public Supplier updateSupplier(Long userID, Supplier supplier)
    {
        Optional<Supplier> oldSupplier = this.supplierRepository.findSupplierBySupplierID(supplier.getSupplierID());
        if ((oldSupplier.isPresent()) && (oldSupplier.get().getUserID() == userID))
        {
            supplier.setUserID(userID);
            return supplier;
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier does not exists");
    }
}
