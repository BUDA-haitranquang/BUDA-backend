package com.higroup.Buda.api.discount.create;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.repositories.DiscountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CreateDiscountService {
    private final DiscountRepository discountRepository;
    @Autowired
    public CreateDiscountService(DiscountRepository discountRepository){
        this.discountRepository = discountRepository;
    }
    @Transactional
    public Discount createDiscount(Long userID, Discount discount){
        discount.setCreatedTime(ZonedDateTime.now());
        discount.setUserID(userID);
        if (discount.getDiscountCode()==null){
            discount.setDiscountCode(UUID.randomUUID().toString());
        }
        Discount discountByCode = this.discountRepository.findDiscountByUserIDAndDiscountCode(userID, discount.getDiscountCode());
        if (discountByCode!=null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Another discount with this code has already existed: " + discountByCode.getName());
        }
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
        discount.setOrderCount(0);
        this.discountRepository.save(discount);
        return discount;
    }
}
