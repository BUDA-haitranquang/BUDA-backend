package com.higroup.Buda.services;

import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.entities.BuyOrderItem;
import com.higroup.Buda.repositories.SupplierRepository;
import com.higroup.Buda.repositories.BuyOrderItemRepository;
import com.higroup.Buda.repositories.BuyOrderRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BuyOrderService {
    private final BuyOrderRepository buyOrderRepository;
    private final SupplierRepository supplierRepository;
    private final UserRepository userRepository;
    private final BuyOrderItemRepository buyOrderItemRepository;

    @Autowired
    public BuyOrderService(BuyOrderRepository buyOrderRepository, SupplierRepository supplierRepository,
            UserRepository userRepository, BuyOrderItemRepository buyOrderItemRepository) {
        this.buyOrderRepository = buyOrderRepository;
        this.supplierRepository = supplierRepository;
        this.userRepository = userRepository;
        this.buyOrderItemRepository = buyOrderItemRepository;
    }

    public BuyOrder createNewBuyOrder(Long userID, BuyOrder buyOrder) {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserID does not exist");
        }
        buyOrder.setCreationTime(ZonedDateTime.now());
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
            for (BuyOrderItem buyOrderItem : buyOrder.getBuyOrderItems()) {
                buyOrderItem.setUserID(userID);
                buyOrderItem.setCreationTime(buyOrder.getCreationTime());
                if (!buyOrder.getSupplier().equals(null)) {
                    buyOrderItem.setSupplierID(buyOrder.getSupplier().getSupplierID());
                }
                this.buyOrderItemRepository.save(buyOrderItem);
            }
        } catch (Exception e) {

        }
        return buyOrder;
    }

    public List<BuyOrder> findAllBuyOrderBySupplierID(Long userID, Long supplierID) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierBySupplierID(supplierID);
        if ((supplier.isPresent()) && (supplier.get().getUserID() == userID)) {
            return this.buyOrderRepository.findAllBuyOrderBySupplier(supplier.get());
        } else
            return Collections.emptyList();
    }

    public List<BuyOrder> findAllBuyOrderByUserID(Long userID) {
        return this.buyOrderRepository.findAllBuyOrderByUserID(userID);
    }

    public void deleteBuyOrderByBuyOrderID(Long userID, Long buyOrderID)
    {
        Optional<BuyOrder> buyOrder = this.buyOrderRepository.findBuyOrderByBuyOrderID(buyOrderID);
        
        if ((buyOrder.isPresent()) && (userID == buyOrder.get().getUserID()))
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
