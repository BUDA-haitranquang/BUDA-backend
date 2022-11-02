package com.higroup.Buda.api.ingredient.quantitylog;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.IngredientLeftLog;
import com.higroup.Buda.repositories.IngredientLeftLogRepository;
import com.higroup.Buda.repositories.IngredientRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.repositories.IngredientLeftLogRepository.ViewIngredientLeftLogInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class IngredientLeftLogService {
    private final IngredientLeftLogRepository ingredientLeftLogRepository;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    @Autowired
    public IngredientLeftLogService(IngredientLeftLogRepository ingredientLeftLogRepository, UserRepository userRepository, IngredientRepository ingredientRepository)
    {
        this.userRepository = userRepository;
        this.ingredientLeftLogRepository = ingredientLeftLogRepository;
        this.ingredientRepository = ingredientRepository;
    }
    public IngredientLeftLog findIngredientLeftLogByIngredientLeftLogID(Long userID, Long ingredientLeftLogID)
    {
        Optional<IngredientLeftLog> ingredientLeftLog = this.ingredientLeftLogRepository.findIngredientLeftLogByIngredientLeftLogID(ingredientLeftLogID);
        if ((ingredientLeftLog.isPresent()) && (ingredientLeftLog.get().getUserID().equals(userID))) return ingredientLeftLog.get();
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Log not found");
    }
    @Transactional
    public IngredientLeftLog registerNewIngredientLeftLog(Long userID, IngredientLeftLog ingredientLeftLog)
    {
        ingredientLeftLog.setUserID(userID);
        this.ingredientLeftLogRepository.save(ingredientLeftLog);
        return ingredientLeftLog;
    }
    public List<IngredientLeftLog> findAllIngredientLeftLogByIngredient(Long userID, Long ingredientID)
    {
        Ingredient ingredient = this.ingredientRepository.findIngredientByIngredientID(ingredientID).get();
        if ((ingredient!=null) && (ingredient.getUserID().equals(userID))){
            return this.ingredientLeftLogRepository.findAllIngredientLeftLogByIngredient(userID, ingredient);
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ingredient not found");
        
    }
    public List<IngredientLeftLog> findAllIngredientLeftLogByStaffID(Long userID, Long staffID)
    {
        return this.ingredientLeftLogRepository.findAllIngredientLeftLogByStaffID(userID, staffID);
    }
    public List<IngredientLeftLog> findAllIngredientLeftLogByUserID(Long userID){
        return this.ingredientLeftLogRepository.findAllIngredientLeftLogByUserID(userID);
    }
    public IngredientLeftLogViewDTO findAllFilterIngredientLeftLogByUserID(Long userID, ViewIngredientLeftLogFilter viewIngredientLeftLogFilter, Pageable pageable)
    {
        Page<ViewIngredientLeftLogInfo> ingredientLeftLogs = this.ingredientLeftLogRepository.findAllFilterIngredientLeftLogByUserID(
            userID,
            viewIngredientLeftLogFilter.getIngredientSKU(), 
            viewIngredientLeftLogFilter.getName(),
            viewIngredientLeftLogFilter.getAmountLeft(),
        return new IngredientLeftLogViewDTO(ingredientLeftLogs.getTotalElements(), ingredientLeftLogs.toList());    
    }
