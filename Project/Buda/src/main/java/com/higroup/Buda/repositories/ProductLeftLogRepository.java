package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Picture;
import com.higroup.Buda.entities.ProductLeftLog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductLeftLogRepository extends JpaRepository<ProductLeftLog, Long> { 

    public interface ProductLeftLogRemoveAmount{
        Product getProduct();
        interface Product{
            Long getProductID();
        }
        Integer getAmount();

    }

    public interface ViewProductLeftLogInfo{
        Product getProduct();
        interface Product{
            Long getUserID();
            Long getProductID();
            Picture getPicture();
            String getProductSKU();
            String getName();
            Integer getAmountLeft();
        }
    }

    Optional<ProductLeftLog> findProductLeftLogByProductLeftLogID(Long productLeftLogID);
    @Query(value = "select * from product_left_log where user_id = :userID and staff_id = :staffID", nativeQuery = true)
    List<ProductLeftLog> findAllProductLeftLogByStaffID(@Param("userID") Long userID, @Param("staffID") Long staffID);

    @Query(value = "select p from ProductLeftLog p "+ "LEFT JOIN FETCH p.product pp" +
    " LEFT JOIN FETCH pp.picture " + 
    " where p.userID = :userID " +
    " and (:productSKU IS NULL or pp.productSKU LIKE %:productSKU% )" +
    " and (:name IS NULL or pp.name LIKE %:name% )" +
    " and (:amountLeft IS NULL or pp.amountLeft LIKE %:amountLeft% )", 
    countQuery = "select count (distinct p) from ProductLeftLog p "  + "LEFT JOIN p.product pp" +
    " LEFT JOIN pp.picture" + 
    " where p.userID = :userID " +
    " and (:productSKU IS NULL or pp.productSKU LIKE %:productSKU%)" +
    " and (:name IS NULL or pp.name LIKE %:name% )" +
    " and (:amountLeft IS NULL or pp.amountLeft LIKE %:amountLeft% )")
    Page<ViewProductLeftLogInfo> findAllFilterproductLeftLogByUserID(Long userID, 
    String productSKU, String name, Integer amountLeft, Pageable pageable);

    List<ProductLeftLog> findAllProductLeftLogByUserID(Long userID);
    @Query(value = "select * from product_left_log where user_id = :userID and product_id = :productID", nativeQuery = true)
    List<ProductLeftLog> findAllProductLeftLogByProduct(@Param("userID") Long userID, @Param("productID") Long productID);


    @Query(value = "select p.product_id, -sum(p.amount_left_change) as amount from product_left_log p "
        + "where p.leftlog_type = 'REMOVE' and p.user_id = :userID "
        + "group by p.product_id "
        + "order by amount desc;"
    , nativeQuery = true)
    List<ProductLeftLogRemoveAmount> getMostRemovedProduct(@Param("userID") Long userID);
}
