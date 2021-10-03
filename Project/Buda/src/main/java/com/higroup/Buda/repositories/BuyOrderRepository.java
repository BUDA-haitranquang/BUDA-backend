package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;


import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyOrderRepository extends JpaRepository<BuyOrder, Long> {
    Optional<BuyOrder> findBuyOrderByBuyOrderID(Long buyOrderID);
    List<BuyOrder> findAllBuyOrderByUserID(Long userID);
    List<BuyOrder> findAllBuyOrderBySupplier(Supplier supplier);
}
