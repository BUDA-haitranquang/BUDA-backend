package com.higroup.Buda.api.business.buy.neworder;

import java.time.ZonedDateTime;
import java.util.Optional;

import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.BuyOrderItem;
import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.BuyOrderItemRepository;
import com.higroup.Buda.repositories.BuyOrderRepository;
import com.higroup.Buda.repositories.SupplierRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
public class NewBuyOrderService {
    private UserRepository userRepository;
    private SupplierRepository supplierRepository;
    private BuyOrderRepository buyOrderRepository;
    private BuyOrderItemRepository buyOrderItemRepository;

    @Autowired
    public NewBuyOrderService(UserRepository userRepository, SupplierRepository supplierRepository, BuyOrderRepository buyOrderRepository, 
                              BuyOrderItemRepository buyOrderItemRepository)
    {
        this.userRepository = userRepository;
        this.supplierRepository = supplierRepository;
        this.buyOrderItemRepository = buyOrderItemRepository;
        this.buyOrderRepository = buyOrderRepository;
    }

    @Transactional
    public BuyOrder createNewBuyOrder(Long userID, BuyOrder buyOrder) {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserID does not exist");
        }
        buyOrder.setCreationTime(ZonedDateTime.now());
        if (buyOrder.getStatus().equals(Status.FINISHED)){
            buyOrder.setFinishTime(buyOrder.getCreationTime());
        }
        buyOrder.setUserID(userID);
        try {
            Optional<Supplier> supplier = this.supplierRepository.findSupplierByUserIDAndPhoneNumber(userID,
                    buyOrder.getSupplier().getPhoneNumber());
            // supplier.ifPresent(this.supplierRepository::save);
            if (supplier.isPresent()) {
                buyOrder.setSupplier(supplier.get());
            } else {
                buyOrder.getSupplier().setUserID(userID);
                this.supplierRepository.save(buyOrder.getSupplier());
            }
        } catch (Exception e) {

        }
        this.buyOrderRepository.save(buyOrder);
        try {
            Double totalCost = 0.0;
            for (BuyOrderItem buyOrderItem : buyOrder.getBuyOrderItems()) {
                buyOrderItem.setUserID(userID);
                buyOrderItem.setCreationTime(buyOrder.getCreationTime());
                if (!buyOrder.getSupplier().equals(null)) {
                    buyOrderItem.setSupplierID(buyOrder.getSupplier().getSupplierID());
                }
                Double currentCost = buyOrderItem.getQuantity() * buyOrderItem.getPricePerUnit();
                totalCost = totalCost + currentCost;
                this.buyOrderItemRepository.save(buyOrderItem);
                buyOrder.setTotalCost(totalCost);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        this.buyOrderRepository.save(buyOrder);
        return buyOrder;
    }

    @Transactional
    public void deleteBuyOrderByBuyOrderID(Long userID, Long buyOrderID)
    {
        Optional<BuyOrder> buyOrder = this.buyOrderRepository.findBuyOrderByBuyOrderID(buyOrderID);
        
        if ((buyOrder.isPresent()) && (userID.equals(buyOrder.get().getUserID())))
        {
            for (BuyOrderItem buyOrderItem: buyOrder.get().getBuyOrderItems())
            {
                this.buyOrderItemRepository.delete(buyOrderItem);
            }
            this.buyOrderRepository.delete(buyOrder.get());
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Buy Order not found");
        }
    }

}
