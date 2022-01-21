package com.higroup.Buda.api.supplier.view;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.repositories.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ViewSupplierService {
    private final SupplierRepository supplierRepository;
    @Autowired
    public ViewSupplierService(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }
    public List<Supplier> findAllByUserID(Long userID)
    {
        return this.supplierRepository.findAllByUserID(userID);
    }
    public Supplier findSupplierBySupplierID(Long userID, Long supplierID)
    {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierBySupplierID(supplierID);
        if (supplier.isPresent() && (supplier.get().getUserID().equals(userID)))
        {
            return supplier.get();
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found");
    }
}
