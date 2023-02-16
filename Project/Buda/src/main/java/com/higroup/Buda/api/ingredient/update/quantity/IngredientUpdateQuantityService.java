package com.higroup.Buda.api.ingredient.update.quantity;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.IngredientLeftLog;
import com.higroup.Buda.repositories.IngredientLeftLogRepository;
import com.higroup.Buda.repositories.IngredientRepository;

@Service
public class IngredientUpdateQuantityService {
    private final IngredientRepository ingredientRepository;
    private final IngredientLeftLogRepository ingredientLeftLogRepository;

    @Autowired
    public IngredientUpdateQuantityService(IngredientRepository ingredientRepository, IngredientLeftLogRepository ingredientLeftLogRepository){
        this.ingredientRepository = ingredientRepository;
        this.ingredientLeftLogRepository = ingredientLeftLogRepository;
    }
    @Transactional
    public Ingredient editIngredientQuantity(Long userID, Long ingredientID, Integer amountLeftChange, String message)
    {
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
            this.ingredientLeftLogRepository.save(ingredientLeftLog);
            return ingredient.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found");
    }
}
