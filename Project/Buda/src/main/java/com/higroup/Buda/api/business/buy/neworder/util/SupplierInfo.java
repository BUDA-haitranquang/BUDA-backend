package com.higroup.Buda.api.business.buy.neworder.util;

import java.util.Optional;

import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.repositories.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class SupplierInfo {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierInfo(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier findSupplierInfo(Long userID, Supplier requestSupplier) {
        if (requestSupplier == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "request supplier is null");
        }
        if (requestSupplier.getSupplierID() != null) {
            Optional<Supplier> supplierOptional = this.supplierRepository
                    .findSupplierBySupplierID(requestSupplier.getSupplierID());
            if ((supplierOptional.isPresent()) && (supplierOptional.get().getUserID().equals(userID))) {
                return supplierOptional.get();
            }
        }
        String phoneNumber = requestSupplier.getPhoneNumber();
        if (phoneNumber == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing supplier phone number");
        }
        Supplier supplier;
        Optional<Supplier> supplierOptional = this.supplierRepository.findSupplierByUserIDAndPhoneNumber(userID,
                phoneNumber);
        if (supplierOptional.isEmpty()) {
            requestSupplier.setUserID(userID);
            supplier = this.supplierRepository.save(requestSupplier);
        } else {
            supplier = supplierOptional.get();
        }
        return supplier;
    }
}
