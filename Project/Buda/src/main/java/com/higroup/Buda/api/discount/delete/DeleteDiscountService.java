package com.higroup.Buda.api.discount.delete;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.repositories.DiscountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DeleteDiscountService {
    private final DiscountRepository discountRepository;
    @Autowired
    public DeleteDiscountService(DiscountRepository discountRepository){
        this.discountRepository = discountRepository;
    }
    @Transactional
    public void deleteDiscount(Long userID, Long discountID)
    {
        Discount discount = this.discountRepository.findDiscountByDiscountID(discountID);
        if ((discount!=null) && (!discount.getUserID().equals(userID)))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserID does not match");
        }
        this.discountRepository.delete(discount);
    }
}
