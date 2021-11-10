package com.higroup.Buda.services;


import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.IngredientRepository;

import com.higroup.Buda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if ((ingredient.isPresent()) && (ingredient.get().getUserID() == userID))
        {
            ingredient.get().setVisible(false);
            this.ingredientRepository.save(ingredient.get());
            return ingredient.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found");
    }
}
