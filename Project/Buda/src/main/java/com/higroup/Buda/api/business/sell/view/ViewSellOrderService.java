package com.higroup.Buda.api.business.sell.view;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.repositories.SellOrderRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.util.Checker.PresentChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ViewSellOrderService {

    private UserRepository userRepository;
    private SellOrderRepository sellOrderRepository;
    private CustomerRepository customerRepository;

    @Autowired
    private PresentChecker presentChecker;

    @Autowired
    public ViewSellOrderService(UserRepository userRepository, SellOrderRepository sellOrderRepository,
            CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.sellOrderRepository = sellOrderRepository;
    }

    public List<SellOrder> findAllSellOrderByUserID(Long userID, Pageable pageable) {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return this.sellOrderRepository.findAllSellOrderByUserID(userID, pageable);
    }

    public List<SellOrder> findAllSellOrderByCustomerID(Long userID, Long customerID) {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        Optional<Customer> customer = this.customerRepository.findCustomerByCustomerID(customerID);
        if ((customer.isPresent()) && (customer.get().getUserID().equals(userID))) {
            return this.sellOrderRepository.findAllSellOrderByCustomer(customer.get());
        } else {
            return Collections.emptyList();
        }
    }

    public List<SellOrder> findAllSellOrderByUserIDLastXDays(Long userID, Long X) {
        presentChecker.checkIdAndRepository(userID, this.userRepository);
        ZonedDateTime xDaysAgo = ZonedDateTime.now().minusDays(X);
        xDaysAgo.withSecond(0).withHour(0).withMinute(0);
        return this.sellOrderRepository.findAllSellOrderByUserIDLastXDays(userID, xDaysAgo);
    }

    public List<SellOrder> findAllIncompletedSellOrderByUserID(Long userID) {
        presentChecker.checkIdAndRepository(userID, this.userRepository);
        return this.sellOrderRepository.findAllIncompletedSellOrderByUser(userID);
    }

    public List<SellOrder> findAllCompletedSellOrderByUserID(Long userID, Pageable pageable) {
        presentChecker.checkIdAndRepository(userID, this.userRepository);
        return this.sellOrderRepository.findAllCompletedSellOrderByUser(userID, pageable);
    }

    public List<SellOrder> findAllSellOrderByUserAndStatus(Long userID, Status status) {
        // return this.sellOrderRepository.findAllSellOrderByStatusAndUserID(userID,
        // status.toString());
        return this.sellOrderRepository.findAllSellOrderByUserIDAndStatus(userID, status);
    }

    public List<SellOrder> findSellOrderByTextID(Long userID, String textID) {
        return this.sellOrderRepository.findAllSellOrderByUserIDAndTextID(userID, textID);
    }
}
