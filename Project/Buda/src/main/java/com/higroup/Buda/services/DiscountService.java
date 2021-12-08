package com.higroup.Buda.services;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.entities.DiscountType;
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
    @Transactional
    public Discount createNewDiscount(Long userID, Discount discount)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserID does not exist");
        }
        discount.setCreatedTime(ZonedDateTime.now());
        discount.setUserID(userID);
        if (discount.getExpiryTime().isBefore(discount.getCreatedTime()))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Expiry time cannot before Created time!");
        }
        switch (discount.getDiscountType()) {
            case CASH_ONLY:
                if(discount.getCash()==null)
                {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cash field is required!");
                }
                else if (discount.getCash()<0)
                {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cash field can't be negative'");
                }
                break;
            case PERCENTAGE_ONLY:
                if(discount.getPercentage()==null)
                {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Percentage field is required!");
                }
                else if (discount.getPercentage()<0 || discount.getPercentage()>100)
                {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Percentage field must be between 0 and 100!");
                }
                break;
            case BOTH:
                if ((discount.getPercentage()==null)||(discount.getCash()==null))
                {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Both Cash and Percentage field are required!");
                }
                else if (discount.getCash()<0)
                {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cash field can't be negative'");
                }
                else if (discount.getPercentage()<0 || discount.getPercentage()>100)
                {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Percentage field must be between 0 and 100!");
                }
                break;
        }
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
    @Transactional
    public void deleteDiscount(Long userID, Long discountID)
    {
        presentChecker.checkIdAndRepository(discountID, this.discountRepository);
        Discount discount = this.discountRepository.findDiscountByDiscountID(discountID);
        if (!discount.getUserID().equals(userID))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserID does not match");
        }
        this.discountRepository.deleteById(discountID);
    }
}
