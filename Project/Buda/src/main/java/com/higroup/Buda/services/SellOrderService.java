package com.higroup.Buda.services;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.SellOrderItem;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.repositories.SellOrderItemRepository;
import com.higroup.Buda.repositories.SellOrderRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SellOrderService {
    private final SellOrderRepository sellOrderRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final SellOrderItemRepository sellOrderItemRepository;

    @Autowired
    public SellOrderService(SellOrderRepository sellOrderRepository, CustomerRepository customerRepository,
            UserRepository userRepository, SellOrderItemRepository sellOrderItemRepository) {
        this.sellOrderItemRepository = sellOrderItemRepository;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.sellOrderRepository = sellOrderRepository;
    }

    public ResponseEntity<?> registerNewSellOrder(Long userID, SellOrder sellOrder) {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        sellOrder.setUserID(userID);
        sellOrder.setCreationTime(ZonedDateTime.now());
        try {
            Optional<Customer> customer = this.customerRepository.findCustomerByUserIDAndPhoneNumber(userID,
                    sellOrder.getCustomer().getPhoneNumber());
            if (customer.isPresent()) {
                sellOrder.setCustomer(customer.get());
            } else {
                sellOrder.getCustomer().setUserID(userID);
                this.customerRepository.save(sellOrder.getCustomer());
            }
        } catch (Exception e) {

        }
        this.sellOrderRepository.save(sellOrder);
        try {
            for (SellOrderItem sellOrderItem : sellOrder.getSellOrderItems()) {
                sellOrderItem.setSellOrder(sellOrder);
                sellOrderItem.setCreationTime(sellOrder.getCreationTime());
                sellOrderItem.setUserID(userID);
                // System.out.println("An item: " + sellOrderItem);
                this.sellOrderItemRepository.save(sellOrderItem);
            }
        } catch (Exception e) {
        }
        // Optional<Customer> customer =
        // this.customerRepository.findCustomerByCustomerID(sellOrder.getCustomer().getCustomerID());
        // if (customer.isPresent())
        // {
        // double totalSpend = customer.get().getTotalSpend() +
        // sellOrder.getFinalCost();
        // customer.get().setTotalSpend(totalSpend);
        // this.customerRepository.save(customer.get());
        // }
        return ResponseEntity.ok().body(sellOrder);
    }

    public ResponseEntity<?> findAllSellOrderByCustomerID(Long userID, Long customerID) {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        Optional<Customer> customer = this.customerRepository.findCustomerByCustomerID(customerID);
        if ((customer.isPresent()) && (customer.get().getUserID() == userID)) {
            return ResponseEntity.ok().body(this.sellOrderRepository.findAllSellOrderByCustomer(customer.get()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

    public ResponseEntity<?> findAllSellOrderByUserID(Long userID) {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok().body(this.sellOrderRepository.findAllSellOrderByUserID(userID));
    }
}
