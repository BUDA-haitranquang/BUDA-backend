package com.higroup.Buda.api.statistics.product.overall;

import java.util.List;

import com.higroup.Buda.customDTO.ProductStatistics;
import com.higroup.Buda.entities.SellOrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductStatisticsRepository extends JpaRepository<SellOrderItem, Long>{
    @Query(value = "select new com.higroup.Buda.customDTO.ProductStatistics(p.productID, p.name, SUM(s.actualTotalSale))"
    + " FROM Product p LEFT JOIN SellOrderItem s"
    + " ON p.productID = s.product.productID"
    + " WHERE p.userID = :userID"
    + " GROUP BY p.productID")
    List<ProductStatistics> findTotalRevenueOfAllProductByUserID(Long userID);
}
