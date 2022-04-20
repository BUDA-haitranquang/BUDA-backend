package com.higroup.Buda.api.supplier.debt;

import com.higroup.Buda.entities.BuyOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierDebtRepository extends PagingAndSortingRepository<BuyOrder, Long> {
    @Query(value = "select coalesce(sum(b.totalCost), 0) " +
            "from BuyOrder b where b.userID = :userID and b.supplier.supplierID = :supplierID and b.status = 'DELAYING'")
    Double findDebtBySupplierID(@Param("userID") Long userID, @Param("supplierID") Long supplierID);

    @Query(value = "select distinct b from BuyOrder b" +
            " LEFT JOIN FETCH b.buyOrderItems bo" +
            " LEFT JOIN FETCH bo.ingredient" +
            " LEFT JOIN FETCH b.supplier s" +
            " LEFT JOIN FETCH b.staff st" +
            " LEFT JOIN FETCH st.roles" +
            " where b.userID = :userID and b.status = 'DELAYING' and s.supplierID = :supplierID")
    List<BuyOrder> findDelayBuyOrderBySupplierID(@Param("userID") Long userID, @Param("supplierID") Long supplierID);
}
