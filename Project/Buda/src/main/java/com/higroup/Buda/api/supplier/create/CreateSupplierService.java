package com.higroup.Buda.api.supplier.create;

import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.repositories.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CreateSupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public CreateSupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    public Supplier createNewSupplier(Long userID, Supplier supplier) {
        Optional<Supplier> phoneSupplier = this.supplierRepository.findSupplierByUserIDAndPhoneNumber(userID,
                supplier.getPhoneNumber());
        if (phoneSupplier.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "This phone number has been used for another supplier: "
                            + phoneSupplier.get().getName());
        }
        supplier.setUserID(userID);
        this.supplierRepository.save(supplier);
        return supplier;
    }
}
