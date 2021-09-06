package com.higroup.Buda.services;


import java.util.Optional;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.repositories.IngredientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    
    @Autowired
    public IngredientService(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient findIngredientByIngredientID(Long ingredientID){
        Optional<Ingredient> ingredient = this.ingredientRepository.findIngredientByIngredientID(ingredientID);

        if (ingredient.isPresent()){
            return ingredient.get();
        }
        else{
            return null;
        }
    }

    public ResponseEntity<?> createNewIngredient(Ingredient newingredient){
        Optional<Ingredient> ingredient = this.ingredientRepository.findIngredientByName(newingredient.getName());
        // check if name of ingredient exist
        if(ingredient.isPresent()){
            return ResponseEntity.badRequest().body(String.format("Alread existed ingredient %s", newingredient.getName()));
        }
        else{
            this.ingredientRepository.save(newingredient);
            return ResponseEntity.ok().body(newingredient.toString());
        }
    }
}
