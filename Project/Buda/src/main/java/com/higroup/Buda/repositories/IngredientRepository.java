package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    public interface ViewIngredientInfo{
        Long getUserID();
        String getIngredientSKU();
        String getName();
        Double getPrice();
        Integer getAmountLeft();
        Integer getAlertAmountLeft();
        String getDescription();
    }

    @Query("select i from Ingredient i left join fetch i.picture p where i.visible = true and i.ingredientID = :ingredientID")
    Optional<Ingredient> findIngredientByIngredientID(Long ingredientID);
    @Query("select i from Ingredient i left join fetch i.picture p where i.userID = :userID and i.visible = true")  
    List<ViewIngredientInfo> findAllFilterIngredientByUserID(@Param("userID") Long userID);
    @Query("select i from Ingredient i left join fetch i.picture p where i.userID = :userID and i.visible = true")  
    List<Ingredient> findAllIngredientByUserID(@Param("userID") Long userID);
    @Query("select i from Ingredient i where i.userID = :userID and i.visible = false")
    List<Ingredient> findAllHiddenIngredientByUserID(@Param("userID") Long userID);
    @Query(value = "select i from Ingredient i where i.user_id = :userID and i.name LIKE :name", nativeQuery = true)
    List<Ingredient> findIngredientByName(@Param("userID") Long userID, @Param("name") String name);
    @Query(value = "select i from Ingredient i where i.userID = :userID and i.amountLeft <= i.alertAmountLeft")
    List<Ingredient> findAlertAmountIngredient(@Param("userID") Long userID);
    Ingredient findIngredientByUserIDAndIngredientSKU(Long userID, String ingredientSKU);
    @Query(value = "select count(i.ingredientID) from Ingredient i where i.userID = :userID")
    Long findNumberOfIngredientByUserID(@Param("userID") Long userID);
}

