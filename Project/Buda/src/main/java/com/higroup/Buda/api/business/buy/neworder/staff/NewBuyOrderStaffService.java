package com.higroup.Buda.api.business.buy.neworder.staff;

import java.time.ZonedDateTime;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.higroup.Buda.api.business.buy.neworder.BuyOrderDTO;
import com.higroup.Buda.api.business.buy.neworder.BuyOrderItemDTO;
import com.higroup.Buda.api.ingredient.create.IngredientCreateService;
import com.higroup.Buda.api.ingredient.view.IngredientViewService;
import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.BuyOrderItem;
import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.IngredientLeftLog;
import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.BuyOrderItemRepository;
import com.higroup.Buda.repositories.BuyOrderRepository;
import com.higroup.Buda.repositories.IngredientLeftLogRepository;
import com.higroup.Buda.repositories.IngredientRepository;
import com.higroup.Buda.repositories.StaffRepository;
import com.higroup.Buda.repositories.SupplierRepository;


@Service
public class NewBuyOrderStaffService {
    private SupplierRepository supplierRepository;
    private BuyOrderRepository buyOrderRepository;
    private BuyOrderItemRepository buyOrderItemRepository;
    private IngredientRepository ingredientRepository;
    private IngredientLeftLogRepository ingredientLeftLogRepository;
    private IngredientCreateService ingredientCreateService;
    private IngredientViewService ingredientViewService;
    private StaffRepository staffRepository;

    @Autowired
    public NewBuyOrderStaffService(SupplierRepository supplierRepository, BuyOrderRepository buyOrderRepository, BuyOrderItemRepository buyOrderItemRepository, IngredientRepository ingredientRepository, IngredientCreateService ingredientCreateService, 
    IngredientViewService ingredientViewService, IngredientLeftLogRepository ingredientLeftLogRepository,
    StaffRepository staffRepository)
    {
        this.staffRepository = staffRepository;
        this.supplierRepository = supplierRepository;
        this.buyOrderItemRepository = buyOrderItemRepository;
        this.buyOrderRepository = buyOrderRepository;
        this.ingredientRepository = ingredientRepository;
        this.ingredientCreateService = ingredientCreateService;
        this.ingredientViewService = ingredientViewService;
        this.ingredientLeftLogRepository = ingredientLeftLogRepository;
    }

    private Supplier findSupplierInfo(Long userID, Supplier requestSupplier)
    {
        if (requestSupplier == null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "request supplier is null");
        }
        String phoneNumber = requestSupplier.getPhoneNumber();
        if (phoneNumber == null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing supplier phone number");
        }
        Supplier supplier;
        Optional<Supplier> supplierOptional = this.supplierRepository.findSupplierByUserIDAndPhoneNumber(userID, phoneNumber);
        if (supplierOptional.isEmpty())
        {
            requestSupplier.setUserID(userID);
            supplier = this.supplierRepository.save(requestSupplier);
        }
        else
        {
            supplier = supplierOptional.get();
        }
        return supplier;
    }

    private BuyOrderItem registerBuyOrderItem(Long userID, Long buyOrderID, @Valid BuyOrderItemDTO buyOrderItemDTO)
    {
        Long ingredientID = buyOrderItemDTO.getIngredient().getIngredientID();
        Ingredient ingredient;
        // if ingredient not exists then create
        if (ingredientID == null)
        {
            ingredient = this.ingredientCreateService.createNewIngredient(userID, buyOrderItemDTO.getIngredient());
        }
        else
        {
            ingredient = this.ingredientViewService.findIngredientByIngredientID(userID, ingredientID);
            // check ingredient visible
            if (ingredient.getVisible().equals(false))
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ingredient is not visible");
            }
        }
        BuyOrder buyOrder = this.buyOrderRepository.findBuyOrderByBuyOrderID(buyOrderID).get();
        
        BuyOrderItem buyOrderItem = new BuyOrderItem();
        buyOrderItem.setBuyOrder(buyOrder);
        buyOrderItem.setCreationTime(ZonedDateTime.now());
        buyOrderItem.setSupplierID(buyOrder.getSupplier().getSupplierID());
        buyOrderItem.setIngredient(ingredient);
        buyOrderItem.setQuantity(buyOrderItemDTO.getQuantity());
        // use request pricePerunit if exists 
        if (buyOrderItemDTO.getPricePerUnit() != null)
        {
            buyOrderItem.setPricePerUnit(buyOrderItemDTO.getPricePerUnit());
        }
        else
        {
            if (ingredient.getPrice() == null) 
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ingredient does not have price");
            }
            buyOrderItem.setPricePerUnit(ingredient.getPrice());
        }
        return this.buyOrderItemRepository.save(buyOrderItem);
    }

    private void editIngredientLeftAmount(Long userID, Long staffID, Long ingredientID, Integer amountLeftChange)
    {
        Ingredient ingredient = this.ingredientRepository.findIngredientByIngredientID(ingredientID).get();
        ingredient.setAmountLeft(ingredient.getAmountLeft() + amountLeftChange);
        this.ingredientRepository.save(ingredient);
        String message = String.format("buy %d %s ingredient", -amountLeftChange, ingredient.getName());
        IngredientLeftLog ingredientLeftLog = new IngredientLeftLog();
        ingredientLeftLog.setIngredient(ingredient);
        ingredientLeftLog.setCreationTime(ZonedDateTime.now());
        ingredientLeftLog.setMessage(message);
        ingredientLeftLog.setUserID(userID);
        ingredientLeftLog.setStaffID(staffID);
        this.ingredientLeftLogRepository.save(ingredientLeftLog);
    }

    private Double getTotalCost(Long userID, Long staffID, Long buyOrderID, BuyOrderDTO buyOrderDTO)
    {

        Double totalCost = 0.0;
        for (BuyOrderItemDTO buyOrderItemDTO: buyOrderDTO.getBuyOrderItemDTOs())
        {
            BuyOrderItem buyOrderItem = this.registerBuyOrderItem(userID, buyOrderID, buyOrderItemDTO);
            Double buyOrderItemCost = buyOrderItem.getPricePerUnit() * buyOrderItem.getQuantity();
            totalCost += buyOrderItemCost;
            this.editIngredientLeftAmount(userID, staffID, buyOrderItem.getIngredient().getIngredientID(), buyOrderItem.getQuantity());
        }
        return totalCost;
    }

    @Transactional
    public BuyOrder createNewBuyOrder(Long userID, Long staffID, @Valid BuyOrderDTO buyOrderDTO) {
        BuyOrder buyOrder = new BuyOrder();
        Optional<Staff> staffOptional = this.staffRepository.findStaffByStaffID(staffID);
        if ((staffOptional.isEmpty()) || (!staffOptional.get().getUserID().equals(userID)))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid staff");
        }
        buyOrder.setCreationTime(ZonedDateTime.now());
        if (buyOrderDTO.getStatus().equals(Status.FINISHED)){
            buyOrder.setFinishTime(buyOrder.getCreationTime());
        }
        buyOrder.setUserID(userID);
        buyOrder.setStaff(staffOptional.get());
        // get supplier info
        Supplier supplier = this.findSupplierInfo(userID, buyOrderDTO.getSupplier());
        buyOrder.setSupplier(supplier);
        // save buy order
        this.buyOrderRepository.save(buyOrder);

        // calculate total cost 
        Double totalCost = this.getTotalCost(userID, staffID, buyOrder.getBuyOrderID(), buyOrderDTO);
        buyOrder.setTotalCost(totalCost);
        this.buyOrderRepository.save(buyOrder);
        return buyOrder;
    }

    @Transactional
    public void deleteBuyOrderByBuyOrderID(Long userID, Long buyOrderID)
    {
        Optional<BuyOrder> buyOrder = this.buyOrderRepository.findBuyOrderByBuyOrderID(buyOrderID);
        
        if ((buyOrder.isPresent()) && (userID.equals(buyOrder.get().getUserID())))
        {
            this.buyOrderItemRepository.deleteAll(buyOrder.get().getBuyOrderItems());
            this.buyOrderRepository.delete(buyOrder.get());
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Buy Order not found");
        }
    }

}
