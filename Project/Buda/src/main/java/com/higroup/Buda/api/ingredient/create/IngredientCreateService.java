package com.higroup.Buda.api.ingredient.create;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.repositories.IngredientRepository;

@Service
public class IngredientCreateService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientCreateService(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }
    @Transactional
    public Ingredient createNewIngredient(Long userID, Ingredient newIngredient){
        if (newIngredient.getIngredientSKU()==null){
            Long count = this.ingredientRepository.findNumberOfIngredientByUserID(userID);
            newIngredient.setIngredientSKU("ING-" + count);
        }
        Ingredient ingredientBySKU = 
        this.ingredientRepository.findIngredientByUserIDAndIngredientSKU(userID, newIngredient.getIngredientSKU());
        // check if name of ingredient exist
        if ((newIngredient.getPicture() == null) || (newIngredient.getPicture().getPictureID() == null)) {
            newIngredient.setPicture(null);
        }
        if(ingredientBySKU!=null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Another ingredient with SKU has already existed: " + ingredientBySKU.getName());
        }
        else {
            newIngredient.setUserID(userID);
            this.ingredientRepository.save(newIngredient);
            return newIngredient;
        }
    }
}
