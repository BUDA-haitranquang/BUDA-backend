package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long>{
    Product findProductByProductID(Long productID);
    @Query("select p from Product p where p.userID = :userID and p.visible = true")
    List<Product> findAllProductByUserID(@Param("userID") Long userID);
    @Query("select p from Product p where p.userID = :userID and p.visible = false")
    List<Product> findAllHiddenProductByUserID(@Param("userID") Long userID);
    @Query(value = "select * from product p where p.product_id in (select pc.product_id from product_component pc where pc.ingredient_id = :ingredientID)", nativeQuery = true)
    List<Product> findAllProductContainIngredient(@Param("ingredientID") Long ingredientID);
    @Query(value = "select p from Product p where p.userID = :userID and p.amountLeft <= p.alertAmount")
    List<Product> findAlertAmountProduct(@Param("userID") Long userID);
    @Query(value = "select * from product p where p.product_id in (select pc.product_id from product_group_component pc where pc.product_group_id = :productGroupID) and p.user_id = :userID", nativeQuery = true)
    List<Product> findAllProductByProductGroup(@Param("productGroupID") Long productGroupID, @Param("userID") Long userID);
}
