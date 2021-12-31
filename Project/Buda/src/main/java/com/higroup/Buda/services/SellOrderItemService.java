package com.higroup.Buda.services;

import java.lang.reflect.InvocationTargetException;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.BeanUtils.NullAwareBeanUtilsBean;
import com.higroup.Buda.customDTO.RegisterSellOrderItem;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.SellOrderItem;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.SellOrderItemRepository;
import com.higroup.Buda.repositories.SellOrderRepository;
import com.higroup.Buda.util.Checker.PresentChecker;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SellOrderItemService {
    private final SellOrderItemRepository sellOrderItemRepository;
    private final SellOrderRepository sellOrderRepository;
    private final ProductRepository productRepository;
    private final PresentChecker presentChecker;
    @Autowired
    public SellOrderItemService(SellOrderItemRepository sellOrderItemRepository, SellOrderRepository sellOrderRepository, ProductRepository productRepository, PresentChecker presentChecker)
    {
        this.productRepository = productRepository;
        this.sellOrderRepository = sellOrderRepository;
        this.sellOrderItemRepository = sellOrderItemRepository;
        this.presentChecker = presentChecker;
    }

    public List<SellOrderItem> findAllBySellOrderID(Long userID, Long sellOrderID)
    {
        Optional<SellOrder> sellOrder = this.sellOrderRepository.findSellOrderBySellOrderID(sellOrderID);
        if (!sellOrder.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sell Order not found");
        }
        else if (sellOrder.get().getUserID().equals(userID)){
            return this.sellOrderItemRepository.findAllSellOrderItemBySellOrder(sellOrder.get());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }
    // @Transactional
    // public SellOrderItem updateSellOrderItem(Long userID, SellOrderItem sellOrderItem)
    // {
    //     presentChecker.checkIdAndRepository(sellOrderItem.getSellOrderItemID(), this.sellOrderItemRepository);
    //     if (sellOrderItem.getUserID().equals(userID))
    //     {
    //         this.sellOrderItemRepository.save(sellOrderItem);
    //     }
    //     else{
    //         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserID does not match");
    //     }
    //     return sellOrderItem;
    // }

    @Transactional
    public SellOrderItem updateSellOrderItem(Long userID, Long sellOrderItemID,  SellOrderItem sellOrderItem) throws IllegalAccessException, InvocationTargetException{
//        presentChecker.checkIdAndRepository(sellOrderItem.getSellOrderItemID(), this.sellOrderItemRepository);
        
        SellOrderItem exist_sellOrderItem = this.sellOrderItemRepository.findById(sellOrderItemID).get();
        if(exist_sellOrderItem == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found sell order item with orderitemID");
        }
            
        if(!exist_sellOrderItem.getUserID().equals(userID))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found sell order item with userid");
        }
        BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
        notNull.copyProperties(exist_sellOrderItem, sellOrderItem);
        this.sellOrderItemRepository.save(exist_sellOrderItem);
        return exist_sellOrderItem;
    }

    @Transactional
    public void deleteSellOrderItem(Long userID, Long sellOrderItemID)
    {
        Optional<SellOrderItem> sellOrderItem = this.sellOrderItemRepository.findById(sellOrderItemID);
        if ((sellOrderItem.isPresent()) && (sellOrderItem.get().getUserID().equals(userID)))
        {
            this.sellOrderItemRepository.delete(sellOrderItem.get());
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SellOrderItem not found");
        }
    }
    public List<SellOrderItem> findAllSellOrderItemByProductID(Long userID, Long productID)
    {
        Product product = this.productRepository.findProductByProductID(productID);
        if (!product.equals(null) && (product.getUserID().equals(userID)))
        {
            return this.sellOrderItemRepository.findAllSellOrderItemByProductID(productID);
        }
        return Collections.emptyList();
    }

    public SellOrderItem findSellOrderItemByID(Long userID, Long sellOrderItemID){
        if (this.sellOrderItemRepository.findById(sellOrderItemID).isPresent())
        {
            SellOrderItem sellOrderItem = this.sellOrderItemRepository.findById(sellOrderItemID).get();
            if(sellOrderItem.getUserID().equals(userID)){
                return sellOrderItem;
            }
            if(!sellOrderItem.getUserID().equals(userID)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserID does not have sellorderItem");
            }
        }
        return null; 
    }
    @Transactional
    public SellOrderItem registerNewSellOrderItem(Long userID, RegisterSellOrderItem registerSellOrderItem){
        Product product = (Product) this.presentChecker.checkIdAndRepository(registerSellOrderItem.getProductID(), this.productRepository);
        SellOrder sellOrder = (SellOrder) this.presentChecker.checkIdAndRepository(registerSellOrderItem.getSellOrderID(), this.sellOrderRepository);
        if(!product.getVisible())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "product is not visible");
        }
        if(sellOrder.checkProductExistInItems(product))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exits in list items of sell order");
        }
        if(product.getAmountLeft() < registerSellOrderItem.getQuantity()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product doesnt have enough quantity left");
        }

        SellOrderItem sellOrderItem = new SellOrderItem();
        sellOrderItem.setSellOrder(sellOrder);
        sellOrderItem.setProduct(product);
        sellOrderItem.setQuantity(registerSellOrderItem.getQuantity());
        sellOrderItem.setPricePerUnit(product.getSellingPrice());
        sellOrderItem.setCostPerUnit(product.getCostPerUnit());
        sellOrderItem.setUserID(userID);
        sellOrderItem.setCreationTime(sellOrder.getCreationTime());
        Double actualTotalSale = product.getSellingPrice() * registerSellOrderItem.getQuantity();
        sellOrderItem.setActualTotalSale(actualTotalSale);
        sellOrderItem.setGender(sellOrder.getGender());
        sellOrderItem.setAgeGroup(sellOrder.getAgeGroup());
        this.sellOrderItemRepository.save(sellOrderItem);
        // add sellorderitem to sellorder
        sellOrder.getSellOrderItems().add(sellOrderItem);
        return sellOrderItem;
    }
}


