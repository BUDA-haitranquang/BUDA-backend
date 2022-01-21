package com.higroup.Buda.api.business.sell.neworder;

import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import com.higroup.Buda.api.business.sell.neworder.util.DefaultCustomerUtilService;
import com.higroup.Buda.api.business.sell.neworder.util.SearchCustomerUtilService;
import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductLeftLog;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.SellOrderItem;
import com.higroup.Buda.entities.enumeration.AgeGroup;
import com.higroup.Buda.entities.enumeration.DiscountType;
import com.higroup.Buda.entities.enumeration.Gender;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.repositories.DiscountRepository;
import com.higroup.Buda.repositories.ProductLeftLogRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.SellOrderItemRepository;
import com.higroup.Buda.repositories.SellOrderRepository;
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
    private SearchCustomerUtilService searchCustomerUtilService;
    private DefaultCustomerUtilService defaultCustomerUtilService;

    private DecimalFormat df = new DecimalFormat("###.##");

    @Autowired
    public NewSellOrderService(CustomerRepository customerRepository, SellOrderRepository sellOrderRepository, SellOrderItemRepository sellOrderItemRepository, 
                               DiscountRepository discountRepository, ProductRepository productRepository, ProductLeftLogRepository productLeftLogRepository,
                               DefaultCustomerUtilService defaultCustomerUtilService, SearchCustomerUtilService searchCustomerUtilService)
    {
        this.searchCustomerUtilService = searchCustomerUtilService;
        this.defaultCustomerUtilService = defaultCustomerUtilService;
        this.customerRepository = customerRepository;
        this.sellOrderRepository = sellOrderRepository;
        this.sellOrderItemRepository = sellOrderItemRepository;
        this.discountRepository = discountRepository;
        this.productRepository = productRepository;
        this.productLeftLogRepository = productLeftLogRepository;
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
    private SellOrderItem registerNewSellOrderItem(Long userID, Long sellOrderID, @Valid SellOrderItemDTO sellOrderItemDTO){
        Product product = this.productRepository.findProductByProductID(sellOrderItemDTO.getProductID());
        SellOrder sellOrder = this.sellOrderRepository.findSellOrderBySellOrderID(sellOrderID).get();
        // check product visible
        if(!product.getVisible())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "product is not visible");
        }
        // check product belong to user
        if(!product.getUserID().equals(userID)){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "product not belong to user");
        }
        // check enough quantity
        if(product.getAmountLeft() < sellOrderItemDTO.getQuantity()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product doesnt have enough quantity left");
        }
        SellOrderItem sellOrderItem = new SellOrderItem();
        sellOrderItem.setSellOrder(sellOrder);
        sellOrderItem.setProduct(product);
        if(sellOrderItemDTO.getPricePerUnit() == null){
            sellOrderItem.setPricePerUnit(product.getSellingPrice());
        }
        else sellOrderItem.setPricePerUnit(sellOrderItemDTO.getPricePerUnit());
        sellOrderItem.setCostPerUnit(product.getCostPerUnit());
        sellOrderItem.setQuantity(sellOrderItemDTO.getQuantity());
        sellOrderItem.setUserID(userID);
        sellOrderItem.setCreationTime(sellOrder.getCreationTime());
        Double actualTotalSale = sellOrderItem.getPricePerUnit() * sellOrderItem.getQuantity();
        sellOrderItem.setActualTotalSale(actualTotalSale);
        sellOrderItem.setGender(sellOrder.getGender());
        sellOrderItem.setAgeGroup(sellOrder.getAgeGroup());
        this.sellOrderItemRepository.save(sellOrderItem);
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
    private void updateDiscount(Long userID, Long discountID, Double discountCash){
        Discount discount = this.discountRepository.findDiscountByDiscountID(discountID);
        discount.setOrderCount(discount.getOrderCount() + 1);
        discountRepository.save(discount);
    }

    @Transactional
    public SellOrder registerSellOrder(Long userID, @Valid SellOrderDTO sellOrderDTO){
        SellOrder sellOrder = new SellOrder();
        sellOrder.setUserID(userID);
        // customer 
        Customer customer = sellOrderDTO.getCustomer();
        if(customer == null){
            customer = defaultCustomerUtilService.defaultCustomer(userID);
        }
        else{
            customer = searchCustomerUtilService.findCustomerByIncompletedInfo(userID, customer);
        }
        sellOrder.setGender(customer.getGender());
        sellOrder.setAgeGroup(customer.getAgeGroup());
        sellOrder.setCustomerMessage(sellOrderDTO.getCustomerMessage());
        sellOrder.setStatus(sellOrderDTO.getStatus());
        sellOrder.setAddress(sellOrderDTO.getAddress());
        sellOrder.setCreationTime(ZonedDateTime.now());
        this.sellOrderRepository.save(sellOrder);

        // real cost
        Double realCost = 0.0;
        for(SellOrderItemDTO sellOrderItemDTO: sellOrderDTO.getSellOrderItemDTOs()){
            Long productID = sellOrderItemDTO.getProductID();
            Integer quantity = sellOrderItemDTO.getQuantity();
            SellOrderItem sellOrderItem = this.registerNewSellOrderItem(userID, sellOrder.getSellOrderID(), sellOrderItemDTO);
            realCost += sellOrderItem.getActualTotalSale();
            // decrease number of product in database
            this.editProductQuantity(userID, productID, -quantity, String.format("buy %d products with id: %d", quantity, productID));
        }

        Double actualDiscountCash = 0.0;
        // if no discount provided
        if(sellOrderDTO.getDiscountID() != null){
            Long discountID = sellOrderDTO.getDiscountID();
            Discount discount = discountRepository.findDiscountByDiscountID(discountID);
            // found discount
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
                            actualDiscountCash = discount.getCash();
                        }
                        else if(discount.getDiscountType() == DiscountType.PERCENTAGE_ONLY){
                            actualDiscountCash = (Double)(discount.getPercentage() / 100.0 * realCost);
                            if(actualDiscountCash > discount.getCashLimit()){
                                actualDiscountCash = discount.getCashLimit();
                            }
                        }
                        this.updateDiscount(userID, discountID, actualDiscountCash);
                        sellOrder.setDiscount(discount);
                    }
                }
            }
            else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found discount");
        }

        double actualDiscountPercentage = Double.parseDouble(df.format(actualDiscountCash/ realCost));
        double finalCost = Double.parseDouble(df.format(realCost - actualDiscountCash));
        sellOrder.setActualDiscountCash(Double.valueOf(df.format(actualDiscountCash)));
        sellOrder.setActualDiscountPercentage(actualDiscountPercentage);
        sellOrder.setFinalCost(finalCost);
        sellOrder.setRealCost(Double.valueOf(df.format(realCost)));
        this.sellOrderRepository.save(sellOrder);
        return sellOrder;
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
