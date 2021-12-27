package com.higroup.Buda.services;

import java.lang.StackWalker.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.BuyOrderItem;
import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.repositories.BuyOrderItemRepository;
import com.higroup.Buda.repositories.BuyOrderRepository;
import com.higroup.Buda.repositories.IngredientRepository;
import com.higroup.Buda.util.Checker.PresentChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BuyOrderItemService {
    private final BuyOrderItemRepository buyOrderItemRepository;
    private final BuyOrderRepository buyOrderRepository;
    private final IngredientRepository ingredientRepository;
    private final PresentChecker presentChecker;
    @Autowired
    public BuyOrderItemService(BuyOrderItemRepository buyOrderItemRepository, BuyOrderRepository buyOrderRepository, PresentChecker presentChecker, IngredientRepository ingredientRepository)
    {
        this.ingredientRepository = ingredientRepository;
        this.presentChecker = presentChecker;
        this.buyOrderRepository = buyOrderRepository;
        this.buyOrderItemRepository = buyOrderItemRepository;
    }
    public List<BuyOrderItem> findAllByBuyOrderID(Long userID, Long buyOrderID)
    {
        Optional<BuyOrder> buyOrder = this.buyOrderRepository.findBuyOrderByBuyOrderID(buyOrderID);
        if ((buyOrder.isPresent()) && (buyOrder.get().getUserID().equals(userID)))
        {
            return this.buyOrderItemRepository.findAllBuyOrderItemByBuyOrder(buyOrder.get());
        }
        return Collections.emptyList();
    }
    @Transactional
    public BuyOrderItem updateBuyOrderItem(Long userID, BuyOrderItem buyOrderItem)
    {
        this.presentChecker.checkIdAndRepository(buyOrderItem.getBuyOrderItemID(), this.buyOrderItemRepository);
        if (buyOrderItem.getUserID().equals(userID))
        {
            this.buyOrderItemRepository.save(buyOrderItem);
        }
        else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        return buyOrderItem;
    }
    @Transactional
    public void deleteBuyOrderItem(Long userID, Long buyOrderItemID)
    {
        Optional<BuyOrderItem> buyOrderItem = this.buyOrderItemRepository.findBuyOrderItemByBuyOrderItemID(buyOrderItemID);
        if ((buyOrderItem.isPresent()) && (buyOrderItem.get().getUserID().equals(userID)))
        {
            this.buyOrderItemRepository.delete(buyOrderItem.get());
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BuyOrderItem not found");
        }
    }
    public List<BuyOrderItem> findAllBuyOrderItemByIngredientID(Long userID, Long ingredientID)
    {
        Optional<Ingredient> ingredient = this.ingredientRepository.findIngredientByIngredientID(ingredientID);
        if ((ingredient.isPresent()) && (ingredient.get().getUserID().equals(userID)))
        {
            return this.buyOrderItemRepository.findAllBuyOrderItemByIngredientID(ingredientID);
        }
        return Collections.emptyList();
    }
}
