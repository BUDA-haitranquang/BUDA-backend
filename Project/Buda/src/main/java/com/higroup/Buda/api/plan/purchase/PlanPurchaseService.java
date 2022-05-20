package com.higroup.Buda.api.plan.purchase;

import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Purchase;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.PurchaseRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlanPurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    @Autowired
    public PlanPurchaseService(PurchaseRepository purchaseRepository, UserRepository userRepository)
    {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    public Purchase createNewPurchase(Long userID, Purchase purchase)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        purchase.setCreationTime(ZonedDateTime.now());
        purchase.setUser(userRepository.findUserByUserID(userID).get());
        // THANH TOAN XONG
        user.get().setPlanType(purchase.getPlan().getPlanType());
        this.userRepository.save(user.get());
        this.purchaseRepository.save(purchase);
        return purchase;
    }
    public List<Purchase> findAllByUserID(Long userID) {
        User user = userRepository.getById(userID);
        return this.purchaseRepository.findAllByUser(user);
    }
}
