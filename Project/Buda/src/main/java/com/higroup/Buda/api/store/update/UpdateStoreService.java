package com.higroup.Buda.api.store.update;

import java.lang.reflect.InvocationTargetException;

import com.higroup.Buda.entities.Store;
import com.higroup.Buda.repositories.StoreRepository;
import com.higroup.Buda.util.BeanUtils.NullAwareBeanUtilsBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateStoreService {
    private final StoreRepository storeRepository;

    @Autowired
    public UpdateStoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store updateStore(Long userID, Store store) throws IllegalAccessException, InvocationTargetException {
        if ((store.getStoreID() != null)) {
            Store oldStore = this.storeRepository.findStoreByStoreID(store.getStoreID());
            if (oldStore.getUserID().equals(userID)) {
                NullAwareBeanUtilsBean bean = new NullAwareBeanUtilsBean();
                bean.copyProperties(oldStore, store);
                oldStore.setUserID(userID);
                this.storeRepository.save(oldStore);
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Store not found");
    }
}
