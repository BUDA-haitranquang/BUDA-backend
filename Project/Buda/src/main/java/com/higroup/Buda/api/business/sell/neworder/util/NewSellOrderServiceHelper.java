package com.higroup.Buda.api.business.sell.neworder.util;

import com.higroup.Buda.api.business.sell.neworder.SellOrderDTO;
import com.higroup.Buda.entities.*;
import com.higroup.Buda.entities.enumeration.DiscountType;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.repositories.DiscountRepository;
import com.higroup.Buda.repositories.ProductLeftLogRepository;
import com.higroup.Buda.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NewSellOrderServiceHelper {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ProductLeftLogRepository productLeftLogRepository;
    private final DiscountRepository discountRepository;
    private final SearchCustomerUtilService searchCustomerUtilService;
    private final DefaultCustomerUtilService defaultCustomerUtilService;

    @Autowired
    public NewSellOrderServiceHelper(CustomerRepository customerRepository, ProductRepository productRepository,
                                     ProductLeftLogRepository productLeftLogRepository,
                                     DiscountRepository discountRepository,
                                     SearchCustomerUtilService searchCustomerUtilService,
                                     DefaultCustomerUtilService defaultCustomerUtilService) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.productLeftLogRepository = productLeftLogRepository;
        this.discountRepository = discountRepository;
        this.searchCustomerUtilService = searchCustomerUtilService;
        this.defaultCustomerUtilService = defaultCustomerUtilService;
    }

    // product edit quantity function
    @Transactional
    public void editProductQuantity(Long userID, Long productID, Integer amountLeftChange) {
        if (amountLeftChange >= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the number of bought product must be positive");
        }
        Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
        if (opProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = opProduct.get();
        if (Objects.equals(product.getUserID(), userID)) {
            Integer amountLeft = product.getAmountLeft();
            if (amountLeft + amountLeftChange < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough amount for the request");
            }
            product.setAmountLeft(amountLeft + amountLeftChange);
            this.productRepository.save(product);

            ProductLeftLog productLeftLog = new ProductLeftLog();
            productLeftLog.setProduct(product);
            productLeftLog.setAmountLeftChange(amountLeftChange);
            productLeftLog.setUserID(userID);
            String message = String.format("buy %d %s products", -amountLeftChange, product.getName());
            productLeftLog.setMessage(message);
            productLeftLog.setCreationTime(ZonedDateTime.now());
            this.productLeftLogRepository.save(productLeftLog);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not belong to user");
        }
    }

    public void setDataForSellOrder(Long userID, SellOrder sellOrder, SellOrderDTO sellOrderDTO) {
        Customer customer = findCustomerInfo(userID, sellOrderDTO.getCustomer());
        sellOrder.setCustomer(customer);
        sellOrder.setGender(customer.getGender());
        sellOrder.setAgeGroup(customer.getAgeGroup());
        sellOrder.setCustomerMessage(sellOrderDTO.getCustomerMessage());
        sellOrder.setStatus(sellOrderDTO.getStatus());
        sellOrder.setCreationTime(ZonedDateTime.now());
        if (sellOrderDTO.getStatus().equals(Status.FINISHED)) {
            sellOrder.setFinishTime(ZonedDateTime.now());
        }
        sellOrder.setAddress(sellOrderDTO.getAddress());
    }

    public Customer findCustomerInfo(Long userID, Customer requestCustomer) {
        Customer customer;
        if ((requestCustomer == null) || ((requestCustomer.getPhoneNumber() == null) && (requestCustomer.getCustomerID() == null))) {
            customer = defaultCustomerUtilService.defaultCustomer(userID);
        } else {
            customer = searchCustomerUtilService.findCustomerByIncompletedInfo(userID, requestCustomer);
        }
        return customer;
    }

    public Double getRealCost(List<SellOrderItem> sellOrderItems) {
        Double realCost = 0.0;
        for (SellOrderItem sellOrderItem : sellOrderItems) {
            realCost += sellOrderItem.getActualTotalSale();
        }
        return realCost;
    }

    public Double getActualDiscountCash(Long discountID, Double realCost, SellOrder sellOrder) {
        Double actualDiscountCash = 0.0;
        if (discountID != null) {

            Optional<Discount> discountOptional = discountRepository.findDiscountByDiscountID(discountID);
            if (discountOptional.isPresent()) {
                Discount discount = discountOptional.get();
                if (discount.getExpiryTime().isBefore(ZonedDateTime.now())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "discount expired");
                } else {
                    // check if real cost equal or bigger than sale order price limit in discount
                    if (discount.getMinimumSellOrderCost() > realCost) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sale order cost not meet requirement");
                    } else {
                        if (discount.getDiscountType() == DiscountType.CASH_ONLY) {
                            actualDiscountCash = Math.min(discount.getCash(), sellOrder.getRealCost());
                        } else if (discount.getDiscountType() == DiscountType.PERCENTAGE_ONLY) {
                            actualDiscountCash = discount.getPercentage() / 100.0 * realCost;
                            if (actualDiscountCash > discount.getCashLimit()) {
                                actualDiscountCash = discount.getCashLimit();
                            }
                        }

                        sellOrder.setDiscount(discount);
                    }
                }
            } else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found discount");
        }
        return actualDiscountCash;
    }

    @Transactional
    public void updateDiscount(Long discountID) {
        Optional<Discount> discountOptional = discountRepository.findDiscountByDiscountID(discountID);
        if (discountOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Discount not found");
        }
        Discount discount = discountOptional.get();
        discount.setOrderCount(discount.getOrderCount() + 1);
        discountRepository.save(discount);
    }

    @Transactional
    public void updateCustomerTotalSpend(Double newTotalSpend, Customer customer) {
        customer.setTotalSpend(newTotalSpend);
        customerRepository.save(customer);
    }
}
