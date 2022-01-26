package com.higroup.Buda.repositories;

import java.util.List;

import com.higroup.Buda.customDTO.ProductRanking;
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

    @Query(value="select new com.higroup.Buda.customDTO.ProductRanking(i.product_id, p.name, "
    + "sum(if(s.status != 'RETURNED', i.price_per_unit * i.quantity, 0)) as revenue, "
    + "sum(if(s.status != 'RETURNED', (i.price_per_unit - i.cost_per_unit) * i.quantity, null)) as profit, "
    + "sum(if(s.status != 'RETURNED', i.quantity, 0)) as sellNumber, "
    + "sum(if(s.status = 'RETURNED', i.quantity, 0)) as returnNumber, "
    + "sum(if(s.status = 'RETURNED', i.price_per_unit * i.quantity, 0)) as returnPrice) "
    + "from sell_order_item i " + "inner join product p on i.product_id = p.product_id " + "inner join sell_order s on s.sell_order_id = i.sell_order_id "
    + "where i.user_id = :userID and month(current_date()) = month(i.creation_time) and year(current_date()) = year(i.creation_time) "
    + "group by i.product_id "
    + "order by sellNumber desc, p.name asc "
    + "limit :number", nativeQuery = true)
    List<ProductRanking> findNProductsTopSellNumber(@Param("userID") Long userID, @Param("number") Integer number);

    @Query(value="select new com.higroup.Buda.customDTO.ProductRanking(i.product_id, p.name, "
    + "sum(if(s.status != 'RETURNED', i.price_per_unit * i.quantity, 0)) as revenue, "
    + "sum(if(s.status != 'RETURNED', (i.price_per_unit - i.cost_per_unit) * i.quantity, null)) as profit, "
    + "sum(if(s.status != 'RETURNED', i.quantity, 0)) as sellNumber, "
    + "sum(if(s.status = 'RETURNED', i.quantity, 0)) as returnNumber, "
    + "sum(if(s.status = 'RETURNED', i.price_per_unit * i.quantity, 0)) as returnPrice) "
    + "from sell_order_item i " + "inner join product p on i.product_id = p.product_id " + "inner join sell_order s on s.sell_order_id = i.sell_order_id "
    + "where i.user_id = :userID and month(current_date()) = month(i.creation_time) and year(current_date()) = year(i.creation_time) "
    + "group by i.product_id "
    + "order by revenue desc, p.name asc "
    + "limit :number", nativeQuery = true)
    List<ProductRanking> findNProductsTopRevenue(@Param("userID") Long userID, @Param("number") Integer number);

    @Query(value="select new com.higroup.Buda.customDTO.ProductRanking(i.product_id, p.name, "
    + "sum(if(s.status != 'RETURNED', i.price_per_unit * i.quantity, 0)) as revenue, "
    + "sum(if(s.status != 'RETURNED', (i.price_per_unit - i.cost_per_unit) * i.quantity, null)) as profit, "
    + "sum(if(s.status != 'RETURNED', i.quantity, 0)) as sellNumber, "
    + "sum(if(s.status = 'RETURNED', i.quantity, 0)) as returnNumber, "
    + "sum(if(s.status = 'RETURNED', i.price_per_unit * i.quantity, 0)) as returnPrice) "
    + "from sell_order_item i " + "inner join product p on i.product_id = p.product_id " + "inner join sell_order s on s.sell_order_id = i.sell_order_id "
    + "where i.user_id = :userID and month(current_date()) = month(i.creation_time) and year(current_date()) = year(i.creation_time) "
    + "group by i.product_id "
    + "order by profit desc, p.name asc "
    + "limit :number", nativeQuery = true)
    List<ProductRanking> findNProductsTopProfit(@Param("userID") Long userID, @Param("number") Integer number);

    @Query(value="select new com.higroup.Buda.customDTO.ProductRanking(i.product_id, p.name, "
    + "sum(if(s.status != 'RETURNED', i.price_per_unit * i.quantity, 0)) as revenue, "
    + "sum(if(s.status != 'RETURNED', (i.price_per_unit - i.cost_per_unit) * i.quantity, null)) as profit, "
    + "sum(if(s.status != 'RETURNED', i.quantity, 0)) as sellNumber, "
    + "sum(if(s.status = 'RETURNED', i.quantity, 0)) as returnNumber, "
    + "sum(if(s.status = 'RETURNED', i.price_per_unit * i.quantity, 0)) as returnPrice) "
    + "from sell_order_item i " + "inner join product p on i.product_id = p.product_id " + "inner join sell_order s on s.sell_order_id = i.sell_order_id "
    + "where i.user_id = :userID and month(current_date()) = month(i.creation_time) and year(current_date()) = year(i.creation_time) "
    + "group by i.product_id "
    + "order by revenue asc, p.name asc "
    + "limit :number", nativeQuery = true)
    List<ProductRanking> findNProductsLeastRevenue(@Param("userID") Long userID, @Param("number") Integer number);

    @Query(value="select new com.higroup.Buda.customDTO.ProductRanking(i.product_id, p.name, "
    + "sum(if(s.status != 'RETURNED', i.price_per_unit * i.quantity, 0)) as revenue, "
    + "sum(if(s.status != 'RETURNED', (i.price_per_unit - i.cost_per_unit) * i.quantity, null)) as profit, "
    + "sum(if(s.status != 'RETURNED', i.quantity, 0)) as sellNumber, "
    + "sum(if(s.status = 'RETURNED', i.quantity, 0)) as returnNumber, "
    + "sum(if(s.status = 'RETURNED', i.price_per_unit * i.quantity, 0)) as returnPrice) "
    + "from sell_order_item i " + "inner join product p on i.product_id = p.product_id " + "inner join sell_order s on s.sell_order_id = i.sell_order_id "
    + "where i.user_id = :userID and month(current_date()) = month(i.creation_time) and year(current_date()) = year(i.creation_time) "
    + "group by i.product_id "
    + "order by sellNumber asc, p.name asc "
    + "limit :number", nativeQuery = true)
    List<ProductRanking> findNProductsLeastSellNumber(@Param("userID") Long userID, @Param("number") Integer number);

    @Query(value="select new com.higroup.Buda.customDTO.ProductRanking(i.product_id, p.name, "
    + "sum(if(s.status != 'RETURNED', i.price_per_unit * i.quantity, 0)) as revenue, "
    + "sum(if(s.status != 'RETURNED', (i.price_per_unit - i.cost_per_unit) * i.quantity, null)) as profit, "
    + "sum(if(s.status != 'RETURNED', i.quantity, 0)) as sellNumber, "
    + "sum(if(s.status = 'RETURNED', i.quantity, 0)) as returnNumber, "
    + "sum(if(s.status = 'RETURNED', i.price_per_unit * i.quantity, 0)) as returnPrice) "
    + "from sell_order_item i " + "inner join product p on i.product_id = p.product_id " + "inner join sell_order s on s.sell_order_id = i.sell_order_id "
    + "where i.user_id = :userID and month(current_date()) = month(i.creation_time) and year(current_date()) = year(i.creation_time) "
    + "group by i.product_id "
    + "order by profit asc, p.name asc "
    + "limit :number", nativeQuery = true)
    List<ProductRanking> findNProductsLeastProfit(@Param("userID") Long userID, @Param("number") Integer number);

    @Query(value="select new com.higroup.Buda.customDTO.ProductRanking(i.product_id, p.name, "
    + "sum(if(s.status != 'RETURNED', i.price_per_unit * i.quantity, 0)) as revenue, "
    + "sum(if(s.status != 'RETURNED', (i.price_per_unit - i.cost_per_unit) * i.quantity, null)) as profit, "
    + "sum(if(s.status != 'RETURNED', i.quantity, 0)) as sellNumber, "
    + "sum(if(s.status = 'RETURNED', i.quantity, 0)) as returnNumber, "
    + "sum(if(s.status = 'RETURNED', i.price_per_unit * i.quantity, 0)) as returnPrice) "
    + "from sell_order_item i " + "inner join product p on i.product_id = p.product_id " + "inner join sell_order s on s.sell_order_id = i.sell_order_id "
    + "where i.user_id = :userID and month(current_date()) = month(i.creation_time) and year(current_date()) = year(i.creation_time) "
    + "group by i.product_id "
    + "order by returnNumber desc, p.name asc "
    + "limit :number", nativeQuery = true)
    List<ProductRanking> findNProductsMostReturnNumber(@Param("userID") Long userID, @Param("number") Integer number);

    @Query(value="select new com.higroup.Buda.customDTO.ProductRanking(i.product_id, p.name, "
    + "sum(if(s.status != 'RETURNED', i.price_per_unit * i.quantity, 0)) as revenue, "
    + "sum(if(s.status != 'RETURNED', (i.price_per_unit - i.cost_per_unit) * i.quantity, null)) as profit, "
    + "sum(if(s.status != 'RETURNED', i.quantity, 0)) as sellNumber, "
    + "sum(if(s.status = 'RETURNED', i.quantity, 0)) as returnNumber, "
    + "sum(if(s.status = 'RETURNED', i.price_per_unit * i.quantity, 0)) as returnPrice) "
    + "from sell_order_item i " + "inner join product p on i.product_id = p.product_id " + "inner join sell_order s on s.sell_order_id = i.sell_order_id "
    + "where i.user_id = :userID and month(current_date()) = month(i.creation_time) and year(current_date()) = year(i.creation_time) "
    + "group by i.product_id "
    + "order by returnPrice desc, p.name asc "
    + "limit :number", nativeQuery = true)
    List<ProductRanking> findNProductsMostReturnPrice(@Param("userID") Long userID, @Param("number") Integer number);

    


}
