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
        List<RevenueByTimeStatistics> revenueByTimeStatistics = new ArrayList<RevenueByTimeStatistics>();
        for (Object[] row: results) {
            RevenueByTimeStatistics r = new RevenueByTimeStatistics((String) row[0], (Double) row[1]);
            revenueByTimeStatistics.add(r);
        }
        return revenueByTimeStatistics;
    }
    public List<?> findTotalRevenueEveryDay(Long userID) {
        return entityManager.createQuery("select new com.higroup.Buda.customDTO.RevenueByTimeStatistics(DATE_FORMAT(s.creationTime, '%d-%m-%Y'), SUM(s.finalCost))"
        + " from SellOrder s where s.userID = :userID "
        + " and s.status = 'FINISHED'"
        + " GROUP BY DATE_FORMAT(s.creationTime, '%d-%m-%Y')"
        + " ORDER BY s.creationTime").setParameter("userID", userID).getResultList();
    }

    public List<RevenueByTimeStatistics> findTotalRevenueLastXDays(Long userID, Long X){
        Query query = entityManager.createNativeQuery("select DATE_FORMAT(creation_time, '%d-%m-%Y') as timePeriod, sum(final_cost) as revenue " 
        + " from sell_order where user_id = :userID"
        + " and creation_time > date_sub(now(), interval :X DAY)"
        + " GROUP BY DATE_FORMAT(creation_time, '%d-%m-%Y')"
        + " order by creation_time;").setParameter("userID", userID).setParameter("X", X);
        return genericConverter(query.getResultList());
    }
}
