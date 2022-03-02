package com.higroup.Buda.api.ingredient.view;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.IngredientLeftLogRepository;
import com.higroup.Buda.repositories.IngredientRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class IngredientViewService {
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;
    private final IngredientLeftLogRepository ingredientLeftLogRepository;

    @Autowired
    public IngredientViewService(IngredientRepository ingredientRepository, UserRepository userRepository, IngredientLeftLogRepository ingredientLeftLogRepository){
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
        this.ingredientLeftLogRepository = ingredientLeftLogRepository;
    }

    public Ingredient findIngredientByIngredientID(Long userID, Long ingredientID){
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Optional<Ingredient> ingredient = this.ingredientRepository.findIngredientByIngredientID(ingredientID);
        if (ingredient.isPresent() && Objects.equals(ingredient.get().getUserID(), userID))
        {
            return ingredient.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found");
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
        if (ingredient.isPresent() && Objects.equals(ingredient.get().getUserID(), userID))
        {
            ingredient.get().setVisible(false);
            this.ingredientRepository.save(ingredient.get());
            return ingredient.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found");
    }
    public List<Ingredient> findAlertAmountIngredient(Long userID){
        Optional<User> userOptional = this.userRepository.findUserByUserID(userID);
        if (!userOptional.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        return this.ingredientRepository.findAlertAmountIngredient(userID);
    }
}

