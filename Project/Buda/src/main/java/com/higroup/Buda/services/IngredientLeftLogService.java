package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.IngredientLeftLog;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.IngredientLeftLogRepository;
import com.higroup.Buda.repositories.IngredientRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public ResponseEntity<?> findIngredientLeftLogByIngredientLeftLogID(Long ingredientLeftLogID)
    {
        Optional<IngredientLeftLog> ingredientLeftLog = this.ingredientLeftLogRepository.findIngredientLeftLogByIngredientLeftLogID(ingredientLeftLogID);
        return ingredientLeftLog.<ResponseEntity<?>>map(leftLog -> ResponseEntity.ok().body(leftLog.toString())).orElseGet(() -> ResponseEntity.badRequest().body("Not found"));
    }
    public ResponseEntity<?> registerNewIngredientLeftLog(Long userID, IngredientLeftLog ingredientLeftLog)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            return ResponseEntity.badRequest().body("User not found");
        }
        ingredientLeftLog.setUserID(userID);
        this.ingredientLeftLogRepository.save(ingredientLeftLog);
        return ResponseEntity.ok().body(ingredientLeftLog.toString());
    }
    public List<IngredientLeftLog> findAllIngredientLeftLogByIngredient(Long ingredientID)
    {
        Optional<Ingredient> Ingredient = this.ingredientRepository.findIngredientByIngredientID(ingredientID);
        return Ingredient.map(this.ingredientLeftLogRepository::findAllIngredientLeftLogByIngredient).orElse(null);
    }
    public List<IngredientLeftLog> findAllIngredientLeftLogByStaffID(Long staffID)
    {
        return this.ingredientLeftLogRepository.findAllIngredientLeftLogByStaffID(staffID);
    }
    public List<IngredientLeftLog> findAllIngredientLeftLogByUserID(Long userID)
    {
        return this.ingredientLeftLogRepository.findAllIngredientLeftLogByUserID(userID);
    }
}
