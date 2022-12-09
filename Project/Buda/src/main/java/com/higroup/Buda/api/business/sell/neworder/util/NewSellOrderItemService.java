package com.higroup.Buda.api.business.sell.neworder.util;

import com.higroup.Buda.api.business.sell.neworder.SellOrderItemDTO;
import com.higroup.Buda.entities.*;
import com.higroup.Buda.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Service
public class NewSellOrderItemService {
    private final ProductRepository productRepository;
    private final ProductComponentRepository productComponentRepository;
    private final IngredientRepository ingredientRepository;
    private final SellOrderRepository sellOrderRepository;
    private final SellOrderItemRepository sellOrderItemRepository;

    @Autowired
    public NewSellOrderItemService(ProductRepository productRepository,
                                   ProductComponentRepository productComponentRepository,
                                   IngredientRepository ingredientRepository,
                                   SellOrderRepository sellOrderRepository,
                                   SellOrderItemRepository sellOrderItemRepository) {
        this.productRepository = productRepository;
        this.productComponentRepository = productComponentRepository;
        this.ingredientRepository = ingredientRepository;
        this.sellOrderRepository = sellOrderRepository;
        this.sellOrderItemRepository = sellOrderItemRepository;
    }

    @Transactional
    public void saveAll(Collection<SellOrderItem> sellOrderItems) {
        sellOrderItemRepository.saveAll(sellOrderItems);
    }

    // sell order item delete
    @Transactional
    public void deleteSellOrderItem(Long userID, Long sellOrderItemID) {
        Optional<SellOrderItem> sellOrderItem = this.sellOrderItemRepository.findById(sellOrderItemID);
        if ((sellOrderItem.isPresent()) && (sellOrderItem.get().getUserID().equals(userID))) {
            this.sellOrderItemRepository.delete(sellOrderItem.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SellOrderItem not found");
        }
    }


    @Transactional
    public SellOrderItem registerNewSellOrderItem(Long userID, Long sellOrderID,
                                                  @Valid SellOrderItemDTO sellOrderItemDTO) {
        Optional<Product> opProduct = productRepository.findProductByProductID(sellOrderItemDTO.getProductID());
        if (opProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Product %s not found", sellOrderItemDTO.getProductID()));
        }
        Product product = opProduct.get();

        Optional<SellOrder> sellOrderOptional = sellOrderRepository.findSellOrderBySellOrderID(sellOrderID);
        if (sellOrderOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("sell order %s not found", sellOrderID));
        }
        SellOrder sellOrder = sellOrderOptional.get();

        // check product visible
        if (!product.getVisible()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("product %s is not visible", product.getName()));
        }
        // check product belong to user
        if (!product.getUserID().equals(userID)) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    String.format("product %s is not belong to user", product.getName()));
        }
        // check enough quantity
        if (product.getAmountLeft() < sellOrderItemDTO.getQuantity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Product %s doesnt have enough quantity left", product.getName()));
        } else {
            for (ProductComponent productComponent : productComponentRepository
                    .findAllByProductID(product.getProductID())) {
                Ingredient ingredient = productComponent.getIngredient();
                ingredient.setAmountLeft(ingredient.getAmountLeft() - Math.min(ingredient.getAmountLeft(),
                        sellOrderItemDTO.getQuantity() * (Math.round(productComponent.getRequiredQuantity()))));
                this.ingredientRepository.save(ingredient);
            }
        }
        SellOrderItem sellOrderItem = new SellOrderItem();
        sellOrderItem.setSellOrder(sellOrder);
        sellOrderItem.setProduct(product);
        if (sellOrderItemDTO.getPricePerUnit() == null) {
            sellOrderItem.setPricePerUnit(product.getSellingPrice());
        } else
            sellOrderItem.setPricePerUnit(sellOrderItemDTO.getPricePerUnit());
        sellOrderItem.setCostPerUnit(product.getCostPerUnit());
        sellOrderItem.setQuantity(sellOrderItemDTO.getQuantity());
        sellOrderItem.setUserID(userID);
        sellOrderItem.setCreationTime(sellOrder.getCreationTime());
        Double actualTotalSale = sellOrderItem.getPricePerUnit() * sellOrderItem.getQuantity();
        sellOrderItem.setActualTotalSale(actualTotalSale);
        sellOrderItem.setGender(sellOrder.getGender());
        sellOrderItem.setAgeGroup(sellOrder.getAgeGroup());

        return sellOrderItemRepository.save(sellOrderItem);
    }


}
