package com.higroup.Buda.repositories;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import com.higroup.Buda.customDTO.ExpenseByTimeStatistics;
import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.entities.enumeration.Status;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface BuyOrderRepository extends PagingAndSortingRepository<BuyOrder, Long> {
    @Query(value = "select distinct b from BuyOrder b" +
            " LEFT JOIN FETCH b.supplier s" +
            " LEFT JOIN FETCH b.staff st" +
            " LEFT JOIN FETCH st.roles" +
            " where b.buyOrderID = :buyOrderID")
    Optional<BuyOrder> findBuyOrderByBuyOrderID(Long buyOrderID);

    @Query(value = "select distinct b from BuyOrder b" +
            " LEFT JOIN FETCH b.supplier s" +
            " LEFT JOIN FETCH b.staff st" +
            " LEFT JOIN FETCH st.roles" +
            " where b.userID = :userID and b.textID = :textID")
    List<BuyOrder> findAllBuyOrderByUserIDAndTextID(Long userID, String textID, Pageable pageable);

    @Query(value = "select distinct b from BuyOrder b" +
            " LEFT JOIN FETCH b.supplier s" +
            " LEFT JOIN FETCH b.staff st" +
            " LEFT JOIN FETCH st.roles" +
            " where b.userID = :userID")
    List<BuyOrder> findAllBuyOrderByUserID(Long userID, Pageable pageable);

    @Query(value = "select distinct b from BuyOrder b" +
            " LEFT JOIN FETCH b.supplier s" +
            " LEFT JOIN FETCH b.staff st" +
            " LEFT JOIN FETCH st.roles" +
            " where b.supplier = :supplier")
    List<BuyOrder> findAllBuyOrderBySupplier(Supplier supplier, Pageable pageable);

    @Query(value = "select distinct b from BuyOrder b " +
            " LEFT JOIN FETCH b.supplier s" +
            " LEFT JOIN FETCH b.staff st" +
            " LEFT JOIN FETCH st.roles" + " where b.status LIKE :status and b.userID = :userID")
    List<BuyOrder> findAllBuyOrderByUserIDAndStatus(Long userID, Status status, Pageable pageable);

    @Query(value = "select distinct b from BuyOrder b " +
            " LEFT JOIN FETCH b.supplier s" +
            " LEFT JOIN FETCH b.staff st" +
            " LEFT JOIN FETCH st.roles" + " where b.status LIKE :status and b.userID = :userID")
    List<BuyOrder> findAllBuyOrderByStatusAndUserID(@Param("userID") Long userID, @Param("status") String status, Pageable pageable);

    @Query(value = "select b from BuyOrder b " +
            " LEFT JOIN FETCH b.supplier s" +
            " LEFT JOIN FETCH b.staff st" +
            " LEFT JOIN FETCH st.roles"
            + " where " 
           + "(b.creationTime >= :X) and " 
            + "b.userID = :userID")
    List<BuyOrder> findAllBuyOrderByUserIDLastXDays(@Param("userID") Long userID, @Param("X") ZonedDateTime X, Pageable pageable);

    @Query(value = "select s from BuyOrder s where s.status NOT LIKE 'FINISHED' and s.status NOT LIKE 'CANCELLED' and s.userID = :userID")
    List<BuyOrder> findAllIncompletedBuyOrderByUser(@Param("userID") Long userID, Pageable pageable);

    @Query(value = "select s from BuyOrder s where s.status LIKE 'FINISHED' and s.userID = :userID")
    List<BuyOrder> findAllCompletedBuyOrderByUser(@Param("userID") Long userID, Pageable pageable);

    @Query(value = "select new com.higroup.Buda.customDTO.ExpenseByTimeStatistics(DATE_FORMAT(f.creationTime, '%V-%Y'), SUM(f.totalCost)) "
            + "from BuyOrder f where f.userID = :userID and year(f.creationTime) = year(current_date) "
            + "group by DATE_FORMAT(f.creationTime, '%V-%Y') "
            + "order by DATE_FORMAT(f.creationTime, '%V-%Y') ")
    List<ExpenseByTimeStatistics> findBuyOrderExpenseByWeek(@Param("userID") Long userID);

    @Query(value = "select new com.higroup.Buda.customDTO.ExpenseByTimeStatistics(DATE_FORMAT(f.creationTime, '%d-%m-%Y'), SUM(f.totalCost)) "
            + "from BuyOrder f where f.userID = :userID and year(f.creationTime) = year(current_date) and month(f.creationTime) = month(current_date) "
            + "group by DATE_FORMAT(f.creationTime, '%d-%m-%Y') "
            + "order by DATE_FORMAT(f.creationTime, '%d-%m-%Y') ")
    List<ExpenseByTimeStatistics> findBuyOrderExpenseCurrentMonth(@Param("userID") Long userID);

    @Query(value = "select new com.higroup.Buda.customDTO.ExpenseByTimeStatistics(DATE_FORMAT(f.creationTime, '%m-%Y'), SUM(f.totalCost)) "
            + "from BuyOrder f where f.userID = :userID and year(f.creationTime) >= (year(current_date) - 1) "
            + "group by DATE_FORMAT(f.creationTime, '%m-%Y') "
            + "order by DATE_FORMAT(f.creationTime, '%m-%Y') ")
    List<ExpenseByTimeStatistics> findBuyOrderExpenseGroupByMonth(@Param("userID") Long userID);
}
