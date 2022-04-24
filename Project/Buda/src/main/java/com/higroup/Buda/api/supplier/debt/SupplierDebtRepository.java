package com.higroup.Buda.api.supplier.debt;

import com.higroup.Buda.entities.BuyOrder;
import javax.persistence.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SupplierDebtRepository  {
    @PersistenceContext
    EntityManager entityManager;

    Double findDebtBySupplierID(Long userID, Long supplierID) {
        Query query = entityManager.createQuery( "select coalesce(sum(b.totalCost), 0) " +
                "from BuyOrder b where b.userID = :userID and b.supplier.supplierID = :supplierID and b.status = 'DELAYING'").setParameter("userID", userID).setParameter("supplierID", supplierID);
        if (query.getResultList().get(0) == null) return 0.0;
        return (Double) query.getResultList().get(0);
    }


    List<BuyOrder> findDelayBuyOrderBySupplierID(Long userID,Long supplierID) {
        Query query = entityManager.createQuery("select distinct b from BuyOrder b" +
                " LEFT JOIN FETCH b.buyOrderItems bo" +
                " LEFT JOIN FETCH bo.ingredient" +
                " LEFT JOIN FETCH b.supplier s" +
                " LEFT JOIN FETCH b.staff st" +
                " LEFT JOIN FETCH st.roles" +
                " where b.userID = :userID and b.status = 'DELAYING' and s.supplierID = :supplierID").setParameter("userID", userID).setParameter("supplierID", supplierID);
        return query.getResultList();

    }
}
