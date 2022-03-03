package com.higroup.Buda.api.business.sell.finish;

import java.time.ZonedDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.SellOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FinishSellOrderService {
    private final SellOrderRepository sellOrderRepository;
    @Autowired
    public FinishSellOrderService(SellOrderRepository sellOrderRepository){
        this.sellOrderRepository = sellOrderRepository;
    }
    @Transactional
    public SellOrder finishSellOrder(Long userID, Long sellOrderID){
        Optional<SellOrder> sellOrderOptional = this.sellOrderRepository.findSellOrderBySellOrderID(sellOrderID);
        if ((sellOrderOptional.isPresent()) && (sellOrderOptional.get().getUserID().equals(userID))){
            SellOrder sellOrder = sellOrderOptional.get();
            if ((sellOrder.getStatus().equals(Status.FINISHED)) || (sellOrder.getStatus().equals(Status.CANCELLED))
            || (sellOrder.getStatus().equals(Status.RETURNED))){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sell order has already been " + sellOrder.getStatus());
            }
            sellOrder.setStatus(Status.FINISHED);
            sellOrder.setFinishTime(ZonedDateTime.now());
            return sellOrder;
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sell Order not found");
    }
}
