package com.higroup.Buda.api.statistics.revenue.total;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.higroup.Buda.customDTO.RevenueByTimeStatistics;

import org.springframework.stereotype.Repository;

@Repository
public class TotalRevenueRepository {
    @PersistenceContext
    EntityManager entityManager;

    private List<RevenueByTimeStatistics> genericConverter(List<Object[]> results){
        List<RevenueByTimeStatistics> revenueByTimeStatistics = new ArrayList<>();
        for (Object[] row: results) {
            RevenueByTimeStatistics r = new RevenueByTimeStatistics((String) row[0], (Double) row[1]);
            revenueByTimeStatistics.add(r);
        }
        return revenueByTimeStatistics;
    }
    public List<?> findTotalRevenueEveryDay(Long userID) {
        Query query = entityManager.createNativeQuery("select DATE_FORMAT(creation_time, '%d-%m-%Y') as timePeriod, sum(revenue) as revenue "
                + " from (select creation_time, final_cost as revenue from sell_order where user_id = :userID and status = 'FINISHED'"
                + " union all"
                + " select creation_time, total_cost as revenue from receipt where user_id = :userID"
                + " ) as ctrctr"
                + " GROUP BY DATE_FORMAT(creation_time, '%d-%m-%Y')"
                + " order by creation_time").setParameter("userID", userID);
        return genericConverter(query.getResultList());
    }

    public List<RevenueByTimeStatistics> findTotalRevenueLastXDays(Long userID, Long X){
        Query query = entityManager.createNativeQuery("select DATE_FORMAT(creation_time, '%d-%m-%Y') as timePeriod, sum(revenue) as revenue "
        + " from (select creation_time, final_cost as revenue from sell_order where user_id = :userID and status = 'FINISHED'"
        + " and creation_time > date_sub(now(), interval :X DAY)"
        + " union all"
        + " select creation_time, total_cost as revenue from receipt where user_id = :userID"
        + " and creation_time > date_sub(now(), interval :X DAY)) as ctrctr"
        + " GROUP BY DATE_FORMAT(creation_time, '%d-%m-%Y')"
        + " order by creation_time").setParameter("userID", userID).setParameter("X", X);
        return genericConverter(query.getResultList());
    }
}
