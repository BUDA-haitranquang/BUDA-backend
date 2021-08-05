package com.higroup.Buda.services;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SellOrderService {
    private final SellOrderRepository sellOrderRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final SellOrderItemRepository sellOrderItemRepository;
    @Autowired
    public SellOrderService(SellOrderRepository sellOrderRepository, CustomerRepository customerRepository, UserRepository userRepository, SellOrderItemRepository sellOrderItemRepository)
    {
        this.sellOrderItemRepository = sellOrderItemRepository;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.sellOrderRepository = sellOrderRepository;
    }
    public ResponseEntity<?> registerNewSellOrder(Long userID, SellOrder sellOrder)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (!user.isPresent())
        {
            return ResponseEntity.badRequest().body("User not found");
        }
        sellOrder.setUserID(userID);
        this.sellOrderRepository.save(sellOrder);
        for (SellOrderItem sellOrderItem: sellOrder.getSellOrderItems())
        {
            this.sellOrderItemRepository.save(sellOrderItem);
        }
        return ResponseEntity.ok().body(sellOrder);
    }
    public List<SellOrder> findAllSellOrderByCustomerID(Long customerID)
    {
        Optional<Customer> customer = this.customerRepository.findCustomerByCustomerID(customerID);
        if (customer.isPresent())
        {
            return this.sellOrderRepository.findAllSellOrderByCustomer(customer.get());
        }
        return null;
    }
    public List<SellOrder> findAllSellOrderByUserID(Long userID)
    {
        return this.sellOrderRepository.findAllSellOrderByUserID(userID);
    }
}
