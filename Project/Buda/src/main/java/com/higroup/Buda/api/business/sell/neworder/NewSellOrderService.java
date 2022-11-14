package com.higroup.Buda.api.business.sell.neworder;

import com.higroup.Buda.api.business.sell.neworder.util.NewSellOrderItemService;
import com.higroup.Buda.api.business.sell.neworder.util.NewSellOrderServiceHelper;
import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.SellOrderItem;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.repositories.SellOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NewSellOrderService {

    private final CustomerRepository customerRepository;
    private final SellOrderRepository sellOrderRepository;
    private final NewSellOrderServiceHelper helper;
    private final NewSellOrderItemService newSellOrderItemService;

    private final DecimalFormat df = new DecimalFormat("###.##");

    @Autowired
    public NewSellOrderService(CustomerRepository customerRepository, SellOrderRepository sellOrderRepository,
                               NewSellOrderServiceHelper helper, NewSellOrderItemService newSellOrderItemService) {

        this.customerRepository = customerRepository;
        this.sellOrderRepository = sellOrderRepository;
        this.helper = helper;
        this.newSellOrderItemService = newSellOrderItemService;
    }

    @Transactional
    public SellOrder registerSellOrder(Long userID, @Valid SellOrderDTO sellOrderDTO) {
        if (sellOrderDTO.getStatus().equals(Status.RETURNED)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "can not set status as returned");
        }
        SellOrder sellOrder = new SellOrder();
        helper.setDataForSellOrder(userID, sellOrder, sellOrderDTO);
        sellOrderRepository.save(sellOrder);

        List<SellOrderItem> listSellOrderItemRegistered = new ArrayList<>();
        for (SellOrderItemDTO sellOrderItemDTO : sellOrderDTO.getSellOrderItemDTOs()) {
            Long productID = sellOrderItemDTO.getProductID();
            Integer quantity = sellOrderItemDTO.getQuantity();
            SellOrderItem sellOrderItem = newSellOrderItemService.registerNewSellOrderItem(userID, sellOrder.getSellOrderID(), sellOrderItemDTO);
            listSellOrderItemRegistered.add(sellOrderItem);
            // decrease number of product in database
            helper.editProductQuantity(userID, productID, -quantity);
        }
        // real cost
        Double realCost = helper.getRealCost(listSellOrderItemRegistered);

        Double actualDiscountCash = helper.getActualDiscountCash(sellOrderDTO.getDiscountID(), realCost, sellOrder);

        helper.updateDiscount(sellOrderDTO.getDiscountID());

        double actualDiscountPercentage = Double.parseDouble(df.format(actualDiscountCash / realCost));
        double finalCost = Double.parseDouble(df.format(realCost - actualDiscountCash));
        sellOrder.setActualDiscountCash(Double.valueOf(df.format(actualDiscountCash)));
        sellOrder.setActualDiscountPercentage(actualDiscountPercentage);
        sellOrder.setFinalCost(finalCost);
        sellOrder.setRealCost(Double.valueOf(df.format(realCost)));
        this.sellOrderRepository.save(sellOrder);

        if (sellOrder.getStatus().equals(Status.FINISHED)) {
            Customer customer = helper.findCustomerInfo(userID, sellOrderDTO.getCustomer());
            helper.updateCustomerTotalSpend(customer.getTotalSpend() + finalCost, customer);
        }
        return sellOrder;
    }

    @Transactional
    public void deleteSellOrderBySellOrderID(Long userID, Long sellOrderID) {
        Optional<SellOrder> sellOrder = sellOrderRepository.findById(sellOrderID);

        try {
            if (sellOrder.isEmpty() || !Objects.equals(sellOrder.get().getUserID(), userID)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sell order not belong to user");
            }
            for (SellOrderItem sellOrderItem : sellOrder.get().getSellOrderItems()) {
                newSellOrderItemService.deleteSellOrderItem(userID, sellOrderItem.getSellOrderItemID());
            }
            sellOrderRepository.deleteById(sellOrderID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public SellOrder updateSellOrder(Long userID, SellOrder sellOrder) {
        Optional<SellOrder> oldSellOrder = this.sellOrderRepository
                .findSellOrderBySellOrderID(sellOrder.getSellOrderID());
        if ((oldSellOrder.isPresent()) && (oldSellOrder.get().getUserID().equals(userID))) {
            sellOrder.setUserID(userID);
            for (SellOrderItem sellOrderItem : sellOrder.getSellOrderItems()) {
                sellOrderItem.setUserID(userID);
            }
            newSellOrderItemService.saveAll(sellOrder.getSellOrderItems());

            try {
                Optional<Customer> customer = this.customerRepository.findCustomerByUserIDAndPhoneNumber(userID,
                        sellOrder.getCustomer().getPhoneNumber());
                if (customer.isPresent()) {
                    sellOrder.setCustomer(customer.get());
                } else {
                    sellOrder.getCustomer().setUserID(userID);
                    customerRepository.save(sellOrder.getCustomer());
                }
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer can not be added");
            }

            sellOrderRepository.save(sellOrder);
            return sellOrder;
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }
}
