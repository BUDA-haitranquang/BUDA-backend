package com.higroup.Buda.api.business.buy.view;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.higroup.Buda.customDTO.ExpenseByTimeStatistics;
import com.higroup.Buda.customDTO.PeriodDTO;
import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.BuyOrderRepository;
import com.higroup.Buda.repositories.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ViewBuyOrderService {
    private BuyOrderRepository buyOrderRepository;
    private SupplierRepository supplierRepository;
    private ViewBuyOrderRepository viewBuyOrderRepository;

    @Autowired
    public ViewBuyOrderService(BuyOrderRepository buyOrderRepository, SupplierRepository supplierRepository, ViewBuyOrderRepository viewBuyOrderRepository) {
        this.buyOrderRepository = buyOrderRepository;
        this.supplierRepository = supplierRepository;
        this.viewBuyOrderRepository = viewBuyOrderRepository;
    }

    public BuyOrder findBuyOrderByBuyOrderID(Long userID, Long buyOrderID){
        Optional<BuyOrder> buyOrderOptional = this.buyOrderRepository.findBuyOrderByBuyOrderID(buyOrderID);
        if ((buyOrderOptional.isPresent()) && (buyOrderOptional.get().getUserID().equals(userID))){
            return buyOrderOptional.get();
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Buy Order not found");
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

    public Long countAllBuyOrderByUserID(Long userID) {
        return this.buyOrderRepository.countAllBuyOrderByUserID(userID);
    }
    public Long countBuyOrderBySupplierName(Long userID, String supplierName) {
        return this.viewBuyOrderRepository.countBuyOrderBySupplierName(userID, supplierName);
    }
    public List<BuyOrder> findBuyOrderBySupplierName(Long userID, String supplierName) {
        return this.viewBuyOrderRepository.findBuyOrderBySupplierName(userID, supplierName);
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

    public Long countBuyOrderByUserIDAndTextID(Long userID, String textID){
        return this.viewBuyOrderRepository.countBuyOrderByUserIDAndTextID(userID, textID);
    }
    public List<BuyOrder> findAllBuyOrderByStatus(Long userID, Status status)
    {
        return this.buyOrderRepository.findAllBuyOrderByStatusAndUserID(userID, status);
    }
    public Long countBuyOrderByUserIDAndStatus(Status status, Long userID)
    {
        return this.viewBuyOrderRepository.countBuyOrderByStatusAndUserID(status, userID);
    }
    public List<BuyOrder> findBuyOrderInPeriod(Long userID, PeriodDTO periodDTO)
    {
        ZonedDateTime from = periodDTO.getFrom().withHour(0).withMinute(0).withSecond(0);
        ZonedDateTime to = periodDTO.getTo().withHour(0).withMinute(0).withSecond(0);
        return this.viewBuyOrderRepository.findBuyOrderInPeriod(userID, from, to);
    }
    public Long countBuyOrderInPeriod(Long userID, PeriodDTO periodDTO)
    {
        ZonedDateTime from = periodDTO.getFrom().withHour(0).withMinute(0).withSecond(0);
        ZonedDateTime to = periodDTO.getTo().withHour(0).withMinute(0).withSecond(0);
        return this.viewBuyOrderRepository.countBuyOrderInPeriod(userID, from, to);
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
