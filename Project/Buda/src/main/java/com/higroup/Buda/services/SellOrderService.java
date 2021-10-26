package com.higroup.Buda.services;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
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
import com.higroup.Buda.util.Checker.PresentChecker;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    @Autowired
    private PresentChecker presentChecker;
    public SellOrder registerNewSellOrder(Long userID, SellOrder sellOrder) {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
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
        return sellOrder;
    }

    public List<SellOrder> findAllSellOrderByCustomerID(Long userID, Long customerID) {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        Optional<Customer> customer = this.customerRepository.findCustomerByCustomerID(customerID);
        if ((customer.isPresent()) && (customer.get().getUserID() == userID)) {
            return this.sellOrderRepository.findAllSellOrderByCustomer(customer.get());
        } else {
            return Collections.emptyList();
        }
    }

    public List<SellOrder> findAllSellOrderByUserID(Long userID) {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return this.sellOrderRepository.findAllSellOrderByUserID(userID);
    }

    public void deleteSellOrderBySellOrderID(Long userID, Long sellOrderID)
    {
        Optional<SellOrder> sellOrder = this.sellOrderRepository.findById(sellOrderID);
        
        try{
            if (sellOrder.get().getUserID()!=userID)
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserID does not match");
            }
            for (SellOrderItem sellOrderItem: sellOrder.get().getSellOrderItems())
            {
                this.sellOrderItemRepository.delete(sellOrderItem);
            }
            this.sellOrderRepository.deleteById(sellOrderID);
        }
        catch(Exception e)
        {
            
        }
    }
}
