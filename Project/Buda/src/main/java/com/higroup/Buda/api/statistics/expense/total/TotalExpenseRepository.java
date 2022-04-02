package com.higroup.Buda.api.statistics.expense.total;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.higroup.Buda.customDTO.ExpenseByTimeStatistics;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TotalExpenseRepository {
    @PersistenceContext
    EntityManager entityManager;

    private List<ExpenseByTimeStatistics> genericConverter(List<Object[]> results){
        List<ExpenseByTimeStatistics> ExpenseByTimeStatistics = new ArrayList<>();
        for (Object[] row: results) {
            ExpenseByTimeStatistics r = new ExpenseByTimeStatistics((String) row[0], (Double) row[1]);
            ExpenseByTimeStatistics.add(r);
        }
        return ExpenseByTimeStatistics;
    }
    public List<?> findTotalExpenseEveryDay(Long userID) {
        Query query = entityManager.createNativeQuery("select DATE_FORMAT(creation_time, '%d-%m-%Y') as timePeriod, sum(Expense) as Expense "
                + " from (select creation_time, total_cost as Expense from buy_order where user_id = :userID and status = 'FINISHED'"
                + " union all"
                + " select creation_time, total_spend as Expense from fixed_cost_bill where user_id = :userID"
                + " union all"
                + " select creation_time, total_cost as Expense from other_cost where user_id = :userID"
                + " union all"
                + " select creation_time, total_cost as Expense from pay_slip where user_id = :userID"
                + " ) as ctrctr"
                + " GROUP BY DATE_FORMAT(creation_time, '%d-%m-%Y')"
                + " order by creation_time").setParameter("userID", userID);
        return genericConverter(query.getResultList());
    }

    public List<?> findTotalExpenseEveryWeek(Long userID) {
        Query query = entityManager.createNativeQuery("select DATE_FORMAT(creation_time, '%V-%X') as timePeriod, sum(Expense) as Expense "
                + " from (select creation_time, total_cost as Expense from buy_order where user_id = :userID and status = 'FINISHED'"
                + " union all"
                + " select creation_time, total_spend as Expense from fixed_cost_bill where user_id = :userID"
                + " union all"
                + " select creation_time, total_cost as Expense from other_cost where user_id = :userID"
                + " union all"
                + " select creation_time, total_cost as Expense from pay_slip where user_id = :userID"
                + " ) as ctrctr"
                + " GROUP BY DATE_FORMAT(creation_time, '%V-%X')"
                + " order by creation_time").setParameter("userID", userID);
        return genericConverter(query.getResultList());
    }

    public List<?> findTotalExpenseEveryMonth(Long userID) {
        Query query = entityManager.createNativeQuery("select DATE_FORMAT(creation_time, '%m-%Y') as timePeriod, sum(Expense) as Expense "
                + " from (select creation_time, total_cost as Expense from buy_order where user_id = :userID and status = 'FINISHED'"
                + " union all"
                + " select creation_time, total_spend as Expense from fixed_cost_bill where user_id = :userID"
                + " union all"
                + " select creation_time, total_cost as Expense from other_cost where user_id = :userID"
                + " union all"
                + " select creation_time, total_cost as Expense from pay_slip where user_id = :userID"
                + " ) as ctrctr"
                + " GROUP BY DATE_FORMAT(creation_time, '%m-%Y')"
                + " order by creation_time").setParameter("userID", userID);
        return genericConverter(query.getResultList());
    }
    public List<?> findTotalExpenseEveryYear(Long userID) {
        Query query = entityManager.createNativeQuery("select DATE_FORMAT(creation_time, '%Y') as timePeriod, sum(Expense) as Expense "
                + " from (select creation_time, total_cost as Expense from buy_order where user_id = :userID and status = 'FINISHED'"
                + " union all"
                + " select creation_time, total_spend as Expense from fixed_cost_bill where user_id = :userID"
                + " union all"
                + " select creation_time, total_cost as Expense from other_cost where user_id = :userID"
                + " union all"
                + " select creation_time, total_cost as Expense from pay_slip where user_id = :userID"
                + " ) as ctrctr"
                + " GROUP BY DATE_FORMAT(creation_time, '%Y')"
                + " order by creation_time").setParameter("userID", userID);
        return genericConverter(query.getResultList());
    }

    public List<ExpenseByTimeStatistics> findTotalExpenseLastXDays(Long userID, Long X){
        Query query = entityManager.createNativeQuery("select DATE_FORMAT(creation_time, '%d-%m-%Y') as timePeriod, sum(Expense) as Expense "
                + " from (select creation_time, total_cost as Expense from buy_order where user_id = :userID and status = 'FINISHED'"
                + " and creation_time > date_sub(now(), interval :X DAY)"
                + " union all"
                + " select creation_time, total_spend as Expense from fixed_cost_bill where user_id = :userID"
                + " and creation_time > date_sub(now(), interval :X DAY)"
                + " union all"
                + " select creation_time, total_cost as Expense from other_cost where user_id = :userID"
                + " and creation_time > date_sub(now(), interval :X DAY)"
                + " union all"
                + " select creation_time, total_cost as Expense from pay_slip where user_id = :userID"
                + " and creation_time > date_sub(now(), interval :X DAY)"
                + " ) as ctrctr"
                + " GROUP BY DATE_FORMAT(creation_time, '%d-%m-%Y')"
                + " order by creation_time").setParameter("userID", userID).setParameter("X", X);
        return genericConverter(query.getResultList());
    }
}
