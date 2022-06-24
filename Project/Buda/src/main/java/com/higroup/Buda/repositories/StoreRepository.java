package com.higroup.Buda.repositories;

import java.util.List;

import com.higroup.Buda.entities.Store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{
    @Query(value = "select s from Store s where s.userID = :userID")
    List<Store> findAllByUserID(@Param("userID") Long userID);
    Store findStoreByStoreID(Long storeID);
    Store findFirstByUserID(Long userID);
}
