package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;


import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.Status;
import com.higroup.Buda.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BuyOrderRepository extends JpaRepository<BuyOrder, Long> {
    Optional<BuyOrder> findBuyOrderByBuyOrderID(Long buyOrderID);
    List<BuyOrder> findAllBuyOrderByUserID(Long userID);
    List<BuyOrder> findAllBuyOrderBySupplier(Supplier supplier);
    List<BuyOrder> findAllBuyOrderByUserIDAndStatus(Long userID, Status status);
    @Query(value = "select * from buy_order s where s.status LIKE :status and s.user_id = :userID", nativeQuery = true)
    List<BuyOrder> findAllBuyOrderByStatusAndUserID(@Param("userID") Long userID, @Param("status") String status);
    @Query(value = "select * from buy_order s where (s.creation_time BETWEEN NOW() - INTERVAL :X DAY and NOW()) and s.user_id = :userID", nativeQuery = true)
    List<BuyOrder> findAllBuyOrderByUserIDLastXDays(@Param("userID") Long userID, @Param("X") Long X);
    @Query(value = "select * from buy_order s where s.status NOT LIKE 'FINISHED' and s.status NOT LIKE 'CANCELLED' and s.user_id = :userID", nativeQuery = true)
    List<BuyOrder> findAllIncompletedBuyOrderByUser(@Param("userID") Long userID);
    @Query(value = "select * from buy_order s where s.status LIKE 'FINISHED' and s.user_id = :userID", nativeQuery = true)
    List<BuyOrder> findAllCompletedBuyOrderByUser(@Param("userID") Long userID);
}
