package com.higroup.Buda.repositories;

import com.higroup.Buda.entities.Receipt;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long>{
    
}
