package com.higroup.Buda.api.business.buy.delay;

import java.util.Optional;

import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.BuyOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DelayPaymentBuyOrderService {
    private final BuyOrderRepository buyOrderRepository;
    @Autowired
    public DelayPaymentBuyOrderService(BuyOrderRepository buyOrderRepository)
    {
        this.buyOrderRepository = buyOrderRepository;
    }
    public BuyOrder delayPayment(Long userID, Long buyOrderID){
        Optional<BuyOrder> buyOrderOptional = this.buyOrderRepository.findBuyOrderByBuyOrderID(buyOrderID);
        if ((buyOrderOptional.isPresent()) && (buyOrderOptional.get().getUserID().equals(userID))){
            BuyOrder buyOrder = buyOrderOptional.get();
            if ((buyOrder.getStatus().equals(Status.FINISHED)) || (buyOrder.getStatus().equals(Status.CANCELLED))){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not delay a cancelled or finished order");
            }
            buyOrder.setStatus(Status.DELAYING);
            this.buyOrderRepository.save(buyOrder);
            return buyOrder;
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Buy order not found");
    }
}
