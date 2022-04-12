package com.higroup.Buda.api.statistics.profit;

import com.higroup.Buda.customDTO.ProfitByTimePeriodStatistics;
import com.higroup.Buda.customDTO.ProfitByTimeStatistics;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TotalProfitRepository {
    @PersistenceContext
    EntityManager entityManager;

    private List<ProfitByTimeStatistics> genericConverter(List<Object[]> results){
        List<ProfitByTimeStatistics> profitByTimeStatistics = new ArrayList<>();
        for (Object[] row: results) {
            ProfitByTimeStatistics p = new ProfitByTimeStatistics((String)row[0], (Double) row[1], (Double) row[2], (Double) row[3]);
            profitByTimeStatistics.add(p);
        }
        return profitByTimeStatistics;
    }
    private List<ProfitByTimePeriodStatistics> genericConverterPeriod(List<Object[]> results)
    {
        List<ProfitByTimePeriodStatistics> profitByTimePeriodStatistics = new ArrayList<>();
        for (Object[] row: results)
        {
            ProfitByTimePeriodStatistics p = new ProfitByTimePeriodStatistics((String) row[0], (String) row[1], (Double) row[2], (Double) row[3], (Double) row[4]);
            profitByTimePeriodStatistics.add(p);
        }
        return profitByTimePeriodStatistics;
    }
    public List<?> findTotalProfitEveryDay(Long userID) {
        Query query = entityManager.createNativeQuery("select DATE_FORMAT(creation_time, '%d-%m-%Y') as timePeriod, sum(revenue) as revenue, sum(expense) as expense,sum(revenue) - sum(expense) as profit "
                + " from (select creation_time, final_cost as revenue, 0 as expense from sell_order where user_id = :userID and status = 'FINISHED'"
                + " union all"
                + " select creation_time, total_cost as revenue, 0 as expense from receipt where user_id = :userID"
                + " union all"
                + " select creation_time, 0 as revenue, total_cost as expense from buy_order where user_id = :userID and status = 'FINISHED'"
                + " union all"
                + " select creation_time, 0 as revenue, total_spend as expense from fixed_cost_bill where user_id = :userID"
                + " union all"
                + " select creation_time, 0 as revenue, total_cost as expense from other_cost where user_id = :userID"
                + " union all"
                + " select creation_time, 0 as revenue, total_cost as expense from pay_slip where user_id = :userID"
                + " ) as ctrctr"
                + " GROUP BY DATE_FORMAT(creation_time, '%d-%m-%Y')"
                + " order by creation_time").setParameter("userID", userID);
        return genericConverter(query.getResultList());
    }

    public List<?> findTotalProfitEveryWeek(Long userID) {
        Query query = entityManager.createNativeQuery("select DATE_FORMAT(creation_time, '%V-%X') as timePeriod, sum(revenue) as revenue, sum(expense) as expense, sum(revenue) - sum(expense) as profit "
                + " from (select creation_time, final_cost as revenue, 0 as expense from sell_order where user_id = :userID and status = 'FINISHED'"
                + " union all"
                + " select creation_time, total_cost as revenue, 0 as expense from receipt where user_id = :userID"
                + " union all"
                + " select creation_time, 0 as revenue, total_cost as expense from buy_order where user_id = :userID and status = 'FINISHED'"
                + " union all"
                + " select creation_time, 0 as revenue, total_spend as expense from fixed_cost_bill where user_id = :userID"
                + " union all"
                + " select creation_time, 0 as revenue, total_cost as expense from other_cost where user_id = :userID"
                + " union all"
                + " select creation_time, 0 as revenue, total_cost as expense from pay_slip where user_id = :userID"
                + " ) as ctrctr"
                + " GROUP BY DATE_FORMAT(creation_time, '%V-%X')"
                + " order by creation_time").setParameter("userID", userID);
        return genericConverter(query.getResultList());
    }

    public List<?> findTotalProfitEveryMonth(Long userID) {
        Query query = entityManager.createNativeQuery("select DATE_FORMAT(creation_time, '%m-%Y') as timePeriod, sum(revenue) as revenue, sum(expense) as expense, sum(revenue) - sum(expense) as profit "
                + " from (select creation_time, final_cost as revenue, 0 as expense from sell_order where user_id = :userID and status = 'FINISHED'"
                + " union all"
                + " select creation_time, total_cost as revenue, 0 as expense from receipt where user_id = :userID"
                + " union all"
                + " select creation_time, 0 as revenue, total_cost as expense from buy_order where user_id = :userID and status = 'FINISHED'"
                + " union all"
                + " select creation_time, 0 as revenue, total_spend as expense from fixed_cost_bill where user_id = :userID"
                + " union all"
                + " select creation_time, 0 as revenue, total_cost as expense from other_cost where user_id = :userID"
                + " union all"
                + " select creation_time, 0 as revenue, total_cost as expense from pay_slip where user_id = :userID"
                + " ) as ctrctr"
                + " GROUP BY DATE_FORMAT(creation_time, '%m-%Y')"
                + " order by creation_time").setParameter("userID", userID);
        return genericConverter(query.getResultList());
    }
    public List<?> findTotalProfitEveryYear(Long userID) {
        Query query = entityManager.createNativeQuery("select DATE_FORMAT(creation_time, '%Y') as timePeriod, sum(revenue) as revenue, sum(expense) as expense, sum(revenue) - sum(expense) as profit "
                + " from (select creation_time, final_cost as revenue, 0 as expense from sell_order where user_id = :userID and status = 'FINISHED'"
                + " union all"
                + " select creation_time, total_cost as revenue, 0 as expense from receipt where user_id = :userID"
                + " union all"
                + " select creation_time, 0 as revenue, total_cost as expense from buy_order where user_id = :userID and status = 'FINISHED'"
                + " union all"
                + " select creation_time, 0 as revenue, total_spend as expense from fixed_cost_bill where user_id = :userID"
                + " union all"
                + " select creation_time, 0 as revenue, total_cost as expense from other_cost where user_id = :userID"
                + " union all"
                + " select creation_time, 0 as revenue, total_cost as expense from pay_slip where user_id = :userID"
                + " ) as ctrctr"
                + " GROUP BY DATE_FORMAT(creation_time, '%Y')"
                + " order by creation_time").setParameter("userID", userID);
        return genericConverter(query.getResultList());
    }

    public List<ProfitByTimeStatistics> findTotalProfitLastXDays(Long userID, Long X){
        Query query = entityManager.createNativeQuery("select DATE_FORMAT(creation_time, '%d-%m-%Y') as timePeriod, sum(revenue) as revenue, sum(expense) as expense, sum(revenue) - sum(expense) as profit "
                + " from (select creation_time, final_cost as revenue, 0 as expense from sell_order where user_id = :userID and status = 'FINISHED'"
                + " and creation_time > date_sub(now(), interval :X DAY)"
                + " union all"
                + " select creation_time, total_cost as revenue, 0 as expense from receipt where user_id = :userID"
                + " and creation_time > date_sub(now(), interval :X DAY)"
                + " union all"
                + " select creation_time, 0 as revenue, total_cost as expense from buy_order where user_id = :userID and status = 'FINISHED'"
                + " and creation_time > date_sub(now(), interval :X DAY)"
                + " union all"
                + " select creation_time, 0 as revenue, total_spend as expense from fixed_cost_bill where user_id = :userID"
                + " and creation_time > date_sub(now(), interval :X DAY)"
                + " union all"
                + " select creation_time, 0 as revenue, total_cost as expense from other_cost where user_id = :userID"
                + " and creation_time > date_sub(now(), interval :X DAY)"
                + " union all"
                + " select creation_time, 0 as revenue, total_cost as expense from pay_slip where user_id = :userID"
                + " and creation_time > date_sub(now(), interval :X DAY)"
                + " ) as ctrctr"
                + " GROUP BY DATE_FORMAT(creation_time, '%d-%m-%Y')"
                + " order by creation_time").setParameter("userID", userID).setParameter("X", X);
        return genericConverter(query.getResultList());
    }

    public List<ProfitByTimeStatistics> findTotalProfitPeriod(Long userID, ZonedDateTime from, ZonedDateTime to) {
        Query query = entityManager.createNativeQuery("select :from as timeFrom, :to as timeTo, sum(revenue) as revenue, sum(expense) as expense, sum(revenue) - sum(expense) as profit "
                + " from (select creation_time, final_cost as revenue, 0 as expense from sell_order where user_id = :userID and status = 'FINISHED'"
                + " and creation_time >= :from and creation_time <= :to"
                + " union all"
                + " select creation_time, total_cost as revenue, 0 as expense from receipt where user_id = :userID"
                + " and creation_time >= :from and creation_time <= :to"
                + " union all"
                + " select creation_time, 0 as revenue, total_cost as expense from buy_order where user_id = :userID and status = 'FINISHED'"
                + " and creation_time >= :from and creation_time <= :to"
                + " union all"
                + " select creation_time, 0 as revenue, total_spend as expense from fixed_cost_bill where user_id = :userID"
                + " and creation_time >= :from and creation_time <= :to"
                + " union all"
                + " select creation_time, 0 as revenue, total_cost as expense from other_cost where user_id = :userID"
                + " and creation_time >= :from and creation_time <= :to"
                + " union all"
                + " select creation_time, 0 as revenue, total_cost as expense from pay_slip where user_id = :userID"
                + " and creation_time >= :from and creation_time <= :to"
                + " ) as ctrctr").setParameter("userID", userID).setParameter("from", from).setParameter("to", to);
        return genericConverterPeriod(query.getResultList());
    }
}
