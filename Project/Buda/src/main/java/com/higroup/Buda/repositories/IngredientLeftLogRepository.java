package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.IngredientLeftLog;
import com.higroup.Buda.entities.Ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IngredientLeftLogRepository extends JpaRepository<IngredientLeftLog, Long> {
    Optional<IngredientLeftLog> findIngredientLeftLogByIngredientLeftLogID(Long ingredientLeftLogID);
    @Query(value = "select * from ingredient_left_log where user_id = :userID and staff_id = :staffID", nativeQuery = true)
    List<IngredientLeftLog> findAllIngredientLeftLogByStaffID(@Param("userID") Long userID, @Param("staffID") Long staffID);
    List<IngredientLeftLog> findAllIngredientLeftLogByUserID(Long userID);
    @Query(value = "select * from ingredient_left_log where user_id = :userID and ingredient_id = :ingredientID", nativeQuery = true)
    List<IngredientLeftLog> findAllIngredientLeftLogByIngredient(@Param("userID") Long userID, @Param("ingredientID") Long ingredientID);
}