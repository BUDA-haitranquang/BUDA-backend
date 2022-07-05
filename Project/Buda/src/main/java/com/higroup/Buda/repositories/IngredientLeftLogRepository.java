package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.IngredientLeftLog;
import com.higroup.Buda.entities.Ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IngredientLeftLogRepository extends JpaRepository<IngredientLeftLog, Long> {
    public interface ViewIngredientLeftLogInfo{
        Ingredient getIngredient();
        interface Ingredient{
            Long getUserID();
            String getIngredientSKU();
            String getName();
            String getAmountLeft();
        }
        
    }
    
    @Query(value = "select i from IngredientLeftLog i " + " LEFT JOIN FETCH i.ingredient ii" + 
    " LEFT JOIN FETCH ii.picture" + 
    " where i.ingredientLeftLogID = :ingredientLeftLogID")
    Optional<IngredientLeftLog> findIngredientLeftLogByIngredientLeftLogID(Long ingredientLeftLogID);
    @Query(value = "select i from IngredientLeftLog i " + " LEFT JOIN FETCH i.ingredient ii" + 
    " LEFT JOIN FETCH ii.picture" + 
    " where i.userID = :userID and i.staffID = :staffID")
    List<IngredientLeftLog> findAllIngredientLeftLogByStaffID(@Param("userID") Long userID, @Param("staffID") Long staffID);
    @Query(value = "select i from IngredientLeftLog i " + " LEFT JOIN FETCH i.ingredient ii" + 
    " LEFT JOIN FETCH ii.picture" + 
    " where i.userID = :userID")
    List<ViewIngredientLeftLogInfo> findAllFilterIngredientLeftLogByUserID(Long userID);
    @Query(value = "select i from IngredientLeftLog i " + " LEFT JOIN FETCH i.ingredient ii" + 
    " LEFT JOIN FETCH ii.picture" + 
    " where i.userID = :userID")
    List<IngredientLeftLog> findAllIngredientLeftLogByUserID(Long userID);
    @Query(value = "select i from IngredientLeftLog i " + " LEFT JOIN FETCH i.ingredient ii" + 
    " LEFT JOIN FETCH ii.picture" + 
    " where i.userID = :userID" +
    " and i.ingredient = :ingredient")
    List<IngredientLeftLog> findAllIngredientLeftLogByIngredient(@Param("userID") Long userID, Ingredient ingredient);
}