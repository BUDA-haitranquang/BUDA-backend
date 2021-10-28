package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.DiscountRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.util.Checker.PresentChecker;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    @Autowired
    private PresentChecker presentChecker;
    public Discount createNewDiscount(Long userID, Discount discount)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserID does not exist");
        }
        discount.setUserID(userID);
        this.discountRepository.save(discount);
        return discount;
    }
    public Discount findDiscountByDiscountID(Long discountID)
    {
        presentChecker.checkIdAndRepository(discountID, this.discountRepository);
        return this.discountRepository.findDiscountByDiscountID(discountID);
    }
    public List<Discount> findAllDiscountByUserID(Long userID)
    {
        presentChecker.checkId(userID);
        return this.discountRepository.findAllDiscountByUserID(userID);
    }
    public void deleteDiscount(Long userID, Long discountID)
    {
        presentChecker.checkIdAndRepository(discountID, this.discountRepository);
        Discount discount = this.discountRepository.findDiscountByDiscountID(discountID);
        if (discount.getUserID()!=userID)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserID does not match");
        }
        this.discountRepository.deleteById(discountID);
    }
}
