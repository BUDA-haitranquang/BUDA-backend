package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Picture;
import com.higroup.Buda.entities.Product;

import org.springframework.data.domain.Page;
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
        Picture getPicture();
        Integer getAmountLeft();
        Integer getAlertAmount();
        Double getCostPerUnit();
        String getDescription();
    }

    @Query("select p from Product p LEFT JOIN FETCH p.picture pi where p.productID = :productID and p.visible = true")
    Optional<Product> findProductByProductID(Long productID);

    @Query(value="select distinct p from Product p "+
    "left join fetch p.picture pi "+
    "where p.userID = :userID and p.visible = true "+
    "and (:productSKU IS NULL or p.productSKU LIKE %:productSKU% )"+
    "and (:name IS NULL or p.name LIKE %:name% )"+
    "and (:sellingPrice IS NULL or p.sellingPrice =:sellingPrice )"+
    "and (:amountLeft IS NULL or p.amountLeft =:amountLeft )"+
    "and (:alertAmount IS NULL or p.alertAmount =:alertAmount )"+
    "and (:costPerUnit IS NULL or p.costPerUnit =:costPerUnit )"+
    "and (:description IS NULL or p.description LIKE %:description% )",
    countQuery = "select count (distinct p) from Product p "+
    "left join p.picture pi "+
    "where p.userID = :userID and p.visible = true "+
    "and (:productSKU IS NULL or p.productSKU LIKE %:productSKU% )"+
    "and (:name IS NULL or p.name LIKE %:name% )"+
    "and (:sellingPrice IS NULL or p.sellingPrice =:sellingPrice )"+
    "and (:amountLeft IS NULL or p.amountLeft =:amountLeft )"+
    "and (:alertAmount IS NULL or p.alertAmount =:alertAmount )"+
    "and (:costPerUnit IS NULL or p.costPerUnit =:costPerUnit )"+
    "and (:description IS NULL or p.description LIKE %:description% )")
    Page<ViewProductInfo> findAllFilterProductByUserID(Long userID, 
    String productSKU, String name, Double sellingPrice, Integer amountLeft, 
    Integer alertAmount, Double costPerUnit, String description, Pageable pageable);

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
