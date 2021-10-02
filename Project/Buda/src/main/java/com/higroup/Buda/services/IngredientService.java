package com.higroup.Buda.services;


import java.util.Optional;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.IngredientRepository;

import com.higroup.Buda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository, UserRepository userRepository){
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
    }

    public Ingredient findIngredientByIngredientID(Long ingredientID){
        Optional<Ingredient> ingredient = this.ingredientRepository.findIngredientByIngredientID(ingredientID);

        return ingredient.orElse(null);
    }

    public ResponseEntity<?> createNewIngredient(Long userID, Ingredient newIngredient){
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            return ResponseEntity.badRequest().body("User not found");
        }
        Optional<Ingredient> ingredient = this.ingredientRepository.findIngredientByName(newIngredient.getName());
        // check if name of ingredient exist
        if(ingredient.isPresent()){
            return ResponseEntity.badRequest().body(String.format("Already existed ingredient %s", newIngredient.getName()));
        }
        else{
            newIngredient.setUserID(userID);
            this.ingredientRepository.save(newIngredient);
            return ResponseEntity.ok().body(newIngredient.toString());
        }
    }

    public Ingredient findIngredientByName(String ingredientName) {
        Optional<Ingredient> ingredient = this.ingredientRepository.findIngredientByName(ingredientName);

        return ingredient.orElse(null);
    }
}
