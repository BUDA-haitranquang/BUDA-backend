package com.higroup.Buda.services;

import com.higroup.Buda.entities.Purchase;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.PurchaseRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, UserRepository userRepository)
    {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
    }
    public ResponseEntity<?> createNewPurchase(Long userID, Purchase purchase)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            return ResponseEntity.badRequest().body("User not found");
        }
        purchase.setUser(userRepository.findUserByUserID(userID).get());
        this.purchaseRepository.save(purchase);
        return ResponseEntity.ok().body(purchase);
    }
    public List<Purchase> findAllByUserID(Long userID) {
        User user = userRepository.getById(userID);
        return this.purchaseRepository.findAllByUser(user);
    }
    public List<Purchase> findAllByUserIdFromTo(Long userID, String from, String to)
    {
        User user = userRepository.getById(userID);
        List<Purchase> purchases = purchaseRepository.findAllByUser(user);
        Instant instant = Instant.now();
        //can be LocalDateTime
        ZoneId systemZone = ZoneId.systemDefault();
        // my timezone
        ZoneOffset currentOffsetForMyZone = systemZone.getRules().getOffset(instant);


        ZonedDateTime A = ZonedDateTime.parse(from + currentOffsetForMyZone.toString() + "[" + ZoneId.systemDefault().toString() + "]");
        ZonedDateTime B = ZonedDateTime.parse(to + currentOffsetForMyZone.toString() + "[" + ZoneId.systemDefault().toString() + "]");


        List<Purchase> result = new ArrayList<Purchase>();
        for (Purchase purchase: purchases)
        {
            if (purchase.getCreationTime().isAfter(B) || purchase.getCreationTime().isBefore(A)) continue;
            result.add(purchase);
        }
        return result;
    }
}
