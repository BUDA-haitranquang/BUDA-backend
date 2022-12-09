package com.higroup.Buda.api.business.sell.cancelorder;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.ProductLeftLog;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.SellOrderItem;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.ProductLeftLogRepository;
import com.higroup.Buda.repositories.SellOrderRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CancelOrderService {
    private final SellOrderRepository sellOrderRepository;
    private final ProductLeftLogRepository productLeftLogRepository;

    public CancelOrderService(SellOrderRepository sellOrderRepository, ProductLeftLogRepository productLeftLogRepository){
        this.productLeftLogRepository = productLeftLogRepository;
        this.sellOrderRepository = sellOrderRepository;
    }
    @Transactional
    public SellOrder cancelSellOrder(Long userID, Long sellOrderID){
        Optional<SellOrder> sellOrderOptional = this.sellOrderRepository.findSellOrderBySellOrderID(sellOrderID);
        if ((sellOrderOptional.isPresent()) && (sellOrderOptional.get().getUserID().equals(userID)))
        {
            SellOrder sellOrder = sellOrderOptional.get();
            if ((sellOrder.getStatus().equals(Status.FINISHED)) || (sellOrder.getStatus().equals(Status.CANCELLED))){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sell order has already been closed");
            }
            sellOrder.setStatus(Status.CANCELLED);
            this.sellOrderRepository.save(sellOrder);
            //Restore balance
            List<ProductLeftLog> productLeftLogs = new ArrayList<>();
            for (SellOrderItem sellOrderItem: sellOrder.getSellOrderItems()){
                ProductLeftLog productLeftLog = new ProductLeftLog();
                productLeftLog.setAmountLeftChange(sellOrderItem.getQuantity());
                productLeftLog.setCreationTime(ZonedDateTime.now());
                productLeftLog.setProduct(sellOrderItem.getProduct());
                productLeftLog.setUserID(userID);
                productLeftLog.setMessage("Cancelled order");
                Product product = sellOrderItem.getProduct();
                product.setAmountLeft(product.getAmountLeft() + sellOrderItem.getQuantity());
                productLeftLogs.add(productLeftLog);
                // this.productLeftLogRepository.save(productLeftLog);
            }
            this.productLeftLogRepository.saveAll(productLeftLogs);
            this.sellOrderRepository.save(sellOrder);
            return sellOrder;
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sell Order not found");
    }
}
