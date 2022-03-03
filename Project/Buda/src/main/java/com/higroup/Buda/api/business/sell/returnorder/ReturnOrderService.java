package com.higroup.Buda.api.business.sell.returnorder;

import java.time.ZonedDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductLeftLog;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.SellOrderItem;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.ProductLeftLogRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.SellOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReturnOrderService {
    private final SellOrderRepository sellOrderRepository;
    private final ProductRepository productRepository;
    private final ProductLeftLogRepository productLeftLogRepository;
    @Autowired
    public ReturnOrderService(SellOrderRepository sellOrderRepository, 
    ProductRepository productRepository, ProductLeftLogRepository productLeftLogRepository){
        this.productLeftLogRepository = productLeftLogRepository;
        this.sellOrderRepository = sellOrderRepository;
        this.productRepository = productRepository;
    }
    @Transactional
    public SellOrder returnSellOrderBySellOrderID(Long userID, Long sellOrderID){
        Optional<SellOrder> sellOrder = this.sellOrderRepository.findSellOrderBySellOrderID(sellOrderID);
        if ((sellOrder.isPresent()) && (sellOrder.get().getUserID().equals(userID))){
            if ((sellOrder.get().getStatus().equals(Status.CANCELLED)) 
            || (sellOrder.get().getStatus().equals(Status.RETURNED))){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This sell order has already been " + sellOrder.get().getStatus());
            }
            sellOrder.get().setStatus(Status.RETURNED);
            this.sellOrderRepository.save(sellOrder.get());
            for (SellOrderItem sellOrderItem: sellOrder.get().getSellOrderItems()){
                ProductLeftLog productLeftLog = new ProductLeftLog();
                productLeftLog.setAmountLeftChange(sellOrderItem.getQuantity());
                productLeftLog.setProduct(sellOrderItem.getProduct());
                productLeftLog.setMessage("Order returned");
                productLeftLog.setCreationTime(ZonedDateTime.now());
                productLeftLog.setUserID(userID);
                Product product = sellOrderItem.getProduct();
                product.setAmountLeft(product.getAmountLeft() + sellOrderItem.getQuantity());
                this.productRepository.save(product);
                this.productLeftLogRepository.save(productLeftLog);
            }
            return sellOrder.get();
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sell Order not found");
    }
}
