package com.higroup.Buda.api.ingredient.update.quantity;

import com.higroup.Buda.customDTO.QuantityLog;
import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.IngredientLeftLog;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.entities.enumeration.LeftLogType;
import com.higroup.Buda.repositories.IngredientLeftLogRepository;
import com.higroup.Buda.repositories.IngredientRepository;
import com.higroup.Buda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class IngredientUpdateQuantityService {
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;
    private final IngredientLeftLogRepository ingredientLeftLogRepository;

    @Autowired
    public IngredientUpdateQuantityService(IngredientRepository ingredientRepository, UserRepository userRepository, IngredientLeftLogRepository ingredientLeftLogRepository){
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
        this.ingredientLeftLogRepository = ingredientLeftLogRepository;
    }
    @Transactional
    public Ingredient editIngredientQuantity(Long userID, Long ingredientID, QuantityLog quantityLog)
    {
        Integer amountLeftChange = quantityLog.getAmountLeftChange();
        String message = quantityLog.getMessage();
        LeftLogType leftLogType = quantityLog.getLeftLogType();
        Optional<Ingredient> ingredient = this.ingredientRepository.findIngredientByIngredientID(ingredientID);
        if (ingredient.isPresent() && Objects.equals(ingredient.get().getUserID(), userID))
        {
            Integer amountLeft = ingredient.get().getAmountLeft();
            if (amountLeft + amountLeftChange < 0)
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough amount for the request");
            }
            ingredient.get().setAmountLeft(amountLeft + amountLeftChange);
            this.ingredientRepository.save(ingredient.get());
            IngredientLeftLog ingredientLeftLog = new IngredientLeftLog();
            ingredientLeftLog.setIngredient(ingredient.get());
            ingredientLeftLog.setAmountLeftChange(amountLeftChange);
            ingredientLeftLog.setUserID(userID);
            ingredientLeftLog.setMessage(message);
            ingredientLeftLog.setCreationTime(ZonedDateTime.now());
            if (leftLogType.equals(LeftLogType.REMOVE)){
                ingredientLeftLog.setOtherCost(ingredient.get().getPrice() * (- amountLeftChange));
            }
            this.ingredientLeftLogRepository.save(ingredientLeftLog);
            return ingredient.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found");
    }
}
