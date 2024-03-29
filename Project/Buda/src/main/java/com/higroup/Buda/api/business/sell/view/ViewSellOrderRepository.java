package com.higroup.Buda.api.business.sell.view;

import java.time.ZonedDateTime;
import java.util.List;

import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.fetchdefault.FetchDefault;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ViewSellOrderRepository extends PagingAndSortingRepository<SellOrder, Long> {
    @Query(value = "select count (*) from SellOrder s " +
            "where s.userID = :userID and s.textID = :textID")
    Long countAllSellOrderByUserIDAndTextID(@Param("userID") Long userID, @Param("textID") String textID);

    @Query(value = "select s from SellOrder s "
            + "LEFT JOIN FETCH s.sellOrderItems si "
            + "LEFT JOIN FETCH si.product pr "
            + "LEFT JOIN FETCH pr.picture "
            + "LEFT JOIN FETCH s.customer c "
            + "LEFT JOIN FETCH s.staff ss "
            + "where s.userID = :userID "
            + "and c.name LIKE %:customerName%")
    List<SellOrder> findAllSellOrderByUserIDAndCustomerName(@Param("userID") Long userID, @Param("customerName") String customerName, Pageable pageable);

    @Query(value = "select count (*) from SellOrder s "
            + "where s.userID = :userID and customer.name LIKE %:customerName%")
    Long countAllSellOrderByUserIDAndCustomerName(@Param("userID") Long userID, @Param("customerName") String customerName);

    @Query(value = "select count (*) from SellOrder s "
            + "where s.userID = :userID and s.status LIKE :status")
    Long countAllSellOrderByUserIDAndStatus(@Param("userID") Long userID, @Param("status") Status status);

    @Query(value = "select distinct s from SellOrder s "
            + "LEFT JOIN FETCH s.sellOrderItems si "
            + "LEFT JOIN FETCH si.product pr "
            + "LEFT JOIN FETCH pr.picture "
            + "LEFT JOIN FETCH s.customer c "
            + "LEFT JOIN FETCH s.staff ss "
            + "where s.userID = :userID "
            + "and s.creationTime >= :from "
            + "and s.creationTime <= :to")
    List<SellOrder> findAllSellOrderInPeriod(@Param("userID") Long userID, ZonedDateTime from, ZonedDateTime to, Pageable pageable);

    @Query(value = "select count(*) from SellOrder s "
            + "where s.userID = :userID and s.creationTime >= :from and s.creationTime <= :to")
    Long countAllSellOrderInPeriod(@Param("userID") Long userID, ZonedDateTime from, ZonedDateTime to);

    @Query(value = FetchDefault.sellOrder
    + "where s.userID = :userID "
    + "and (:from IS NULL or s.creationTime >= :from) "
    + "and (:to IS NULL or s.creationTime <= :to)"
    + "and (:customerName IS NULL or c.name LIKE %:customerName%) "
    + "and (:status IS NULL or s.status = :status) "
    + "and (:textID IS NULL or s.textID = :textID)",
    countQuery = "select count (distinct s) from SellOrder s left join s.customer c "
    + "where s.userID = :userID "
    + "and (:from IS NULL or s.creationTime >= :from) "
    + "and (:to IS NULL or s.creationTime <= :to)"
    + "and (:customerName IS NULL or c.name LIKE %:customerName%) "
    + "and (:status IS NULL or s.status = :status) "
    + "and (:textID IS NULL or s.textID = :textID)")
    Page<SellOrder> findAllSellOrderWithFilter(@Param("userID") Long userID, ZonedDateTime from, ZonedDateTime to, @Param("customerName") String customerName,
    @Param("status") Status status, @Param("textID") String textID, Pageable pageable);
}