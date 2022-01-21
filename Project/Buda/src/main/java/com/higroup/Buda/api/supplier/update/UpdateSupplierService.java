package com.higroup.Buda.api.supplier.update;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.BeanUtils.NullAwareBeanUtilsBean;
import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.repositories.SupplierRepository;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateSupplierService {
    private final SupplierRepository supplierRepository;
    public UpdateSupplierService(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }
    @Transactional
    public Supplier updateSupplier(Long userID, Supplier supplier) throws IllegalAccessException, InvocationTargetException
    {
        Optional<Supplier> oldSupplierOptional = this.supplierRepository.findSupplierBySupplierID(supplier.getSupplierID());
        if ((oldSupplierOptional.isPresent()) && (oldSupplierOptional.get().getUserID().equals(userID)))
        {
            Supplier oldSupplier = oldSupplierOptional.get();
            BeanUtilsBean bean = new NullAwareBeanUtilsBean();
            supplier.setUserID(userID);
            String phoneNumber = supplier.getPhoneNumber();
            if (phoneNumber!=null){
                Optional<Supplier> optionalPhoneSupplier = 
                this.supplierRepository.findSupplierByUserIDAndPhoneNumber(userID, phoneNumber);
                if ((optionalPhoneSupplier.isPresent()) && 
                (!optionalPhoneSupplier.get().getSupplierID().equals(oldSupplier.getSupplierID()))){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "This phone number has been used for another supplier");
                }
            }
            bean.copyProperties(oldSupplier, supplier);
            this.supplierRepository.save(oldSupplier);
            return oldSupplier;
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier does not exist");
    }
}
