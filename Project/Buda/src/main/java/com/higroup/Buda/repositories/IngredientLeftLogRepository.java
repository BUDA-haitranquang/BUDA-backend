package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.IngredientLeftLog;
import com.higroup.Buda.entities.Ingredient;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IngredientLeftLogRepository extends JpaRepository<IngredientLeftLog, Long> {


    public interface IngredientLeftLogRemoveAmount{
        Ingredient getIngredient();
        interface Ingredient{
            Long getIngredient();
        }
        Integer getAmount();

    }

    public interface ViewIngredientLeftLogInfo{
        Ingredient getIngredient();
        interface Ingredient{
            Long getUserID();
            Long getIngredientID();
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
    List<ViewIngredientLeftLogInfo> findAllFilterIngredientLeftLogByUserID(Long userID, Pageable pageable);
    @Query(value = "select i from IngredientLeftLog i " + " LEFT JOIN FETCH i.ingredient ii" + 
    " LEFT JOIN FETCH ii.picture" + 
    " where i.userID = :userID")
    List<IngredientLeftLog> findAllIngredientLeftLogByUserID(Long userID);
    @Query(value = "select i from IngredientLeftLog i " + " LEFT JOIN FETCH i.ingredient ii" + 
    " LEFT JOIN FETCH ii.picture" + 
    " where i.userID = :userID" +
    " and i.ingredient = :ingredient")
    List<IngredientLeftLog> findAllIngredientLeftLogByIngredient(@Param("userID") Long userID, Ingredient ingredient);


    @Query(value = "select i.ingredient_id, -sum(i.amount_left_change) as amount from ingredient_left_log i "
        + "where i.leftlog_type = 'REMOVE' and i.user_id = :userID "
        + "group by i.ingredient_id "
        + "order by amount desc;"
    , nativeQuery = true)
    List<IngredientLeftLogRemoveAmount> getMostRemovedIngredient(@Param("userID") Long userID);
}