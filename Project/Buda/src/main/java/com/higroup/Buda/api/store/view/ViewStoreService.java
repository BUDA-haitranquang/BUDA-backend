package com.higroup.Buda.api.store.view;

import java.util.List;

import com.higroup.Buda.entities.Store;
import com.higroup.Buda.repositories.StoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewStoreService {
    private final StoreRepository storeRepository;
    @Autowired
    public ViewStoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }
    public List<Store> findAllStoresByUser(Long userID) {
        return this.storeRepository.findAllByUserID(userID);
    }
}
