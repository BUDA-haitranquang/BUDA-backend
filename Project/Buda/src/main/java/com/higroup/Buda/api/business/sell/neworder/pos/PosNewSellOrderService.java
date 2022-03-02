package com.higroup.Buda.api.business.sell.neworder.pos;

import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.higroup.Buda.api.business.sell.neworder.SellOrderDTO;
import com.higroup.Buda.api.business.sell.neworder.SellOrderItemDTO;
import com.higroup.Buda.api.business.sell.neworder.util.DefaultCustomerUtilService;
import com.higroup.Buda.api.business.sell.neworder.util.SearchCustomerUtilService;
import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.SellOrderItem;
import com.higroup.Buda.entities.enumeration.DiscountType;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.repositories.DiscountRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.SellOrderItemRepository;
import com.higroup.Buda.repositories.SellOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PosNewSellOrderService {
    private final SellOrderRepository sellOrderRepository;
    private final SellOrderItemRepository sellOrderItemRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final DefaultCustomerUtilService defaultCustomerUtilService;
    private final DiscountRepository discountRepository;
    private final SearchCustomerUtilService searchCustomerUtilService;
    private DecimalFormat df = new DecimalFormat("###.##");
    @Autowired
    public PosNewSellOrderService(SellOrderRepository sellOrderRepository,
            SellOrderItemRepository sellOrderItemRepository,
            ProductRepository productRepository, CustomerRepository customerRepository,
            DefaultCustomerUtilService defaultCustomerService,
            DiscountRepository discountRepository,
            SearchCustomerUtilService customerUtilService) {
        this.searchCustomerUtilService = customerUtilService;
        this.discountRepository = discountRepository;
        this.defaultCustomerUtilService = defaultCustomerService;
        this.sellOrderItemRepository = sellOrderItemRepository;
        this.sellOrderRepository = sellOrderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public SellOrder newPosSellOrder(Long userID, @Valid SellOrderDTO sellOrderDTO) {
        // Chua co implementation cu the
        // Kiem tra customer
        SellOrder sellOrder = new SellOrder();

        Customer customer = sellOrderDTO.getCustomer();
        if (customer == null) {
            customer = defaultCustomerUtilService.defaultCustomer(userID);
        } else {
            customer = this.searchCustomerUtilService.findCustomerByIncompletedInfo(userID, customer);
        }
        sellOrder.setCustomer(customer);
        sellOrder.setGender(customer.getGender());
        sellOrder.setAgeGroup(customer.getAgeGroup());
        sellOrder.setCustomerMessage(sellOrderDTO.getCustomerMessage());
        sellOrder.setUserID(userID);
        // Day la don mua truc tiep - phai tra tien ngay
        sellOrder.setStatus(Status.FINISHED);
        sellOrder.setCreationTime(ZonedDateTime.now());
        sellOrder.setFinishTime(ZonedDateTime.now());
        Double realCost = 0.0;
        this.sellOrderRepository.save(sellOrder);
        for (SellOrderItemDTO sellOrderItemDTO: sellOrderDTO.getSellOrderItemDTOs()){
            SellOrderItem sellOrderItem = newPosSellOrderItem(sellOrderItemDTO);
            sellOrderItem.setSellOrder(sellOrder);
            sellOrderItem.setAgeGroup(customer.getAgeGroup());
            sellOrderItem.setGender(customer.getGender());
            sellOrderItem.setUserID(userID);
            sellOrderItem.setCreationTime(sellOrder.getCreationTime());
            realCost = realCost + sellOrderItem.getActualTotalSale();
            this.sellOrderItemRepository.save(sellOrderItem);
        }
        sellOrder.setRealCost(realCost);
        //Discount
        Double actualDiscountCash = 0.0;
        // if no discount provided
        if(sellOrderDTO.getDiscountID() != null){
            Long discountID = sellOrderDTO.getDiscountID();
            Optional<Discount> discountOptional = discountRepository.findDiscountByDiscountID(discountID);
            // found discount
            if(discountOptional.isPresent())
            {
                Discount discount = discountOptional.get();
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
                        sellOrder.setDiscount(discount);
                    }
                }
            }
            else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found discount");
        }
        sellOrder.setActualDiscountCash(actualDiscountCash);
        sellOrder.setFinalCost(sellOrder.getRealCost() - actualDiscountCash);
        Double actualDiscountPercentage = Double.parseDouble(df.format(actualDiscountCash/realCost));
        sellOrder.setActualDiscountPercentage(actualDiscountPercentage);
        this.sellOrderRepository.save(sellOrder);
        return sellOrder;
    }
    @Transactional
    public SellOrderItem newPosSellOrderItem(SellOrderItemDTO sellOrderItemDTO){
        SellOrderItem sellOrderItem = new SellOrderItem();
        Optional<Product> opProduct = this.productRepository.findProductByProductID(sellOrderItemDTO.getProductID());
        if(!opProduct.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = opProduct.get();
        sellOrderItem.setProduct(product);
        sellOrderItem.setPricePerUnit(sellOrderItemDTO.getPricePerUnit());
        if (product.getAmountLeft() >= sellOrderItemDTO.getQuantity()){
            sellOrderItem.setQuantity(sellOrderItemDTO.getQuantity());
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There are not enough products " + product.getName() + " left");
        Double actualTotalSale = 
        sellOrderItem.getPricePerUnit() * Double.valueOf(sellOrderItem.getQuantity().doubleValue());
        sellOrderItem.setActualTotalSale(actualTotalSale);
        sellOrderItem.setCostPerUnit(product.getCostPerUnit());
        product.setAmountLeft(product.getAmountLeft() - sellOrderItemDTO.getQuantity());
        this.productRepository.save(product);
        return sellOrderItem;
    }
    
}
