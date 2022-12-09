package com.higroup.Buda.api.business.buy.view;

import java.time.ZonedDateTime;
import java.util.List;

import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.enumeration.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ViewBuyOrderRepository extends PagingAndSortingRepository<BuyOrder, Long> {
        @Query(value = "select count (*) from BuyOrder b" +
                        " where b.userID= :userID"+
                        " and supplier.name LIKE %:supplierName%")
        Long countBuyOrderBySupplierName(@Param("userID")Long userID, @Param("supplierName")String supplierName);
        @Query(value = "select distinct b from BuyOrder b" +
        " LEFT JOIN FETCH b.buyOrderItems bo" +
        " LEFT JOIN FETCH bo.ingredient ingr " +
        " LEFT JOIN FETCH ingr.picture " +
        " LEFT JOIN FETCH b.supplier s" +
        " LEFT JOIN FETCH b.staff st" +
        // " LEFT JOIN FETCH st.roles" +
        " where b.userID= :userID"+
        " and s.name LIKE %:supplierName%")
        List<BuyOrder> findBuyOrderBySupplierName(@Param("userID")Long userID, @Param("supplierName")String supplierName, Pageable pageable);

        @Query(value = "select count (*) from BuyOrder b" +
                        " where b.userID = :userID and b.textID = :textID")
        Long countBuyOrderByUserIDAndTextID(@Param("userID")Long userID, @Param("textID")String textID);

        @Query(value = "select count (*) from BuyOrder b " +
                " where b.status LIKE :status and b.userID = :userID")
        Long countBuyOrderByStatusAndUserID(@Param("status")Status status, @Param("userID")Long userID);

        @Query(value = "select distinct b from BuyOrder b" +
        " LEFT JOIN FETCH b.buyOrderItems bo" +
        " LEFT JOIN FETCH bo.ingredient ingr " +
        " LEFT JOIN FETCH ingr.picture " +
        " LEFT JOIN FETCH b.supplier s" +
        " LEFT JOIN FETCH b.staff st" +
        // " LEFT JOIN FETCH st.roles" +
        " where b.userID= :userID"+
        " and b.creationTime>= :from"+
        " and b.creationTime<= :to")
        List<BuyOrder> findBuyOrderInPeriod(@Param("userID") Long userID, ZonedDateTime from, ZonedDateTime to, Pageable pageable);

        @Query(value = "select count(*) from BuyOrder b" +
        " where b.userID= :userID"+
        " and b.creationTime>= :from"+
        " and b.creationTime<= :to")
        Long countBuyOrderInPeriod(@Param("userID") Long userID, ZonedDateTime from, ZonedDateTime to);
        @Query(value = "select distinct b from BuyOrder b" +
        " LEFT JOIN FETCH b.buyOrderItems bo" +
        " LEFT JOIN FETCH bo.ingredient ingr " +
        " LEFT JOIN FETCH ingr.picture " +
        " LEFT JOIN FETCH b.supplier s" +
        " LEFT JOIN FETCH b.staff st" +
        // " LEFT JOIN FETCH st.roles" +
        " where b.userID= :userID"+
        " and (:from IS NULL or b.creationTime>= :from)"+
        " and (:to IS NULL or b.creationTime<= :to)"+
        " and (:supplierName IS NULL or s.name LIKE %:supplierName%)"+
        " and (:textID IS NULL or b.textID = :textID)", 
        countQuery = "select count(distinct b) from BuyOrder b" +
  
        " LEFT JOIN  b.supplier s" +
        // " LEFT JOIN FETCH st.roles" +
        " where b.userID= :userID"+
        " and (:from IS NULL or b.creationTime>= :from)"+
        " and (:to IS NULL or b.creationTime<= :to)"+
        " and (:supplierName IS NULL or s.name LIKE %:supplierName%)"+
        " and (:textID IS NULL or b.textID = :textID)")
        Page<BuyOrder> findBuyOrderByFilter(@Param("userID")Long userID, ZonedDateTime from, ZonedDateTime to
        , @Param("supplierName") String supplierName, @Param("textID") String textID, Pageable pageable);

}
