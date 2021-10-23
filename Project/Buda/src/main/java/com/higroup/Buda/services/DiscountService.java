package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.DiscountRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
    private final DiscountRepository discountRepository;
    private final UserRepository userRepository;
    @Autowired
    public DiscountService(DiscountRepository discountRepository, UserRepository userRepository)
    {
        this.userRepository = userRepository;
        this.discountRepository = discountRepository;
    }
    public ResponseEntity<?> registerNewDiscount(Long userID, Discount discount)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            return ResponseEntity.badRequest().body("User not found");
        }
        discount.setUserID(userID);
        this.discountRepository.save(discount);
        return ResponseEntity.ok().body(discount);
    }
    public ResponseEntity<?> findDiscountByDiscountID(Long discountID)
    {
        Optional<Discount> discount = this.discountRepository.findDiscountByDiscountID(discountID);
        return discount.<ResponseEntity<?>>map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.badRequest().body("Not found"));
    }
    public ResponseEntity<?> findAllDiscountByUserID(Long userID)
    {
        return ResponseEntity.ok().body(this.discountRepository.findAllDiscountByUserID(userID));
    }
}
