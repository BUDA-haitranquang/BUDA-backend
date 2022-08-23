package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductLeftLog;

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

public interface ProductLeftLogRepository extends JpaRepository<ProductLeftLog, Long> {

    public interface ViewProductLeftLogInfo{
        Product getProduct();
        interface Product{
            Long getUserID();
            Long getProductID();
            String getProductSKU();
            String getName();
            Integer getAmountLeft();
        }
    }

    Optional<ProductLeftLog> findProductLeftLogByProductLeftLogID(Long productLeftLogID);
    @Query(value = "select * from product_left_log where user_id = :userID and staff_id = :staffID", nativeQuery = true)
    List<ProductLeftLog> findAllProductLeftLogByStaffID(@Param("userID") Long userID, @Param("staffID") Long staffID);
    @Query(value = "select * from product_left_log where user_id = :userID", nativeQuery = true)
    List<ViewProductLeftLogInfo> findAllFilterProductLeftLogByUserID(Long userID);
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
