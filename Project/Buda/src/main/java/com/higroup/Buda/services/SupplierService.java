package com.higroup.Buda.services;

import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getSuppliers()
    {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierByID(Long id)
    {
        return supplierRepository.findSupplierBySupplierID(id).get();
    }


}
