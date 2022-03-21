package com.higroup.Buda.api.business.buy.view;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.higroup.Buda.customDTO.ExpenseByTimeStatistics;
import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.BuyOrderRepository;
import com.higroup.Buda.repositories.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ViewBuyOrderService {
    private BuyOrderRepository buyOrderRepository;
    private SupplierRepository supplierRepository;

    @Autowired
    public ViewBuyOrderService(BuyOrderRepository buyOrderRepository, SupplierRepository supplierRepository){
        this.buyOrderRepository = buyOrderRepository;
        this.supplierRepository = supplierRepository;
    }

    public List<BuyOrder> findAllBuyOrderBySupplierID(Long userID, Long supplierID) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierBySupplierID(supplierID);
        if ((supplier.isPresent()) && (supplier.get().getUserID().equals(userID))) {
            return this.buyOrderRepository.findAllBuyOrderBySupplier(supplier.get());
        } else
            return Collections.emptyList();
    }

    public List<BuyOrder> findAllBuyOrderByUserID(Long userID, Pageable pageable) {
        
        return this.buyOrderRepository.findAllBuyOrderByUserID(userID, pageable);
    }

    public List<BuyOrder> findAllBuyOrderByUserIDLastXDays(Long userID, Long X)
    {
        ZonedDateTime zonedDateTime = ZonedDateTime.now().minusDays(X);
        zonedDateTime.withHour(0).withMinute(0).withSecond(0);
        return this.buyOrderRepository.findAllBuyOrderByUserIDLastXDays(userID, zonedDateTime);
    }

    public List<BuyOrder> findAllIncompletedBuyOrderByUser(Long userID)
    {
        return this.buyOrderRepository.findAllIncompletedBuyOrderByUser(userID);
    }

    public List<BuyOrder> findAllCompletedBuyOrderByUser(Long userID, Pageable pageable) {
        return this.buyOrderRepository.findAllCompletedBuyOrderByUser(userID, pageable);
    }

    public List<BuyOrder> findBuyOrderByTextID(Long userID, String textID) {
        return this.buyOrderRepository.findAllBuyOrderByUserIDAndTextID(userID, textID);
    }

    public List<BuyOrder> findAllBuyOrderByStatus(Long userID, Status status)
    {
        return this.buyOrderRepository.findAllBuyOrderByUserIDAndStatus(userID, status);
    }

    public List<ExpenseByTimeStatistics> findBuyOrderExpenseByWeek(Long userID)
    {
        return this.buyOrderRepository.findBuyOrderExpenseByWeek(userID);
    }

    public List<ExpenseByTimeStatistics> findBuyOrderExpenseCurrentMonth(Long userID)
    {
        return this.buyOrderRepository.findBuyOrderExpenseCurrentMonth(userID);
    }

    public List<ExpenseByTimeStatistics> findBuyOrderExpenseGroupByMonth(Long userID)
    {
        return this.buyOrderRepository.findBuyOrderExpenseGroupByMonth(userID);
    }
}
