package com.higroup.Buda.api.store.create;

import com.higroup.Buda.entities.Store;
import com.higroup.Buda.repositories.StoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateStoreService {
    private final StoreRepository storeRepository;
    @Autowired
    public CreateStoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }
    public Store createStore(Long userID, Store store) {
        store.setUserID(userID);
        this.storeRepository.save(store);
        return store;
    }
}
