package com.higroup.Buda.api.business.buy.print;

import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.Store;
import com.higroup.Buda.repositories.BuyOrderRepository;
import com.higroup.Buda.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PrintBuyOrderService {
    private final BuyOrderRepository buyOrderRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public PrintBuyOrderService(BuyOrderRepository buyOrderRepository, StoreRepository storeRepository) {
        this.buyOrderRepository = buyOrderRepository;
        this.storeRepository = storeRepository;
    }

    public PrintBuyOrderDTO printBuyOrder(Long userID, InputPrintBuyOrderDTO inputPrintBuyOrderDTO) {
        Optional<BuyOrder> buyOrder = this.buyOrderRepository.findBuyOrderByBuyOrderID(inputPrintBuyOrderDTO.getBuyOrderID());
        if (buyOrder.isEmpty() || !buyOrder.get().getUserID().equals(userID))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Buy order not found");
        }
        Store store;
        if (inputPrintBuyOrderDTO.getStoreID() == null) {
            store = this.storeRepository.findFirstByUserID(userID);
        }
        else {
            store = this.storeRepository.findStoreByStoreID(inputPrintBuyOrderDTO.getStoreID());
        }
        if (!store.getUserID().equals(userID))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found");
        }
        return new PrintBuyOrderDTO(buyOrder.get(), store);
    }
}