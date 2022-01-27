package com.higroup.Buda.api.discount.view;

import java.util.List;

import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.repositories.DiscountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ViewDiscountService {
    private final DiscountRepository discountRepository;
    @Autowired
    public ViewDiscountService(DiscountRepository discountRepository){
        this.discountRepository = discountRepository;
    }
    public List<Discount> findAllDiscountByUserID(Long userID){
        return this.discountRepository.findAllDiscountByUserID(userID);
    }
    public Discount findDiscountByDiscountID(Long userID, Long discountID){
        Discount discount = this.discountRepository.findDiscountByDiscountID(discountID);
        if ((discount!=null) && (discount.getUserID().equals(userID)))
        {
            return discount;
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Discount not found");
    }
}

