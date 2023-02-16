package com.higroup.Buda.api.business.buy.neworder;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.higroup.Buda.api.business.buy.neworder.util.SupplierInfo;
import com.higroup.Buda.api.ingredient.create.IngredientCreateService;
import com.higroup.Buda.api.ingredient.view.IngredientViewService;
import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.BuyOrderItem;
import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.IngredientLeftLog;
import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.BuyOrderItemRepository;
import com.higroup.Buda.repositories.BuyOrderRepository;
import com.higroup.Buda.repositories.IngredientLeftLogRepository;
import com.higroup.Buda.repositories.IngredientRepository;

@Service
public class NewBuyOrderService {
    private SupplierInfo supplierInfo;
    private BuyOrderRepository buyOrderRepository;
    private BuyOrderItemRepository buyOrderItemRepository;
    private IngredientRepository ingredientRepository;
    private IngredientLeftLogRepository ingredientLeftLogRepository;
    private IngredientCreateService ingredientCreateService;
    private IngredientViewService ingredientViewService;

    @Autowired
    public NewBuyOrderService(SupplierInfo findSupplierInfo,
            BuyOrderRepository buyOrderRepository, BuyOrderItemRepository buyOrderItemRepository,
            IngredientRepository ingredientRepository, IngredientCreateService ingredientCreateService,
            IngredientViewService ingredientViewService, IngredientLeftLogRepository ingredientLeftLogRepository) {
        this.supplierInfo = findSupplierInfo;
        this.buyOrderItemRepository = buyOrderItemRepository;
        this.buyOrderRepository = buyOrderRepository;
        this.ingredientRepository = ingredientRepository;
        this.ingredientCreateService = ingredientCreateService;
        this.ingredientViewService = ingredientViewService;
        this.ingredientLeftLogRepository = ingredientLeftLogRepository;
    }

    private BuyOrderItem registerBuyOrderItem(Long userID, BuyOrder buyOrder, @Valid BuyOrderItemDTO buyOrderItemDTO) {
        Ingredient ingredient = retrieveIngredient(userID, buyOrderItemDTO);
        BuyOrderItem buyOrderItem = mapToBuyOrderItem(buyOrder, userID, ingredient, buyOrderItemDTO);
        return buyOrderItem;
    }
    
    private Ingredient retrieveIngredient(Long userID, BuyOrderItemDTO buyOrderItemDTO) {
        Long ingredientID = buyOrderItemDTO.getIngredient().getIngredientID();
        if (ingredientID == null) {
            return ingredientCreateService.createNewIngredient(userID, buyOrderItemDTO.getIngredient());
        }
        Ingredient ingredient = ingredientViewService.findIngredientByIngredientID(userID, ingredientID);
        if (!ingredient.getVisible()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ingredient is not visible");
        }
        return ingredient;
    }
    
    private BuyOrderItem mapToBuyOrderItem(BuyOrder buyOrder, Long userID, Ingredient ingredient, BuyOrderItemDTO buyOrderItemDTO) {
        BuyOrderItem buyOrderItem = new BuyOrderItem();
        buyOrderItem.setBuyOrder(buyOrder);
        buyOrderItem.setCreationTime(ZonedDateTime.now());
        buyOrderItem.setSupplierID(buyOrder.getSupplier().getSupplierID());
        buyOrderItem.setIngredient(ingredient);
        buyOrderItem.setUserID(userID);
        buyOrderItem.setQuantity(buyOrderItemDTO.getQuantity());
        if (buyOrderItemDTO.getPricePerUnit() != null) {
            buyOrderItem.setPricePerUnit(buyOrderItemDTO.getPricePerUnit());
        } else {
            if (ingredient.getPrice() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ingredient does not have price");
            }
            buyOrderItem.setPricePerUnit(ingredient.getPrice());
        }
        return buyOrderItem;
    }
    

    private IngredientLeftLog editIngredientLeftAmount(Long userID, Long ingredientID, Integer amountLeftChange) {
        Ingredient ingredient = this.ingredientRepository.findIngredientByIngredientID(ingredientID).get();
        ingredient.setAmountLeft(ingredient.getAmountLeft() + amountLeftChange);
        this.ingredientRepository.save(ingredient);
        String message = String.format("buy %d %s ingredient", amountLeftChange, ingredient.getName());
        IngredientLeftLog ingredientLeftLog = new IngredientLeftLog(ingredient, ZonedDateTime.now(), amountLeftChange, message, userID);
        return ingredientLeftLog;
        // this.ingredientLeftLogRepository.save(ingredientLeftLog);
    }

    @Transactional
    public BuyOrder createNewBuyOrder(Long userID, @Valid BuyOrderDTO buyOrderDTO) {
        BuyOrder buyOrder = new BuyOrder();
        buyOrder.setCreationTime(ZonedDateTime.now());
        if (buyOrderDTO.getStatus().equals(Status.FINISHED)) {

            buyOrder.setFinishTime(buyOrder.getCreationTime());
        }
        buyOrder.setStatus(buyOrderDTO.getStatus());
        buyOrder.setUserID(userID);
        buyOrder.setDescription(buyOrderDTO.getDescription());
        if ((buyOrderDTO.getTextID() != null) && (!buyOrderDTO.getTextID().equals(""))) {
            List<BuyOrder> buyOrderTexts = this.buyOrderRepository.findBuyOrderByUserIDAndTextID(userID,
                    buyOrderDTO.getTextID());
            if (buyOrderTexts.size() > 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Another buy order with this textID already exist");
            } else
                buyOrder.setTextID(buyOrderDTO.getTextID());
        }
        // get supplier info
        Supplier supplier = this.supplierInfo.findSupplierInfo(userID, buyOrderDTO.getSupplier());
        buyOrder.setSupplier(supplier);
        // save buy order
        this.buyOrderRepository.save(buyOrder);
        List<BuyOrderItem> buyOrderItems = new ArrayList<BuyOrderItem>();
        List<IngredientLeftLog> ingredientLeftLogs = new ArrayList<IngredientLeftLog>();
        Double totalCost = 0.0;
        for (BuyOrderItemDTO buyOrderItemDTO : buyOrderDTO.getBuyOrderItemDTOs()) {
            totalCost += buyOrderItemDTO.getPricePerUnit() * buyOrderItemDTO.getQuantity();
            buyOrderItems.add(registerBuyOrderItem(userID, buyOrder, buyOrderItemDTO));
            ingredientLeftLogs.add(editIngredientLeftAmount(userID, buyOrderItemDTO.getIngredient().getIngredientID(),
                    buyOrderItemDTO.getQuantity()));
        }
        this.buyOrderItemRepository.saveAll(buyOrderItems);
        Set<BuyOrderItem> bItems = new HashSet<BuyOrderItem>(buyOrderItems);
        buyOrder.setBuyOrderItems(bItems);
        this.ingredientLeftLogRepository.saveAll(ingredientLeftLogs);
        // calculate total cost
        buyOrder.setTotalCost(totalCost);
        this.buyOrderRepository.save(buyOrder);
        return buyOrder;
    }

}
