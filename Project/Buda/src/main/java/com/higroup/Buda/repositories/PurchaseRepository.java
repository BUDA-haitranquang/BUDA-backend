package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Purchase;
import com.higroup.Buda.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{
    List<Purchase> findAllByUser(User user);
    Optional<Purchase> findPurchaseByPurchaseID(@Param("purchaseID") Long purchaseID);
}
