package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Ingredient;

import org.springframework.data.jpa.repository.JpaRepository;


public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findIngredientByIngredientID(Long ingredientID);  
    List<Ingredient> findAllIngredientByuserID(Long userID);
    Optional<Ingredient> findIngredientByName(String name);
}

