package com.higroup.Buda.services;

import java.util.List;

import com.higroup.Buda.entities.Purchase;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.PurchaseRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private PurchaseRepository purchaseRepository;
    private UserRepository userRepository;
    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, UserRepository userRepository)
    {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
    }
    public List<Purchase> findAllByUserID(Long userID) {
        User user = userRepository.getById(userID);
        if (user!=null)
        {
            return this.purchaseRepository.findAllByUser(user);
        }
        return null;
    }
}
