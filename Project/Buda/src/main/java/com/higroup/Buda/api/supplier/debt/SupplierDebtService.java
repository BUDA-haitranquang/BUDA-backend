package com.higroup.Buda.api.supplier.debt;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.repositories.SupplierRepository;

@Service
public class SupplierDebtService {
    private final SupplierRepository supplierRepository;
    private final SupplierDebtRepository supplierDebtRepository;

    @Autowired
    public SupplierDebtService(SupplierRepository supplierRepository, SupplierDebtRepository supplierDebtRepository) {
        this.supplierRepository = supplierRepository;
        this.supplierDebtRepository = supplierDebtRepository;
    }


    public List<?> findDelayBuyOrderBySupplierID(Long userID, Long supplierID) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierBySupplierID(supplierID);
        if (supplier.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found");
        }
        return this.supplierDebtRepository.findDelayBuyOrderBySupplierID(userID, supplierID);
    }

    public Double findDebtBySupplierID(Long userID, Long supplierID) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierBySupplierID(supplierID);
        if (supplier.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found");
        }
        return this.supplierDebtRepository.findDebtBySupplierID(userID, supplierID);
    }
}
