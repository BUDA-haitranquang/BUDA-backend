package com.higroup.Buda.api.business.buy.delay.pay;

import java.time.ZonedDateTime;
import java.util.Optional;

import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.BuyOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PayDelayedBuyOrderService {
    private final BuyOrderRepository buyOrderRepository;
    @Autowired
    public PayDelayedBuyOrderService(BuyOrderRepository buyOrderRepository){
        this.buyOrderRepository = buyOrderRepository;
    }
    public BuyOrder payDelayedBuyOrder(Long userID, Long buyOrderID){
        Optional<BuyOrder> buyOrderOptional = this.buyOrderRepository.findBuyOrderByBuyOrderID(buyOrderID);
        if ((buyOrderOptional.isPresent()) && (buyOrderOptional.get().getUserID().equals(userID))){
            BuyOrder buyOrder = buyOrderOptional.get();
            if (!buyOrder.getStatus().equals(Status.DELAYING)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Buy order is not delayed");
            }
            buyOrder.setStatus(Status.FINISHED);
            buyOrder.setFinishTime(ZonedDateTime.now());
            this.buyOrderRepository.save(buyOrder);
            return buyOrder;
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Buy order not found");
    }
}
