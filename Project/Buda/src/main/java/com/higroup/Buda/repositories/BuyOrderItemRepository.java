package com.higroup.Buda.repositories;

import com.higroup.Buda.entities.BuyOrderItem;
import com.higroup.Buda.entities.BuyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BuyOrderItemRepository extends JpaRepository<BuyOrderItem, Long> {
    List<BuyOrderItem> findAllBuyOrderItemByBuyOrder(@Param("buyOrder") BuyOrder BuyOrder);
    Optional<BuyOrderItem> findBuyOrderItemByBuyOrderItemID(@Param("buyOrderItemID") Long buyOrderItemID);
    @Query(value = "select * from buy_order_item i where i.ingredient_id = :ingredientID", nativeQuery = true)
    List<BuyOrderItem> findAllBuyOrderItemByIngredientID(@Param("ingredientID") Long ingredientID);
}
