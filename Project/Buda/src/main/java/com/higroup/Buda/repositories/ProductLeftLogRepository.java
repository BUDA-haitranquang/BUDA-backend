package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.ProductLeftLog;
import com.higroup.Buda.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLeftLogRepository extends JpaRepository<ProductLeftLog, Long> {
    Optional<ProductLeftLog> findProductLeftLogByProductLeftLogID(Long productLeftLogID);
    List<ProductLeftLog> findAllProductLeftLogByStaffID(Long staffID);
    List<ProductLeftLog> findAllProductLeftLogByUserID(Long userID);
    List<ProductLeftLog> findAllProductLeftLogByProduct(Product product);
}
