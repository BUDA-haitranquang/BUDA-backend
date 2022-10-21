package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long>{

    public interface ViewProductInfo{
        Long getUserID();
        Long getProductID();
        String getProductSKU();
        String getName();
        Double getSellingPrice();
        Integer getAmountLeft();
        Integer AlertAmount();
        Double getCostPerUnit();
        String getDescription();
    }

    @Query("select p from Product p LEFT JOIN FETCH p.picture pi where p.productID = :productID and p.visible = true")
    Optional<Product> findProductByProductID(Long productID);
    @Query("select p from Product p LEFT JOIN FETCH p.picture pi where p.userID = :userID and p.visible = true")
    List<ViewProductInfo> findAllFilterProductByUserID(@Param("userID") Long userID, Pageable pageable);
    @Query("select p from Product p LEFT JOIN FETCH p.picture pi where p.userID = :userID and p.visible = true")
    List<Product> findAllProductByUserID(@Param("userID") Long userID);
    @Query("select p from Product p where p.userID = :userID and p.visible = false")
    List<Product> findAllHiddenProductByUserID(@Param("userID") Long userID);
    @Query(value = "select * from product p where p.product_id in (select pc.product_id from product_component pc where pc.ingredient_id = :ingredientID)", nativeQuery = true)
    List<Product> findAllProductContainIngredient(@Param("ingredientID") Long ingredientID);
    @Query(value = "select p from Product p where p.userID = :userID and p.amountLeft <= p.alertAmount")
    List<Product> findAlertAmountProduct(@Param("userID") Long userID);
    @Query(value = "select * from product p where p.product_id in (select pc.product_id from product_group_component pc where pc.product_group_id = :productGroupID) and p.user_id = :userID", nativeQuery = true)
    List<Product> findAllProductByProductGroup(@Param("productGroupID") Long productGroupID, @Param("userID") Long userID);
    @Query(value = "select * from product p where p.product_id in "
    + "(select pci.product_id from product_combo_item pci where pci.product_combo_id = :productComboID) and p.user_id = :userID", nativeQuery = true)
    List<Product> findAllProductByProductCombo(@Param("userID") Long userID, @Param("productComboID") Long productComboID);
    Product findProductByUserIDAndProductSKU(Long userID, String productSKU);
    @Query(value = "select count(p.productID) from Product p where p.userID = :userID")
    Long findNumberOfProductByUserID(@Param("userID") Long userID);
}
