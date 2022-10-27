package com.higroup.Buda.api.ingredient.view;


import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.repositories.IngredientRepository.ViewIngredientInfo;
import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/ingredient/view")
@CrossOrigin("*")
public class IngredientViewController {
    private final RequestUtil requestUtil;
    private final IngredientViewService ingredientViewService;
    @Autowired
    public IngredientViewController(IngredientViewService CRUDingredientService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.ingredientViewService = CRUDingredientService;
    }
    @GetMapping(path = "/ingredientID/{ingredientID}")
    public ResponseEntity<?> findIngredientByIngredientID(HttpServletRequest httpServletRequest, @PathVariable long ingredientID)
    {

        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientViewService.findIngredientByIngredientID(userID, ingredientID));
    }
    // @GetMapping(path = "/{ingredientName}")
    // public ResponseEntity<?> findIngredientByName(HttpServletRequest httpServletRequest, @PathVariable String ingredientName)
    // {
    //     Long userID = this.requestUtil.getUserID(httpServletRequest);
    //     return ResponseEntity.ok().body(this.ingredientService.findIngredientByName(userID, ingredientName));
    // }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllIngredientByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientViewService.findAllIngredientByUserID(userID));
    }
    @GetMapping(path = "/filter-all")
    public ResponseEntity<?> findAllFilterIngredientByCurrentUser(HttpServletRequest httpServletRequest, ViewIngredientInfo viewIngredientInfo, Pageable pageable)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientViewService.findAllFilterIngredient(userID, viewIngredientInfo, pageable));
    }
    @GetMapping(path = "/hidden/all")
    public ResponseEntity<?> findAllHiddenIngredientByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientViewService.findAllHiddenIngredientByUserID(userID));
    }
    @GetMapping(path = "/hide/{ingredientID}")
    public ResponseEntity<?> hideIngredientByIngredientID(HttpServletRequest httpServletRequest, @PathVariable Long ingredientID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientViewService.hideIngredientByIngredientID(userID, ingredientID));
    }
    @GetMapping(path = "/alert")
    public ResponseEntity<?> findAlertAmountIngredient(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientViewService.findAlertAmountIngredient(userID));
    }

}

