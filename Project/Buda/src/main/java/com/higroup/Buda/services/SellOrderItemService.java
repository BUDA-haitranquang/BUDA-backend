package com.higroup.Buda.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.SellOrderItem;
import com.higroup.Buda.repositories.SellOrderItemRepository;
import com.higroup.Buda.repositories.SellOrderRepository;
import com.higroup.Buda.util.Checker.PresentChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SellOrderItemService {
    private final SellOrderItemRepository sellOrderItemRepository;
    private final SellOrderRepository sellOrderRepository;
    @Autowired
    public SellOrderItemService(SellOrderItemRepository sellOrderItemRepository, SellOrderRepository sellOrderRepository)
    {
        this.sellOrderRepository = sellOrderRepository;
        this.sellOrderItemRepository = sellOrderItemRepository;
    }
    @Autowired
    private PresentChecker presentChecker;
    public List<SellOrderItem> findAllBySellOrderID(Long userID, Long sellOrderID)
    {
        Optional<SellOrder> sellOrder = this.sellOrderRepository.findSellOrderBySellOrderID(sellOrderID);
        if (!sellOrder.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sell Order not found");
        }
        else if (sellOrder.get().getUserID() == userID){
            return this.sellOrderItemRepository.findAllSellOrderItemBySellOrder(sellOrder.get());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }
    public SellOrderItem updateSellOrderItem(Long userID, SellOrderItem sellOrderItem)
    {
        presentChecker.checkIdAndRepository(sellOrderItem.getSellOrderItemID(), sellOrderItemRepository);
        if (sellOrderItem.getUserID()==userID)
        {
            this.sellOrderItemRepository.save(sellOrderItem);
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserID does not match");
        }
        return sellOrderItem;
    }
    public void deleteSellOrderItem(Long userID, Long sellOrderItemID)
    {
        Optional<SellOrderItem> sellOrderItem = this.sellOrderItemRepository.findById(sellOrderItemID);
        if (sellOrderItem.isPresent())
        {
            if (sellOrderItem.get().getUserID() != userID)
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserID does not match");
            }
            this.sellOrderItemRepository.deleteById(sellOrderItemID);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SellOrderItem not found");
        }
    }
}
