package com.higroup.Buda.repositories;

import java.util.List;

import com.higroup.Buda.customDTO.ProductStatistics;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.SellOrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SellOrderItemRepository extends JpaRepository<SellOrderItem, Long>{
    List<SellOrderItem> findAllSellOrderItemBySellOrder(SellOrder sellOrder);
    @Query(value = "select * from sell_order_item i where i.product_id = :productID", nativeQuery = true)
    List<SellOrderItem> findAllSellOrderItemByProductID(@Param("productID") Long productID);
    @Query(value = "select new com.higroup.Buda.customDTO.ProductStatistics(p.productID, p.name, SUM(s.actualTotalSale))"
    + " FROM Product p LEFT JOIN SellOrderItem s"
    + " ON p.productID = s.product.productID"
    + " WHERE p.userID = :userID"
    + " GROUP BY p.productID")
    List<ProductStatistics> findTotalRevenueOfAllProductByUserID(Long userID);
}
