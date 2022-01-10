package com.higroup.Buda.api.business.sell.returnorder;

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
public class ReturnOrderService {
    private final SellOrderRepository sellOrderRepository;
    @Autowired
    public ReturnOrderService(SellOrderRepository sellOrderRepository){
        this.sellOrderRepository = sellOrderRepository;
    }
    @Transactional
    public SellOrder returnSellOrderBySellOrderID(Long userID, Long sellOrderID){
        Optional<SellOrder> sellOrder = this.sellOrderRepository.findSellOrderBySellOrderID(sellOrderID);
        if ((sellOrder.isPresent()) && (sellOrder.get().getUserID().equals(userID))){
            sellOrder.get().setStatus(Status.RETURNED);
            this.sellOrderRepository.save(sellOrder.get());
            return sellOrder.get();
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sell Order not found");
    }
}
