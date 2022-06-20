package com.higroup.Buda.api.business.sell.print;

import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.Store;
import com.higroup.Buda.repositories.SellOrderRepository;
import com.higroup.Buda.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PrintSellOrderService {
    private final SellOrderRepository SellOrderRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public PrintSellOrderService(SellOrderRepository SellOrderRepository, StoreRepository storeRepository) {
        this.SellOrderRepository = SellOrderRepository;
        this.storeRepository = storeRepository;
    }

    public PrintSellOrderDTO printSellOrder(Long userID, InputPrintSellOrderDTO inputPrintSellOrderDTO) {
        Optional<SellOrder> SellOrder = this.SellOrderRepository.findSellOrderBySellOrderID(inputPrintSellOrderDTO.getSellOrderID());
        if (SellOrder.isEmpty() || !SellOrder.get().getUserID().equals(userID))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sell order not found");
        }
        Store store = this.storeRepository.findStoreByStoreID(inputPrintSellOrderDTO.getStoreID());
        if (!store.getUserID().equals(userID))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found");
        }
        return new PrintSellOrderDTO(SellOrder.get(), store);
    }
}