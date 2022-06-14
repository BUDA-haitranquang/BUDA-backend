package com.higroup.Buda.api.business.buy.status;

import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.BuyOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UpdateBuyOrderStatusService {
    private final BuyOrderRepository buyOrderRepository;

    @Autowired
    public UpdateBuyOrderStatusService(BuyOrderRepository buyOrderRepository) {
        this.buyOrderRepository = buyOrderRepository;
    }

    public BuyOrder updateBuyOrderStatus(Long userID, UpdateBuyOrderStatusDTO updateBuyOrderStatusDTO) {
        Optional<BuyOrder> buyOrder = this.buyOrderRepository.findBuyOrderByBuyOrderID(updateBuyOrderStatusDTO.getBuyOrderID());
        if (buyOrder.isEmpty() || !buyOrder.get().getUserID().equals(userID))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Buy order not found");
        }
        if (buyOrder.get().getStatus().equals(Status.CANCELLED))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cancelled order can not be modified");
        }
        buyOrder.get().setStatus(updateBuyOrderStatusDTO.getStatus());
        buyOrderRepository.save(buyOrder.get());
        return buyOrder.get();
    }
}
