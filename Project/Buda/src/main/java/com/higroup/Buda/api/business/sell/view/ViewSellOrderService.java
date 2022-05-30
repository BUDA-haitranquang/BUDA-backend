package com.higroup.Buda.api.business.sell.view;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.higroup.Buda.customDTO.PeriodDTO;
import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.SellOrder;
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
    private ViewSellOrderRepository viewSellOrderRepository;

    @Autowired
    private PresentChecker presentChecker;

    @Autowired
    public ViewSellOrderService(UserRepository userRepository, SellOrderRepository sellOrderRepository,
            CustomerRepository customerRepository, ViewSellOrderRepository viewSellOrderRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.sellOrderRepository = sellOrderRepository;
        this.viewSellOrderRepository = viewSellOrderRepository;
    }

    public SellOrder findSellOrderBySellOrderID(Long userID, Long sellOrderID){
        Optional<SellOrder> sellOrderOptional = this.sellOrderRepository.findSellOrderBySellOrderID(sellOrderID);
        if ((sellOrderOptional.isPresent()) && (sellOrderOptional.get().getUserID().equals(userID))){
            return sellOrderOptional.get();
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sell Order not found");
    }
    public ViewSellOrderDTO findAllSellOrderByUserID(Long userID, Pageable pageable) {
        Long count = this.sellOrderRepository.countAllSellOrderByUserID(userID);
        List<SellOrder> sellOrders = this.sellOrderRepository.findAllSellOrderByUserID(userID, pageable);
        return new ViewSellOrderDTO(count, sellOrders);
    }

    public List<SellOrder> findAllSellOrderByCustomerID(Long userID, Long customerID) {
        Optional<Customer> customer = this.customerRepository.findCustomerByCustomerID(customerID);
        if ((customer.isPresent()) && (customer.get().getUserID().equals(userID))) {
            return this.sellOrderRepository.findAllSellOrderByCustomer(customer.get());
        } else {
            return Collections.emptyList();
        }
    }

    public ViewSellOrderDTO findAllSellOrderByCustomerName(Long userID, String customerName, Pageable pageable){
        List<SellOrder> sellOrders = this.viewSellOrderRepository.findAllSellOrderByUserIDAndCustomerName(userID, customerName, pageable);
        Long count=this.viewSellOrderRepository.countAllSellOrderByUserIDAndCustomerName(userID, customerName);
        return new ViewSellOrderDTO(count, sellOrders);
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

    public ViewSellOrderDTO findAllSellOrderByUserAndStatus(Long userID, Status status, Pageable pageable) {
        List<SellOrder> sellOrders = this.sellOrderRepository.findAllSellOrderByUserIDAndStatus(userID, status, pageable);
        Long count = this.viewSellOrderRepository.countAllSellOrderByUserIDAndStatus(userID, status);
        return new ViewSellOrderDTO(count, sellOrders);
    }

    public ViewSellOrderDTO findSellOrderByTextID(Long userID, String textID, Pageable pageable) {
        List<SellOrder> sellOrders =this.sellOrderRepository.findAllSellOrderByUserIDAndTextID(userID, textID, pageable);
        Long count =this.viewSellOrderRepository.countAllSellOrderByUserIDAndTextID(userID, textID);
        return new ViewSellOrderDTO(count, sellOrders);
    }

    public ViewSellOrderDTO findAllSellOrderInPeriod(Long userID, PeriodDTO periodDTO, Pageable pageable) {
        ZonedDateTime from = periodDTO.getFrom().withHour(0).withMinute(0).withSecond(0);
        ZonedDateTime to = periodDTO.getTo().withHour(0).withMinute(0).withSecond(0);
        List<SellOrder> sellOrders = this.viewSellOrderRepository.findAllSellOrderInPeriod(userID, from, to, pageable);
        Long count = this.viewSellOrderRepository.countAllSellOrderInPeriod(userID, from, to);
        return new ViewSellOrderDTO(count, sellOrders);
    }
}