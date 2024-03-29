package com.higroup.Buda.api.business.sell.neworder.staff;

import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.higroup.Buda.api.business.sell.neworder.SellOrderDTO;
import com.higroup.Buda.api.business.sell.neworder.SellOrderItemDTO;
import com.higroup.Buda.api.business.sell.neworder.util.DefaultCustomerUtilService;
import com.higroup.Buda.api.business.sell.neworder.util.SearchCustomerUtilService;
import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductLeftLog;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.SellOrderItem;
import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.entities.enumeration.DiscountType;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.repositories.DiscountRepository;
import com.higroup.Buda.repositories.ProductLeftLogRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.SellOrderItemRepository;
import com.higroup.Buda.repositories.SellOrderRepository;
import com.higroup.Buda.repositories.StaffRepository;

@Service
public class NewSellOrderStaffService {

    private CustomerRepository customerRepository;
    private SellOrderRepository sellOrderRepository;
    private SellOrderItemRepository sellOrderItemRepository;
    private DiscountRepository discountRepository;
    private ProductRepository productRepository;
    private ProductLeftLogRepository productLeftLogRepository;
    private SearchCustomerUtilService searchCustomerUtilService;
    private DefaultCustomerUtilService defaultCustomerUtilService;
    private StaffRepository staffRepository;

    private DecimalFormat df = new DecimalFormat("###.##");

    @Autowired
    public NewSellOrderStaffService(CustomerRepository customerRepository, SellOrderRepository sellOrderRepository, SellOrderItemRepository sellOrderItemRepository, 
                               DiscountRepository discountRepository, ProductRepository productRepository, ProductLeftLogRepository productLeftLogRepository,
                               DefaultCustomerUtilService defaultCustomerUtilService, SearchCustomerUtilService searchCustomerUtilService,
                               StaffRepository staffRepository)
    {
        this.staffRepository = staffRepository;
        this.searchCustomerUtilService = searchCustomerUtilService;
        this.defaultCustomerUtilService = defaultCustomerUtilService;
        this.customerRepository = customerRepository;
        this.sellOrderRepository = sellOrderRepository;
        this.sellOrderItemRepository = sellOrderItemRepository;
        this.discountRepository = discountRepository;
        this.productRepository = productRepository;
        this.productLeftLogRepository = productLeftLogRepository;
    }

    // product edit quantity function
    @Transactional
    public Product editProductQuantity(Long userID, Long staffID, Long productID, Integer amountLeftChange)
    {
        if(amountLeftChange >= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the number of bought product must be positive");
        }
        Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
        if(!opProduct.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = opProduct.get();
        if (Objects.equals(product.getUserID(), userID))
        {
            Integer amountLeft = product.getAmountLeft();
            if (amountLeft + amountLeftChange < 0)
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough amount for the request");
            }
            product.setAmountLeft(amountLeft + amountLeftChange);
            this.productRepository.save(product);
            String message = String.format("buy %d %s products", -amountLeftChange, product.getName());
            ProductLeftLog productLeftLog = new ProductLeftLog();
            productLeftLog.setProduct(product);
            productLeftLog.setAmountLeftChange(amountLeftChange);
            productLeftLog.setUserID(userID);
            productLeftLog.setStaffID(staffID);
            productLeftLog.setMessage(message);
            productLeftLog.setCreationTime(ZonedDateTime.now());
            this.productLeftLogRepository.save(productLeftLog);
            return product;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not belong to user");
        }
    }


    // sell order item function
    // sell order item register
    // @Transactional 
    private SellOrderItem registerNewSellOrderItem(Long userID, Long staffID, Long sellOrderID, @Valid SellOrderItemDTO sellOrderItemDTO){
        Optional<Product> opProduct = this.productRepository.findProductByProductID(sellOrderItemDTO.getProductID());
        if(!opProduct.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = opProduct.get();
        SellOrder sellOrder = this.sellOrderRepository.findSellOrderBySellOrderID(sellOrderID).get();
        // check product visible
        if(!product.getVisible())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("product %s is not visible", product.getName()));
        }
        // check product belong to user
        if(!product.getUserID().equals(userID)){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, String.format("product %s not belong to user", product.getName()));
        }
        // check enough quantity
        if(product.getAmountLeft() < sellOrderItemDTO.getQuantity()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Product %s doesnt have enough quantity left", product.getName()));
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
        Optional<Discount> discountOptional = this.discountRepository.findDiscountByDiscountID(discountID);
        if (discountOptional.isEmpty()){
            return;
        }
        Discount discount = discountOptional.get();
        discount.setOrderCount(discount.getOrderCount() + 1);
        discountRepository.save(discount);
    }

    private Customer findCustomerInfo(Long userID, Customer requestCustomer)
    {
        Customer customer;
        if(requestCustomer == null){
            customer = defaultCustomerUtilService.defaultCustomer(userID);
        }
        else{
            customer = searchCustomerUtilService.findCustomerByIncompletedInfo(userID, requestCustomer);
        }
        return customer;
    }

    private Double getActualDiscountCash(Long userID, Long discountID, Double realCost, SellOrder sellOrder)
    {
        Double actualDiscountCash = 0.0;
        if(discountID!= null){
            
            Optional<Discount> discountOptional = discountRepository.findDiscountByDiscountID(discountID);
            // found discount
            if (discountOptional.isPresent())
            {
                Discount discount = discountOptional.get();
                if(discount.getExpiryTime().isBefore(ZonedDateTime.now())){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "discount expired time !!!");
                }
                else 
                {
                    // check if real cost equal or bigger than sale order price limit in discount
                    if(discount.getMinimumSellOrderCost() > realCost)
                    {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sale order cost not meet requirement");
                    }
                    else
                    {
                        if(discount.getDiscountType() == DiscountType.CASH_ONLY)
                        {
                            actualDiscountCash = discount.getCash();
                        }
                        else if(discount.getDiscountType() == DiscountType.PERCENTAGE_ONLY)
                        {
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
        return actualDiscountCash;
    }

    private Double getRealCost(Long userID, Long staffID, Long sellOrderID, SellOrderDTO sellOrderDTO)
    {
        Double realCost = 0.0;
        for(SellOrderItemDTO sellOrderItemDTO: sellOrderDTO.getSellOrderItemDTOs()){
            Long productID = sellOrderItemDTO.getProductID();
            Integer quantity = sellOrderItemDTO.getQuantity();
            SellOrderItem sellOrderItem = this.registerNewSellOrderItem(userID, staffID, sellOrderID, sellOrderItemDTO);
            realCost += sellOrderItem.getActualTotalSale();
            // decrease number of product in database
            this.editProductQuantity(userID, staffID, productID, -quantity);
        }
        return realCost;
    }

    private void updateCustomerTotalSpend(Double newTotalSpend, Customer customer){
        customer.setTotalSpend(newTotalSpend);
        customerRepository.save(customer);
    }

    @Transactional
    public SellOrder registerSellOrder(Long userID, Long staffID, @Valid SellOrderDTO sellOrderDTO){
        Optional<Staff> staffOptional = this.staffRepository.findStaffByStaffID(staffID);
        if ((!staffOptional.isPresent()) || (!staffOptional.get().getUserID().equals(userID))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid staff");
        }
        if(sellOrderDTO.getStatus().equals(Status.RETURNED)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "can not set status as returned");
        }
        SellOrder sellOrder = new SellOrder();
        sellOrder.setUserID(userID);
        sellOrder.setStaff(staffOptional.get());
        // customer 
        Customer customer = this.findCustomerInfo(userID, sellOrderDTO.getCustomer());
        
        sellOrder.setGender(customer.getGender());
        sellOrder.setAgeGroup(customer.getAgeGroup());
        sellOrder.setCustomerMessage(sellOrderDTO.getCustomerMessage());
        sellOrder.setStatus(sellOrderDTO.getStatus());
        sellOrder.setCreationTime(ZonedDateTime.now());
        if(sellOrderDTO.getStatus().equals(Status.FINISHED)){
            sellOrder.setFinishTime(ZonedDateTime.now());
        }
        sellOrder.setAddress(sellOrderDTO.getAddress());
        this.sellOrderRepository.save(sellOrder);

        // real cost
        Double realCost = this.getRealCost(userID, staffID, sellOrder.getSellOrderID(), sellOrderDTO);

        Double actualDiscountCash = this.getActualDiscountCash(userID, sellOrderDTO.getDiscountID(), realCost, sellOrder);

        double actualDiscountPercentage = Double.parseDouble(df.format(actualDiscountCash/ realCost));
        double finalCost = Double.parseDouble(df.format(realCost - actualDiscountCash));
        sellOrder.setActualDiscountCash(Double.valueOf(df.format(actualDiscountCash)));
        sellOrder.setActualDiscountPercentage(actualDiscountPercentage);
        sellOrder.setFinalCost(finalCost);
        sellOrder.setRealCost(Double.valueOf(df.format(realCost)));
        this.sellOrderRepository.save(sellOrder);
        if (sellOrder.getStatus().equals(Status.FINISHED)){
            this.updateCustomerTotalSpend(customer.getTotalSpend() + finalCost, customer);
        }
        return sellOrder;
    }


    @Transactional
    public void deleteSellOrderBySellOrderID(Long userID, Long sellOrderID)
    {
        Optional<SellOrder> sellOrder = this.sellOrderRepository.findById(sellOrderID);
        
        try{
            if (!Objects.equals(sellOrder.get().getUserID(), userID))
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sell order not belong to user");
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
                // this.sellOrderItemRepository.save(sellOrderItem);
            }
            this.sellOrderItemRepository.saveAll(sellOrder.getSellOrderItems());
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
