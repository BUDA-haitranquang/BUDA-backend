package com.higroup.Buda.api.ingredient.delete;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.IngredientRepository;
import com.higroup.Buda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@Service
public class IngredientDeleteService {
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;

    @Autowired
    public IngredientDeleteService(IngredientRepository ingredientRepository, UserRepository userRepository)
    {
        this.userRepository = userRepository;
        this.ingredientRepository = ingredientRepository;
    }
    public void deleteIngredientByIngredient(Long userID, Long ingredientID)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Optional<Ingredient> ingredient = this.ingredientRepository.findIngredientByIngredientID(ingredientID);
        if (ingredient.isPresent() && (Objects.equals(ingredient.get().getUserID(), userID)))
        {
            if (ingredient.get().getVisible())
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not delete a visible ingredient");
            }
            else
            {
                this.ingredientRepository.delete(ingredient.get());
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient does not exists");
    }
}
