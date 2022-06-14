package com.higroup.Buda.api.business.sell.status;

import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.SellOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UpdateSellOrderStatusService {
    private final SellOrderRepository sellOrderRepository;

    @Autowired
    public UpdateSellOrderStatusService(SellOrderRepository sellOrderRepository) {
        this.sellOrderRepository = sellOrderRepository;
    }

    public SellOrder updateSellOrderStatus(Long userID, UpdateSellOrderStatusDTO updateSellOrderStatusDTO) {
        Optional<SellOrder> sellOrder = this.sellOrderRepository.findSellOrderBySellOrderID(updateSellOrderStatusDTO.getSellOrderID());
        if (sellOrder.isEmpty() || !sellOrder.get().getUserID().equals(userID))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sell order not found");
        }
        if (sellOrder.get().getStatus().equals(Status.CANCELLED))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cancelled order can not be modified");
        }
        sellOrder.get().setStatus(updateSellOrderStatusDTO.getStatus());
        sellOrderRepository.save(sellOrder.get());
        return sellOrder.get();
    }
}
