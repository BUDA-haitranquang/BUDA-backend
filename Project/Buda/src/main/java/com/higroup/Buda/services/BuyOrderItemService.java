package com.higroup.Buda.services;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.BuyOrderItem;
import com.higroup.Buda.repositories.BuyOrderItemRepository;
import com.higroup.Buda.repositories.BuyOrderRepository;
import com.higroup.Buda.util.Checker.PresentChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BuyOrderItemService {
    private final BuyOrderItemRepository buyOrderItemRepository;
    private final BuyOrderRepository buyOrderRepository;
    private final PresentChecker presentChecker;
    @Autowired
    public BuyOrderItemService(BuyOrderItemRepository buyOrderItemRepository, BuyOrderRepository buyOrderRepository, PresentChecker presentChecker)
    {
        this.presentChecker = presentChecker;
        this.buyOrderRepository = buyOrderRepository;
        this.buyOrderItemRepository = buyOrderItemRepository;
    }
    public List<BuyOrderItem> findAllByBuyOrderID(Long buyOrderID)
    {
        Optional<BuyOrder> BuyOrder = this.buyOrderRepository.findBuyOrderByBuyOrderID(buyOrderID);
        return BuyOrder.map(this.buyOrderItemRepository::findAllBuyOrderItemByBuyOrder).orElse(null);
    }
    public BuyOrderItem updateBuyOrderItem(Long userID, BuyOrderItem buyOrderItem)
    {
        this.presentChecker.checkIdAndRepository(buyOrderItem.getBuyOrderItemID(), this.buyOrderItemRepository);
        if (buyOrderItem.getUserID()==userID)
        {
            this.buyOrderItemRepository.save(buyOrderItem);
        }
        else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        return buyOrderItem;
    }
    public void deleteBuyOrderItem(Long userID, Long buyOrderItemID)
    {
        Optional<BuyOrderItem> buyOrderItem = this.buyOrderItemRepository.findBuyOrderItemByBuyOrderItemID(buyOrderItemID);
        if ((buyOrderItem.isPresent()) && (buyOrderItem.get().getUserID() == userID))
        {
            this.buyOrderItemRepository.delete(buyOrderItem.get());
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BuyOrderItem not found");
        }
    }
}
