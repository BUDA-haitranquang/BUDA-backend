package com.higroup.Buda.repositories;

import java.util.List;

import com.higroup.Buda.entities.Receipt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReceiptRepository extends JpaRepository<Receipt, Long>{
    @Query("select r from Receipt r where userID = :userID")
    List<Receipt> findAllByUserID(@Param("userID") Long userID);
}
