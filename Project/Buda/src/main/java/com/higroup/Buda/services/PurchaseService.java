package com.higroup.Buda.services;

import com.higroup.Buda.entities.Purchase;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.PurchaseRepository;
import com.higroup.Buda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<?> createNewPurchase(Long userID, Purchase purchase)
    {
        purchase.setUser(userRepository.findUserByUserID(userID).get());
        this.purchaseRepository.save(purchase);
        return ResponseEntity.ok().body(purchase.toString());
    }
    public List<Purchase> findAllByUserID(Long userID) {
        User user = userRepository.getById(userID);
        if (user!=null)
        {
            return this.purchaseRepository.findAllByUser(user);
        }
        return null;
    }
    public List<Purchase> findAllByUserIdFromTo(Long userID, String from, String to)
    {
        User user = userRepository.getById(userID);
        if (user != null)
        {
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
                if (purchase.getCreationDate().isAfter(B) || purchase.getCreationDate().isBefore(A)) continue;
                result.add(purchase);
            }
            return result;
        }
        return null;
    }
}
