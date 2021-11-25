package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import javax.print.attribute.standard.MediaSize.Other;

import com.higroup.Buda.customDTO.ExpenseByTimeStatistics;
import com.higroup.Buda.entities.OtherCost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OtherCostRepository extends JpaRepository<OtherCost, Long>{
    OtherCost findOtherCostByOtherCostID(Long otherCostID);
    @Query(value = "select o from OtherCost o where o.userID = :userID and o.visible = true")
    List<OtherCost> findAllByUserID(@Param("userID") Long userID);
    @Query(value = "select * from other_cost o where o.user_id = :userID and o.status != 'FINISHED' and o.status != 'CANCELLED'", nativeQuery = true)
    List<OtherCost> findAllIncompletedOtherCostByUserID(Long userID);
    @Query(value = "select * from other_cost o where o.user_id = :userID and (o.creation_time BETWEEN NOW() - INTERVAL :X DAY and NOW())", nativeQuery = true)
    List<OtherCost> findAllOtherCostByUserIDLastXDays(@Param("userID") Long userID, @Param("X") Long X);
    @Query(value = "select o from OtherCost o where o.userID = :userID and o.visible = false")
    List<OtherCost> findAllHiddenOtherCostByUserID(@Param("userID") Long userID);
    @Query(value = "select new com.higroup.Buda.customDTO.ExpenseByTimeStatistics(DATE_FORMAT(f.creationTime, '%V-%Y'), SUM(f.totalCost)) "
    + "from OtherCost f where f.userID = :userID and year(f.creationTime) = year(current_date) "
    + "group by DATE_FORMAT(f.creationTime, '%V-%Y')"
    + "order by DATE_FORMAT(f.creationTime, '%V-%Y')")
    List<ExpenseByTimeStatistics> findOtherCostExpenseByWeek(@Param("userID") Long userID);
}
