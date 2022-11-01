package com.higroup.Buda.api.ingredient.quantitylog;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.repositories.IngredientLeftLogRepository.ViewIngredientLeftLogInfo;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ingredient/quantity-log")
@CrossOrigin("*")
public class IngredientLeftLogController {
    private final IngredientLeftLogService ingredientLeftLogService;
    private final RequestUtil requestUtil;
    @Autowired
    public IngredientLeftLogController(IngredientLeftLogService IngredientLeftLogService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.ingredientLeftLogService = IngredientLeftLogService;
    }
    // @PostMapping(path = "/userID/{userID}")
    // public ResponseEntity<?> registerNewIngredientLeftLog(@PathVariable Long userID, @RequestBody IngredientLeftLog IngredientLeftLog)
    // {
    //     return ResponseEntity.ok().body(this.ingredientLeftLogService.registerNewIngredientLeftLog(userID, IngredientLeftLog));
    // }
    @GetMapping(path = "/id/{ingredientLeftLogID}")
    public ResponseEntity<?> findIngredientLeftLogByIngredientLeftLogID(HttpServletRequest httpServletRequest, @PathVariable Long ingredientLeftLogID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientLeftLogService.findIngredientLeftLogByIngredientLeftLogID(userID, ingredientLeftLogID));
    }
    @GetMapping(path = "ingredient/{ingredientID}/all")
    public ResponseEntity<?> findAllIngredientLeftLogByIngredient(HttpServletRequest httpServletRequest, @PathVariable Long ingredientID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientLeftLogService.findAllIngredientLeftLogByIngredient(userID, ingredientID));
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllIngredientLeftLogByUserID(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientLeftLogService.findAllIngredientLeftLogByUserID(userID));
    }
    @GetMapping(path = "/filter-all")
    public ResponseEntity<?> findAllFilterIngredientLeftLogByUserID(HttpServletRequest httpServletRequest, @RequestBody ViewIngredientLeftLogFilter viewIngredientLeftLogFilter, Pageable pageable)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientLeftLogService.findAllFilterIngredientLeftLogByUserID(userID, viewIngredientLeftLogFilter, pageable));
    }
    @GetMapping(path = "staff/{staffID}/all")
    public ResponseEntity<?> findAllIngredientLeftLogByStaffID(HttpServletRequest httpServletRequest, @PathVariable Long staffID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientLeftLogService.findAllIngredientLeftLogByStaffID(userID, staffID));
    }
}
