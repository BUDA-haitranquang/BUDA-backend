package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.Picture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    public interface ViewIngredientInfo{
        Long getUserID();
        Long getIngredientID();
        String getIngredientSKU();
        String getName();
        Double getPrice();
        Integer getAmountLeft();
        Integer getAlertAmountLeft();
        Picture getPicture();
        String getDescription();
    }

    @Query("select i from Ingredient i left join fetch i.picture p where i.visible = true and i.ingredientID = :ingredientID")
    Optional<Ingredient> findIngredientByIngredientID(@Param("ingredientID") Long ingredientID);

    @Query(value="select distinct i from Ingredient i "+
    "left join fetch i.picture p "+
    "where i.userID = :userID and i.visible = true "+
    "and (:ingredientSKU IS NULL or i.ingredientSKU LIKE %:ingredientSKU% )"+
    "and (:name IS NULL or i.name LIKE %:name% )"+
    "and (:price IS NULL or i.price =:price )"+
    "and (:amountLeft IS NULL or i.amountLeft =:amountLeft )"+
    "and (:alertAmountLeft IS NULL or i.alertAmountLeft =:alertAmountLeft )"+
    "and (:description IS NULL or i.description LIKE %:description% )",
    countQuery = "select count (distinct i) from Ingredient i " + 
    "left join i.picture p " + 
    "where i.userID = :userID " +
    "and (i.visible = true ) "+
    "and (:ingredientSKU IS NULL or i.ingredientSKU LIKE %:ingredientSKU% )"+
    "and (:name IS NULL or i.name LIKE %:name% )"+
    "and (:price IS NULL or i.price =:price )"+
    "and (:amountLeft IS NULL or i.amountLeft = :amountLeft )"+
    "and (:alertAmountLeft IS NULL or i.alertAmountLeft = :alertAmountLeft )"+
    "and (:description IS NULL or i.description LIKE %:description% )")
    Page<ViewIngredientInfo> findAllFilterIngredient(@Param("userID") Long userID, 
    @Param("ingredientSKU") String ingredientSKU, @Param("name") String name, 
    @Param("price") Double price, @Param("amountLeft") Integer amountLeft,
    @Param("alertAmountLeft") Integer alertAmountLeft, 
    @Param("description") String description, Pageable pageable);

    @Query("select i from Ingredient i left join fetch i.picture p where i.userID = :userID and i.visible = true")  
    List<Ingredient> findAllIngredientByUserID(@Param("userID") Long userID);
    @Query("select i from Ingredient i where i.userID = :userID and i.visible = false")
    List<Ingredient> findAllHiddenIngredientByUserID(@Param("userID") Long userID);
    @Query(value = "select i from Ingredient i where i.user_id = :userID and i.name LIKE :name", nativeQuery = true)
    List<Ingredient> findIngredientByName(@Param("userID") Long userID, @Param("name") String name);
    @Query(value = "select i from Ingredient i where i.userID = :userID and i.amountLeft <= i.alertAmountLeft")
    List<Ingredient> findAlertAmountIngredient(@Param("userID") Long userID);
    Ingredient findIngredientByUserIDAndIngredientSKU(@Param("userID") Long userID, @Param("ingredientSKU") String ingredientSKU);
    @Query(value = "select count(i.ingredientID) from Ingredient i where i.userID = :userID")
    Long findNumberOfIngredientByUserID(@Param("userID") Long userID);
}

