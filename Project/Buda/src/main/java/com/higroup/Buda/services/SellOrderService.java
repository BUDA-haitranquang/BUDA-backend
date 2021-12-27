package com.higroup.Buda.services;

import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.util.*;

import javax.transaction.Transactional;

import com.higroup.Buda.customDTO.AgeGroupStatistics;
import com.higroup.Buda.customDTO.RegisterSellOrder;
import com.higroup.Buda.customDTO.RegisterSellOrderItem;
import com.higroup.Buda.entities.AgeGroup;
import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.entities.DiscountType;
import com.higroup.Buda.entities.Gender;
import com.higroup.Buda.entities.MembershipType;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.SellOrderItem;
import com.higroup.Buda.entities.Status;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.repositories.DiscountRepository;
import com.higroup.Buda.repositories.MembershipTypeRepository;
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
    private final SellOrderItemService sellOrderItemService;
    private final SellOrderRepository sellOrderRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final MembershipTypeRepository membershipTypeRepository;
    private final DiscountRepository discountRepository;
    private final SellOrderItemRepository sellOrderItemRepository;
    private final ProductService productService;
    private final WarrantyOrderService warrantyOrderService;

    private DecimalFormat df = new DecimalFormat("###.##");

    @Autowired
    public SellOrderService(SellOrderRepository sellOrderRepository, CustomerRepository customerRepository,
            UserRepository userRepository, SellOrderItemService sellOrderItemService, MembershipTypeRepository membershipTypeRepository, SellOrderItemRepository sellOrderItemRepository,
            DiscountRepository discountRepository, ProductService productService, WarrantyOrderService warrantyOrderService) {
        this.sellOrderItemService = sellOrderItemService;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.sellOrderRepository = sellOrderRepository;
        this.membershipTypeRepository = membershipTypeRepository;
        this.discountRepository = discountRepository;
        this.sellOrderItemRepository = sellOrderItemRepository;
        this.productService = productService;
        this.warrantyOrderService = warrantyOrderService;
    }
    @Autowired
    private PresentChecker presentChecker;

    @Transactional 
    public SellOrder registerSellOrder(Long userID, RegisterSellOrder registerSellOrder){
        SellOrder sellOrder = new SellOrder();
        // Optional<User> user = this.userRepository.findUserByUserID(userID);
        // if (user.isEmpty()) {
        //     throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        // }
        sellOrder.setUserID(userID);
        sellOrder.setCreationTime(ZonedDateTime.now());
        // customer solving
        Customer customer;
        if(registerSellOrder.getCustomer() == null){
            String default_phoneNumber = "000000000";
            customer = this.customerRepository.findCustomerByUserIDAndPhoneNumber(userID, default_phoneNumber).get();
            if(customer == null){
                customer = new Customer();
                customer.setAgeGroup(AgeGroup.UNKNOWN);
                customer.setGender(Gender.UNKNOWN);
                customer.setPhoneNumber(default_phoneNumber);
                customer.setUserID(userID);
                this.customerRepository.save(customer);
            }
        }
        else{
            String phoneNumber = registerSellOrder.getCustomer().getPhoneNumber();
            if(phoneNumber == null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone number invalid");
            }
            customer = this.customerRepository.findCustomerByUserIDAndPhoneNumber(userID, phoneNumber).get();
            if(customer == null){
                customer = registerSellOrder.getCustomer();
                this.customerRepository.save(sellOrder.getCustomer());
            }
        }
        sellOrder.setCustomer(customer);
        sellOrder.setGender(customer.getGender());
        sellOrder.setAgeGroup(customer.getAgeGroup());
        sellOrder.setCustomerMessage(registerSellOrder.getCustomer_message());
        sellOrder.setStatus(registerSellOrder.getStatus());
        sellOrder.setAddress(registerSellOrder.getAddress());
        this.sellOrderRepository.save(sellOrder);

        double realCost = 0;
        // add sellorderitem by product id
        for(Long productID : registerSellOrder.getProducts().keySet()){
            Integer quantity = registerSellOrder.getProducts().get(productID);
            SellOrderItem sellOrderItem = sellOrderItemService.registerNewSellOrderItem(userID, new RegisterSellOrderItem(productID, sellOrder.getSellOrderID(), quantity));
            warrantyOrderService.registerNewWarrantyOrder(userID, userID, sellOrder.getSellOrderID(), customer.getCustomerID());
            realCost += sellOrderItem.getActualTotalSale(); 
            productService.editProductQuantity(userID, productID, -quantity, String.format("buy %d products with id: %d", quantity, productID));
        }
        double actual_discount_cash = 0;
        // discount solving 
        if(registerSellOrder.getDiscountID() != null){
            Discount discount = this.discountRepository.findDiscountByDiscountID(registerSellOrder.getDiscountID());
            if(discount != null){
                if(discount.getExpiryTime().isBefore(ZonedDateTime.now())){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "discount expired time !!!");
                }
                else {
                    // check if real cost equal or bigger than sale order price limit in discount
                    if(discount.getMinimumSellOrderCost() > realCost){
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sale order cost not meet requirement");
                    }
                    else{
                        if(discount.getDiscountType() == DiscountType.CASH_ONLY){
                            actual_discount_cash = discount.getCash();
                        }
                        else if(discount.getDiscountType() == DiscountType.PERCENTAGE_ONLY){
                            actual_discount_cash = (long)(discount.getPercentage() / 100.0 * realCost);
                            if(actual_discount_cash > discount.getCashLimit()){
                                actual_discount_cash = discount.getCashLimit();
                            }
                        }
                    }
                }
            }
            else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found discount");
        }
        // set precistion == 2
        double actualDiscountPercentage = Double.parseDouble(df.format(actual_discount_cash / realCost));
        double finalCost = Double.parseDouble(df.format(realCost - actual_discount_cash));
        sellOrder.setActualDiscountCash(Double.valueOf(df.format(actual_discount_cash)));
        sellOrder.setActualDiscountPercentage(actualDiscountPercentage);
        sellOrder.setFinalCost(finalCost);
        sellOrder.setRealCost(Double.valueOf(df.format(realCost)));
        // 
        this.sellOrderRepository.save(sellOrder);
        return sellOrder;
        
    }    

    // @Transactional
    // public SellOrder registerNewSellOrder(Long userID, SellOrder sellOrder) {
    //     Optional<User> user = this.userRepository.findUserByUserID(userID);
    //     if (user.isEmpty()) {
    //         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    //     }
    //     sellOrder.setUserID(userID);
    //     sellOrder.setCreationTime(ZonedDateTime.now());
    //     try {
    //         Optional<Customer> customer = this.customerRepository.findCustomerByUserIDAndPhoneNumber(userID,
    //                 sellOrder.getCustomer().getPhoneNumber());
    //         if (customer.isPresent()) {
    //             sellOrder.setCustomer(customer.get());
    //         } else {
    //             sellOrder.getCustomer().setUserID(userID);
    //             this.customerRepository.save(sellOrder.getCustomer());
    //         }
    //     } catch (Exception e) {

    //     }
    //     this.sellOrderRepository.save(sellOrder);
    //     try {
    //         for (SellOrderItem sellOrderItem : sellOrder.getSellOrderItems()) {
    //             sellOrderItem.setSellOrder(sellOrder);
    //             sellOrderItem.setCreationTime(sellOrder.getCreationTime());
    //             sellOrderItem.setUserID(userID);
    //             // System.out.println("An item: " + sellOrderItem);
    //             this.sellOrderItemRepository.save(sellOrderItem);
    //         }
    //     } catch (Exception e) {
    //     }
    //     // Optional<Customer> customer =
    //     // this.customerRepository.findCustomerByCustomerID(sellOrder.getCustomer().getCustomerID());
    //     // if (customer.isPresent())
    //     // {
    //     // double totalSpend = customer.get().getTotalSpend() +
    //     // sellOrder.getFinalCost();
    //     // customer.get().setTotalSpend(totalSpend);
    //     // this.customerRepository.save(customer.get());
    //     // }
    //     return sellOrder;
    // }

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

    public List<SellOrder> findAllSellOrderByUserID(Long userID) {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return this.sellOrderRepository.findAllSellOrderByUserID(userID);
    }

    @Transactional
    public void deleteSellOrderBySellOrderID(Long userID, Long sellOrderID)
    {
        Optional<SellOrder> sellOrder = this.sellOrderRepository.findById(sellOrderID);
        
        try{
            if (!Objects.equals(sellOrder.get().getUserID(), userID))
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserID does not match");
            }
            for (SellOrderItem sellOrderItem: sellOrder.get().getSellOrderItems())
            {
                // this.sellOrderItemRepository.delete(sellOrderItem);
                this.sellOrderItemService.deleteSellOrderItem(userID, sellOrderItem.getSellOrderItemID());
            }
            this.sellOrderRepository.deleteById(sellOrderID);
        }
        catch(Exception e)
        {
                        
        }
    }
    @Transactional
    public SellOrder updateSellOrder(Long userID, SellOrder sellOrder)
    {
        Optional<SellOrder> oldSellOrder = this.sellOrderRepository.findSellOrderBySellOrderID(sellOrder.getSellOrderID());
        if ((oldSellOrder.isPresent()) && (oldSellOrder.get().getUserID().equals(userID)))
        {
            sellOrder.setUserID(userID);
            for (SellOrderItem sellOrderItem: sellOrder.getSellOrderItems())
            {
                sellOrderItem.setUserID(userID);
                this.sellOrderItemRepository.save(sellOrderItem);
            }
            try
            {
                Optional<Customer> customer = this.customerRepository.findCustomerByUserIDAndPhoneNumber(userID,
                    sellOrder.getCustomer().getPhoneNumber());
                if (customer.isPresent()) {
                    sellOrder.setCustomer(customer.get());
                } else {
                    sellOrder.getCustomer().setUserID(userID);
                    this.customerRepository.save(sellOrder.getCustomer());
                }
            }
            catch(Exception e)
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer can not be added");
            }
            this.sellOrderRepository.save(sellOrder);
            return sellOrder;
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }

    public List<SellOrder> findAllSellOrderByUserIDLastXDays(Long userID, Long X)
    {
        presentChecker.checkIdAndRepository(userID, this.userRepository);
        return this.sellOrderRepository.findAllSellOrderByUserIDLastXDays(userID, X);
    }

    public List<SellOrder> findAllIIncompletedSellOrderByUserID(Long userID)
    {
        presentChecker.checkIdAndRepository(userID, this.userRepository);
        return this.sellOrderRepository.findAllIncompletedSellOrderByUser(userID);
    }

    public List<SellOrder> findAllSellOrderByUserAndStatus(Long userID, Status status)
    {
        // return this.sellOrderRepository.findAllSellOrderByStatusAndUserID(userID, status.toString());
        return this.sellOrderRepository.findAllSellOrderByUserIDAndStatus(userID, status);
    }

}
