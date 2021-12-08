package com.higroup.Buda.restcontroller;

import java.util.List;

import com.higroup.Buda.entities.IngredientLeftLog;
import com.higroup.Buda.services.IngredientLeftLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/Ingredient-left-log")
@CrossOrigin("*")
public class IngredientLeftLogController {
    private final IngredientLeftLogService IngredientLeftLogService;
    @Autowired
    public IngredientLeftLogController(IngredientLeftLogService IngredientLeftLogService)
    {
        this.IngredientLeftLogService = IngredientLeftLogService;
    }
    @PostMapping(path = "/userID/{userID}")
    public ResponseEntity<?> registerNewIngredientLeftLog(@PathVariable Long userID, @RequestBody IngredientLeftLog IngredientLeftLog)
    {
        return ResponseEntity.ok().body(this.IngredientLeftLogService.registerNewIngredientLeftLog(userID, IngredientLeftLog));
    }
    @GetMapping(path = "/id/{IngredientLeftLogID}")
    public ResponseEntity<?> findIngredientLeftLogByIngredientLeftLogID(@PathVariable Long IngredientLeftLogID)
    {
        return ResponseEntity.ok().body(this.IngredientLeftLogService.findIngredientLeftLogByIngredientLeftLogID(IngredientLeftLogID));
    }
    @GetMapping(path = "Ingredient/{IngredientID}/all")
    public List<IngredientLeftLog> findAllIngredientLeftLogByIngredient(@PathVariable Long IngredientID)
    {
        return this.IngredientLeftLogService.findAllIngredientLeftLogByIngredient(IngredientID);
    }
    @GetMapping(path = "user/{userID}/all")
    public List<IngredientLeftLog> findAllIngredientLeftLogByUserID(@PathVariable Long userID)
    {
        return this.IngredientLeftLogService.findAllIngredientLeftLogByUserID(userID);
    }
    @GetMapping(path = "staff/{staffID}/all")
    public List<IngredientLeftLog> findAllIngredientLeftLogByStaffID(@PathVariable Long staffID)
    {
        return this.IngredientLeftLogService.findAllIngredientLeftLogByStaffID(staffID);
    }
}
