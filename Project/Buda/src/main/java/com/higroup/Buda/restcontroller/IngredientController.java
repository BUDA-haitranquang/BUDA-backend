package com.higroup.Buda.restcontroller;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.services.IngredientService;
import com.higroup.Buda.util.JwtTokenUtil;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

@RestController
@RequestMapping("api/ingredient")
@CrossOrigin("*")
public class IngredientController {
    private final RequestUtil requestUtil;
    private final IngredientService ingredientService;
    @Autowired
    public IngredientController(IngredientService ingredientService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.ingredientService = ingredientService;
    }
    @GetMapping(path = "/ingredientID/{ingredientID}")
    public ResponseEntity<?> findIngredientByIngredientID(HttpServletRequest httpServletRequest, @PathVariable long ingredientID)
    {   

        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        Ingredient ingredient = this.ingredientService.findIngredientByIngredientID(ingredientID);
        // if userid match ingredientID
        if(Objects.equals(userID, ingredient.getUserID())){
            return ResponseEntity.ok().body(ingredient);
        }
        // if not return unauthorized
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }
    
    // @GetMapping(path = "/{ingredientName}")
    // public ResponseEntity<?> findIngredientByName(HttpServletRequest httpServletRequest, @PathVariable String ingredientName)
    // {
    //     Long userID = this.requestUtil.getUserID(httpServletRequest);
    //     return ResponseEntity.ok().body(this.ingredientService.findIngredientByName(userID, ingredientName));
    // }
    @PostMapping(path = "/new")
    public ResponseEntity<?> createNewIngredient(HttpServletRequest httpServletRequest, @RequestBody Ingredient ingredient)
    {   
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientService.createNewIngredient(userID, ingredient));
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllIngredientByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientService.findAllIngredientByUserID(userID));
    }
    @GetMapping(path = "/hidden/all")
    public ResponseEntity<?> findAllHiddenIngredientByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientService.findAllHiddenIngredientByUserID(userID));
    }
    @GetMapping(path = "/hide/{ingredientID}")
    public ResponseEntity<?> hideIngredientByIngredientID(HttpServletRequest httpServletRequest, @PathVariable Long ingredientID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientService.hideIngredientByIngredientID(userID, ingredientID));
    }
    @PostMapping(path = "/edit/quantity/{ingredientID}")
    public ResponseEntity<?> editIngredientQuantity(HttpServletRequest httpServletRequest, @PathVariable Long ingredientID, @RequestBody Integer amountLeftChange, @RequestBody String message)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientService.editIngredientQuantity(userID, ingredientID, amountLeftChange, message));
    }
    @GetMapping(path = "/alert")
    public ResponseEntity<?> findAlertAmountIngredient(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientService.findAlertAmountIngredient(userID));
    }
    @PostMapping(path = "/edit/{ingredientID}")
    public ResponseEntity<?> editIngredient(HttpServletRequest httpServletRequest, @PathVariable Long ingredientID, @RequestBody Ingredient ingredient) throws InvocationTargetException, IllegalAccessException {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientService.editIngredient(userID, ingredientID, ingredient));
    }
}
