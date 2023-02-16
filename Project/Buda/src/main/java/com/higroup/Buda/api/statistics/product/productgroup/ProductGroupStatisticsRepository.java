package com.higroup.Buda.api.statistics.product.productgroup;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class ProductGroupStatisticsRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Double findTotalRevenueFromProductGroup(Long userID, Long productGroupID) {
        Query query = entityManager.createNativeQuery("select sum(actual_total_sale) as revenue "
                + " from sell_order_item where user_id = :userID and product_id in "
                + " (select product_id from product_group_component where product_group_id = :productGroupID)").setParameter("userID", userID).setParameter("productGroupID", productGroupID);
        if (query.getResultList().get(0) == null) return 0.0;
        return (Double) query.getResultList().get(0);
    }
}
