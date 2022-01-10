package com.higroup.Buda.api.business.sell.neworder;

import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.higroup.Buda.customDTO.RegisterSellOrder;
import com.higroup.Buda.customDTO.RegisterSellOrderItem;
import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductLeftLog;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.SellOrderItem;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.entities.enumeration.AgeGroup;
import com.higroup.Buda.entities.enumeration.DiscountType;
import com.higroup.Buda.entities.enumeration.Gender;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.repositories.DiscountRepository;
import com.higroup.Buda.repositories.ProductLeftLogRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.SellOrderItemRepository;
import com.higroup.Buda.repositories.SellOrderRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.util.Checker.PresentChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class NewSellOrderService {

    private CustomerRepository customerRepository;
    private SellOrderRepository sellOrderRepository;
    private SellOrderItemRepository sellOrderItemRepository;
    private DiscountRepository discountRepository;
    private ProductRepository productRepository;
    private ProductLeftLogRepository productLeftLogRepository;
    private UserRepository userRepository;

    private DecimalFormat df = new DecimalFormat("###.##");

    @Autowired
    public NewSellOrderService(CustomerRepository customerRepository, SellOrderRepository sellOrderRepository, SellOrderItemRepository sellOrderItemRepository, 
                               DiscountRepository discountRepository, ProductRepository productRepository, ProductLeftLogRepository productLeftLogRepository,
                               UserRepository userRepository)
    {
        this.customerRepository = customerRepository;
        this.sellOrderRepository = sellOrderRepository;
        this.sellOrderItemRepository = sellOrderItemRepository;
        this.discountRepository = discountRepository;
        this.productRepository = productRepository;
        this.productLeftLogRepository = productLeftLogRepository;
        this.userRepository = userRepository;
    }

    @Autowired
    private PresentChecker presentChecker;

    // product edit quantity function
    @Transactional
    public Product editProductQuantity(Long userID, Long productID, Integer amountLeftChange, String message)
    {
        Product product = this.productRepository.findProductByProductID(productID);
        if (Objects.equals(product.getUserID(), userID))
        {
            Integer amountLeft = product.getAmountLeft();
            if (amountLeft + amountLeftChange < 0)
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough amount for the request");
            }
            product.setAmountLeft(amountLeft + amountLeftChange);
            this.productRepository.save(product);
            ProductLeftLog productLeftLog = new ProductLeftLog();
            productLeftLog.setProduct(product);
            productLeftLog.setAmountLeftChange(amountLeftChange);
            productLeftLog.setUserID(userID);
            productLeftLog.setMessage(message);
            productLeftLog.setCreationTime(ZonedDateTime.now());
            this.productLeftLogRepository.save(productLeftLog);
            return product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }


    // sell order item function
    // sell order item register
    @Transactional
    public SellOrderItem registerNewSellOrderItem(Long userID, RegisterSellOrderItem registerSellOrderItem){
        Product product = (Product) this.presentChecker.checkIdAndRepository(registerSellOrderItem.getProductID(), this.productRepository);
        SellOrder sellOrder = (SellOrder) this.presentChecker.checkIdAndRepository(registerSellOrderItem.getSellOrderID(), this.sellOrderRepository);
        if(!product.getVisible())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "product is not visible");
        }
        if(sellOrder.checkProductExistInItems(product))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exits in list items of sell order");
        }
        if(product.getAmountLeft() < registerSellOrderItem.getQuantity()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product doesnt have enough quantity left");
        }

        SellOrderItem sellOrderItem = new SellOrderItem();
        sellOrderItem.setSellOrder(sellOrder);
        sellOrderItem.setProduct(product);
        sellOrderItem.setQuantity(registerSellOrderItem.getQuantity());
        sellOrderItem.setPricePerUnit(product.getSellingPrice());
        sellOrderItem.setCostPerUnit(product.getCostPerUnit());
        sellOrderItem.setUserID(userID);
        sellOrderItem.setCreationTime(sellOrder.getCreationTime());
        Double actualTotalSale = product.getSellingPrice() * registerSellOrderItem.getQuantity();
        sellOrderItem.setActualTotalSale(actualTotalSale);
        sellOrderItem.setGender(sellOrder.getGender());
        sellOrderItem.setAgeGroup(sellOrder.getAgeGroup());
        this.sellOrderItemRepository.save(sellOrderItem);
        // add sellorderitem to sellorder
        sellOrder.getSellOrderItems().add(sellOrderItem);
        return sellOrderItem;
    }

    // sell order item delete
    @Transactional
    public void deleteSellOrderItem(Long userID, Long sellOrderItemID)
    {
        Optional<SellOrderItem> sellOrderItem = this.sellOrderItemRepository.findById(sellOrderItemID);
        if ((sellOrderItem.isPresent()) && (sellOrderItem.get().getUserID().equals(userID)))
        {
            this.sellOrderItemRepository.delete(sellOrderItem.get());
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SellOrderItem not found");
        }
    }



    @Transactional
    public SellOrder registerSellOrder(Long userID, RegisterSellOrder registerSellOrder){
        SellOrder sellOrder = new SellOrder();

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
                customer.setName("UNKNOWN");
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
            SellOrderItem sellOrderItem = this.registerNewSellOrderItem(userID, new RegisterSellOrderItem(productID, sellOrder.getSellOrderID(), quantity));
            realCost += sellOrderItem.getActualTotalSale(); 
            this.editProductQuantity(userID, productID, -quantity, String.format("buy %d products with id: %d", quantity, productID));
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
    
    public List<SellOrder> findAllSellOrderByUserID(Long userID) {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return this.sellOrderRepository.findAllSellOrderByUserID(userID);
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
                this.deleteSellOrderItem(userID, sellOrderItem.getSellOrderItemID());
            }
            this.sellOrderRepository.deleteById(sellOrderID);
        }
        catch(Exception e)
        {
            throw e;               
        }
    }

    public List<SellOrder> findAllSellOrderByUserIDLastXDays(Long userID, Long X)
    {
        presentChecker.checkIdAndRepository(userID, this.userRepository);
        ZonedDateTime xDaysAgo = ZonedDateTime.now().minusDays(X);
        xDaysAgo.withSecond(0).withHour(0).withMinute(0);
        return this.sellOrderRepository.findAllSellOrderByUserIDLastXDays(userID, xDaysAgo);
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


}
