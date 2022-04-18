package com.higroup.Buda.api.ingredient.update;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.IngredientRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.util.BeanUtils.NullAwareBeanUtilsBean;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class IngredientUpdateService {
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;

    @Autowired
    public IngredientUpdateService(IngredientRepository ingredientRepository, UserRepository userRepository) {
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Ingredient editIngredient(Long userID, Long ingredientID, Ingredient ingredient)
            throws InvocationTargetException, IllegalAccessException {

        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Optional<Ingredient> dest_ingredient = this.ingredientRepository.findIngredientByIngredientID(ingredientID);

        if (dest_ingredient.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found");
        if (ingredient.getIngredientSKU() != null) {
            Ingredient oldSKUIngredient = this.ingredientRepository.findIngredientByUserIDAndIngredientSKU(userID,
                    ingredient.getIngredientSKU());
            if ((oldSKUIngredient != null)
                    && (!oldSKUIngredient.getIngredientID().equals(dest_ingredient.get().getIngredientID()))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "This SKU has been used for another ingredient");
            }
        }
        if (Objects.equals(dest_ingredient.get().getUserID(), userID)) {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            // ingredient.setIngredientSKU(dest_ingredient.get().getIngredientSKU());
            notNull.copyProperties(dest_ingredient.get(), ingredient);
            this.ingredientRepository.save(dest_ingredient.get());
            return dest_ingredient.get();
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
    }
}
