package com.higroup.Buda.api.business.buy.returnorder;

import java.time.ZonedDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.BuyOrderItem;
import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.IngredientLeftLog;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.BuyOrderRepository;
import com.higroup.Buda.repositories.IngredientLeftLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReturnBuyOrderService {
    private final BuyOrderRepository buyOrderRepository;
    private final IngredientLeftLogRepository ingredientLeftLogRepository;

    @Autowired
    public ReturnBuyOrderService(BuyOrderRepository buyOrderRepository,
            IngredientLeftLogRepository ingredientLeftLogRepository) {
        this.ingredientLeftLogRepository = ingredientLeftLogRepository;
        this.buyOrderRepository = buyOrderRepository;
    }

    @Transactional
    public BuyOrder returnBuyOrderByBuyOrderID(Long userID, Long buyOrderID) {
        Optional<BuyOrder> buyOrderOptional = this.buyOrderRepository.findBuyOrderByBuyOrderID(buyOrderID);
        if ((buyOrderOptional.isPresent()) && (buyOrderOptional.get().getUserID().equals(userID))) {
            BuyOrder buyOrder = buyOrderOptional.get();
            if (buyOrder.getStatus().equals(Status.FINISHED)) {
                for (BuyOrderItem buyOrderItem : buyOrder.getBuyOrderItems()) {
                    IngredientLeftLog ingredientLeftLog = new IngredientLeftLog();
                    ingredientLeftLog.setUserID(userID);
                    ingredientLeftLog.setIngredient(buyOrderItem.getIngredient());
                    ingredientLeftLog.setCreationTime(ZonedDateTime.now());
                    ingredientLeftLog.setAmountLeftChange(-buyOrderItem.getQuantity());
                    ingredientLeftLog.setMessage("Buy Order " + buyOrder.getTextID() + " returned");
                    Ingredient ingredient = buyOrderItem.getIngredient();
                    if (ingredient.getAmountLeft() < buyOrderItem.getQuantity()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "There are not enough " + ingredient.getName() + " left!");
                    }
                    ingredient.setAmountLeft(ingredient.getAmountLeft() - buyOrderItem.getQuantity());
                    this.ingredientLeftLogRepository.save(ingredientLeftLog);
                }
            }
            buyOrder.setStatus(Status.RETURNED);
            this.buyOrderRepository.save(buyOrder);
            return buyOrder;
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Buy Order not found");
    }
}
