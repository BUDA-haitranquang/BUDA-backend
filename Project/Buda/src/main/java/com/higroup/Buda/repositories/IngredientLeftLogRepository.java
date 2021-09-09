package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.IngredientLeftLog;
import com.higroup.Buda.entities.Ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientLeftLogRepository extends JpaRepository<IngredientLeftLog, Long> {
    Optional<IngredientLeftLog> findIngredientLeftLogByIngredientLeftLogID(Long ingredientLeftLogID);
    List<IngredientLeftLog> findAllIngredientLeftLogByStaffID(Long staffID);
    List<IngredientLeftLog> findAllIngredientLeftLogByUserID(Long userID);
    List<IngredientLeftLog> findAllIngredientLeftLogByIngredient(Ingredient ingredient);
}