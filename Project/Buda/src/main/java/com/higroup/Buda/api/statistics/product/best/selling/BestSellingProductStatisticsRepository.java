package com.higroup.Buda.api.statistics.product.best.selling;

import java.util.List;

import com.higroup.Buda.entities.SellOrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BestSellingProductStatisticsRepository extends JpaRepository<SellOrderItem, Long>{
    public interface ProductRanking{
        Long getProductID();
        String getName();
        Double getRevenue();
        Double getProfit();
        Integer getSellNumber();
        Integer getReturnNumber();
        Double getReturnPrice();
    }

    @Query(value="select i.product_id as productID, p.name as name, "
    + "sum(if(s.status = 'FINISHED', i.price_per_unit * i.quantity, 0)) as revenue, "
    + "sum(if(s.status = 'FINISHED', (i.price_per_unit - i.cost_per_unit) * i.quantity, null)) as profit, "
    + "sum(if(s.status = 'FINISHED', i.quantity, 0)) as sellNumber, "
    + "sum(if(s.status = 'RETURNED', i.quantity, 0)) as returnNumber, "
    + "sum(if(s.status = 'RETURNED', i.price_per_unit * i.quantity, 0)) as returnPrice "
    + "from sell_order_item i " + "inner join product p on i.product_id = p.product_id " + "inner join sell_order s on s.sell_order_id = i.sell_order_id "
    + "where i.user_id = :userID and s.finish_time BETWEEN DATE_SUB(NOW(), INTERVAL 180 DAY) and now() "
    + "group by i.product_id "
    + "having profit is not null and revenue is not null "
    + "order by sellNumber desc, p.name asc "
    + "limit :number", nativeQuery = true)
    List<ProductRanking> findNProductsTopSellNumber(@Param("userID") Long userID, @Param("number") Integer number);

    @Query(value="select i.product_id as productID, p.name as name, "
    + "sum(if(s.status = 'FINISHED', i.price_per_unit * i.quantity, 0)) as revenue, "
    + "sum(if(s.status = 'FINISHED', (i.price_per_unit - i.cost_per_unit) * i.quantity, null)) as profit, "
    + "sum(if(s.status = 'FINISHED', i.quantity, 0)) as sellNumber, "
    + "sum(if(s.status = 'RETURNED', i.quantity, 0)) as returnNumber, "
    + "sum(if(s.status = 'RETURNED', i.price_per_unit * i.quantity, 0)) as returnPrice "
    + "from sell_order_item i " + "inner join product p on i.product_id = p.product_id " + "inner join sell_order s on s.sell_order_id = i.sell_order_id "
    + "where i.user_id = :userID and s.finish_time BETWEEN DATE_SUB(NOW(), INTERVAL 180 DAY) and now() "
    + "group by i.product_id "
    + "having profit is not null and revenue is not null "
    + "order by revenue desc, p.name asc "
    + "limit :number", nativeQuery = true)
    List<ProductRanking> findNProductsTopRevenue(@Param("userID") Long userID, @Param("number") Integer number);

    @Query(value="select i.product_id as productID, p.name as name, "
    + "sum(if(s.status = 'FINISHED', i.price_per_unit * i.quantity, null)) as revenue, "
    + "sum(if(s.status = 'FINISHED', (i.price_per_unit - i.cost_per_unit) * i.quantity, null)) as profit, "
    + "sum(if(s.status = 'FINISHED', i.quantity, 0)) as sellNumber, "
    + "sum(if(s.status = 'RETURNED', i.quantity, 0)) as returnNumber, "
    + "sum(if(s.status = 'RETURNED', i.price_per_unit * i.quantity, 0)) as returnPrice "
    + "from sell_order_item i " + "inner join product p on i.product_id = p.product_id " + "inner join sell_order s on s.sell_order_id = i.sell_order_id "
    + "where i.user_id = :userID and s.finish_time BETWEEN DATE_SUB(NOW(), INTERVAL 180 DAY) and now() "
    + "group by i.product_id "
    + "having profit is not null and revenue is not null "
    + "order by profit desc, p.name asc "
    + "limit :number", nativeQuery = true)
    List<ProductRanking> findNProductsTopProfit(@Param("userID") Long userID, @Param("number") Integer number);
}
