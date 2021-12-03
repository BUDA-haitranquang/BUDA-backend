package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findIngredientByIngredientID(Long ingredientID);
    @Query("select i from Ingredient i where i.userID = :userID and i.visible = true")  
    List<Ingredient> findAllIngredientByUserID(@Param("userID") Long userID);
    @Query("select i from Ingredient i where i.userID = :userID and i.visible = false")
    List<Ingredient> findAllHiddenIngredientByUserID(@Param("userID") Long userID);
    @Query(value = "select i from Ingredient i where i.user_id = :userID and i.name LIKE :name", nativeQuery = true)
    List<Ingredient> findIngredientByName(@Param("userID") Long userID, @Param("name") String name);
    @Query(value = "select i from Ingredient i where i.amountLeft <= i.alertAmountLeft")
    List<Ingredient> findAlertAmountIngredient();
}

