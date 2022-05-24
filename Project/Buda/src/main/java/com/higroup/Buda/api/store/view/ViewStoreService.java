package com.higroup.Buda.api.store.view;

import java.util.List;

import com.higroup.Buda.entities.Store;
import com.higroup.Buda.repositories.StoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public Store findOneStoreByUser(Long userID) {
        Store store = this.storeRepository.findAllByUserID(userID).get(0);
        if (store != null) {
            return store;
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user has no stores");
    }
}
