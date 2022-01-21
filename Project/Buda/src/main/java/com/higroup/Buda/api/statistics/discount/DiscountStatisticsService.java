package com.higroup.Buda.api.statistics.discount;

import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.repositories.DiscountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Service
public class DiscountStatisticsService {
    private final DiscountRepository discountRepository;
    @Autowired
    public DiscountStatisticsService(DiscountRepository discountRepository){
        this.discountRepository = discountRepository;
    }
    public Double totalRevenueByDiscountID(Long userID, Long discountID){
        Discount discount = this.discountRepository.findDiscountByDiscountID(discountID);
        if ((discount!=null) && (discount.getUserID().equals(userID))){
            Double ans = 0.0;
            for (SellOrder sellOrder: discount.getSellOrders())
            {
                ans = ans + sellOrder.getFinalCost();
            }
            return ans;
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Discount not found");
    }
}
