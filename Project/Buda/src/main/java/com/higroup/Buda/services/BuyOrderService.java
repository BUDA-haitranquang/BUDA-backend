package com.higroup.Buda.services;

import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.entities.BuyOrderItem;
import com.higroup.Buda.repositories.SupplierRepository;
import com.higroup.Buda.repositories.BuyOrderItemRepository;
import com.higroup.Buda.repositories.BuyOrderRepository;
import com.higroup.Buda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuyOrderService {
    private final BuyOrderRepository buyOrderRepository;
    private final SupplierRepository supplierRepository;
    private final UserRepository userRepository;
    private final BuyOrderItemRepository buyOrderItemRepository;

    @Autowired
    public BuyOrderService(BuyOrderRepository buyOrderRepository, SupplierRepository supplierRepository, UserRepository userRepository, BuyOrderItemRepository buyOrderItemRepository) {
        this.buyOrderRepository = buyOrderRepository;
        this.supplierRepository = supplierRepository;
        this.userRepository = userRepository;
        this.buyOrderItemRepository = buyOrderItemRepository;
    }
    public ResponseEntity<?> registerNewBuyOrder(Long userID, BuyOrder buyOrder)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            return ResponseEntity.badRequest().body("User not found");
        }
        buyOrder.setUserID(userID);
        this.buyOrderRepository.save(buyOrder);
        for (BuyOrderItem buyOrderItem: buyOrder.getBuyOrderItems())
        {
            this.buyOrderItemRepository.save(buyOrderItem);
        }
        Optional<Supplier> supplier = this.supplierRepository.findSupplierBySupplierID(buyOrder.getSupplier().getSupplierID());
        supplier.ifPresent(this.supplierRepository::save);
        return ResponseEntity.ok().body(buyOrder);
    }
    public List<BuyOrder> findAllBuyOrderBySupplierID(Long supplierID)
    {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierBySupplierID(supplierID);
        return supplier.map(this.buyOrderRepository::findAllBuyOrderBySupplier).orElse(null);
    }
    public List<BuyOrder> findAllBuyOrderByUserID(Long userID)
    {
        return this.buyOrderRepository.findAllBuyOrderByUserID(userID);
    }
}
