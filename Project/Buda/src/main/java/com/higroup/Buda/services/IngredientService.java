package com.higroup.Buda.services;


import java.lang.reflect.InvocationTargetException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.higroup.Buda.BeanUtils.NullAwareBeanUtilsBean;
import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.IngredientLeftLog;
import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.IngredientLeftLogRepository;
import com.higroup.Buda.repositories.IngredientRepository;

import com.higroup.Buda.repositories.UserRepository;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;
    private final IngredientLeftLogRepository ingredientLeftLogRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository, UserRepository userRepository, IngredientLeftLogRepository ingredientLeftLogRepository){
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
        this.ingredientLeftLogRepository = ingredientLeftLogRepository;
    }

    public Ingredient findIngredientByIngredientID(Long ingredientID){
        Optional<Ingredient> ingredient = this.ingredientRepository.findIngredientByIngredientID(ingredientID);

        return ingredient.orElse(null);
    }

    public Ingredient createNewIngredient(Long userID, Ingredient newIngredient){
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        //Optional<Ingredient> ingredient = this.ingredientRepository.findIngredientByName(newIngredient.getName());
        // check if name of ingredient exist
        // if(ingredient.isPresent()){
        //     return ResponseEntity.badRequest().body(String.format("Already existed ingredient %s", newIngredient.getName()));
        // }
        else{
            newIngredient.setUserID(userID);
            this.ingredientRepository.save(newIngredient);
            return newIngredient;
        }
    }

    public List<Ingredient> findIngredientByName(Long userID, String ingredientName) {
        return this.ingredientRepository.findIngredientByName(userID, ingredientName);
    }

    public List<Ingredient> findAllHiddenIngredientByUserID(Long userID)
    {
        return this.ingredientRepository.findAllHiddenIngredientByUserID(userID);
    }

    public List<Ingredient> findAllIngredientByUserID(Long userID)
    {
        return this.ingredientRepository.findAllIngredientByUserID(userID);
    } 

    public Ingredient hideIngredientByIngredientID(Long userID, Long ingredientID)
    {
        Optional<Ingredient> ingredient = this.ingredientRepository.findIngredientByIngredientID(ingredientID);
        System.out.println(ingredient.get());
        if (Objects.equals(ingredient.get().getUserID(), userID))
        {
            ingredient.get().setVisible(false);
            this.ingredientRepository.save(ingredient.get());
            return ingredient.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found");
    }

    public Ingredient editIngredientQuantity(Long userID, Long ingredientID, Integer amountLeftChange, String message)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
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

    public List<Ingredient> findAlertAmountIngredient(Long userID){
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        return this.ingredientRepository.findAlertAmountIngredient();
    }

    public Ingredient editIngredient(Long userID, Long ingredientID, Ingredient ingredient) throws InvocationTargetException, IllegalAccessException {

        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Optional<Ingredient> dest_ingredient = this.ingredientRepository.findIngredientByIngredientID(ingredientID);
        if (dest_ingredient.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found");
        if (Objects.equals(dest_ingredient.get().getUserID(), userID))
        {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(dest_ingredient.get(), ingredient);
            this.ingredientRepository.save(dest_ingredient.get());
            return dest_ingredient.get();
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
    }
}
