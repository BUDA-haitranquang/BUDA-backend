package com.higroup.Buda.api.ingredient.create;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.IngredientLeftLogRepository;
import com.higroup.Buda.repositories.IngredientRepository;
import com.higroup.Buda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class IngredientCreateService {
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;

    @Autowired
    public IngredientCreateService(IngredientRepository ingredientRepository, UserRepository userRepository){
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;;
    }
    @Transactional
    public Ingredient createNewIngredient(Long userID, Ingredient newIngredient){
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        if (newIngredient.getIngredientSKU()==null){
            newIngredient.setIngredientSKU(UUID.randomUUID().toString());
        }
        Ingredient ingredientBySKU = 
        this.ingredientRepository.findIngredientByUserIDAndIngredientSKU(userID, newIngredient.getIngredientSKU());
        // check if name of ingredient exist
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