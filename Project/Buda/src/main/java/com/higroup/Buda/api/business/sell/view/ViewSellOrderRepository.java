package com.higroup.Buda.api.business.sell.view;

import java.time.ZonedDateTime;
import java.util.List;

import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.enumeration.Status;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ViewSellOrderRepository extends PagingAndSortingRepository<SellOrder, Long> {
    @Query(value = "select count (*) from SellOrder s " +
            "where s.userID = :userID and s.textID = :textID")
    Long countAllSellOrderByUserIDAndTextID(Long userID, String textID);

    @Query(value = "select s from SellOrder s "
            + "LEFT JOIN FETCH s.sellOrderItems si "
            + "LEFT JOIN FETCH si.product pr "
            + "LEFT JOIN FETCH pr.picture "
            + "LEFT JOIN FETCH s.customer c "
            + "LEFT JOIN FETCH s.staff ss "
            + "where s.userID = :userID "
            + "and c.name LIKE %:customerName%")
    List<SellOrder> findAllSellOrderByUserIDAndCustomerName(Long userID, String customerName);

    @Query(value = "select count (*) from SellOrder s "
            + "where s.userID = :userID and customer.name LIKE %:customerName%")
    Long countAllSellOrderByUserIDAndCustomerName(Long userID, String customerName);

    @Query(value = "select count (*) from SellOrder s "
            + "where s.userID = :userID and s.status LIKE :status")
    Long countAllSellOrderByUserIDAndStatus(Long userID, Status status);

    @Query(value = "select distinct s from SellOrder s "
            + "LEFT JOIN FETCH s.sellOrderItems si "
            + "LEFT JOIN FETCH si.product pr "
            + "LEFT JOIN FETCH pr.picture "
            + "LEFT JOIN FETCH s.customer c "
            + "LEFT JOIN FETCH s.staff ss "
            + "where s.userID = :userID "
            + "and s.creationTime >= :from "
            + "and s.creationTime <= :to")
    List<SellOrder> findAllSellOrderInPeriod(Long userID, ZonedDateTime from, ZonedDateTime to);

    @Query(value = "select count(*) from SellOrder s "
            + "where s.userID = :userID and s.creationTime >= :from and s.creationTime <= :to")
    Long countAllSellOrderInPeriod(Long userID, ZonedDateTime from, ZonedDateTime to);
}