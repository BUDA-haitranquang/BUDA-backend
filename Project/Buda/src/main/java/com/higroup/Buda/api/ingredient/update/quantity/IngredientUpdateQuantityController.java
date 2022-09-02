package com.higroup.Buda.api.ingredient.update.quantity;

import com.higroup.Buda.customDTO.QuantityLog;
import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("api/ingredient/update/quantity")
@CrossOrigin("*")
public class IngredientUpdateQuantityController {
    private final RequestUtil requestUtil;
    private final IngredientUpdateQuantityService ingredientUpdateQuantityService;
    @Autowired
    public IngredientUpdateQuantityController(IngredientUpdateQuantityService ingredientUpdateQuantityService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.ingredientUpdateQuantityService = ingredientUpdateQuantityService;
    }
    @PutMapping(path = "/{ingredientID}")
    public ResponseEntity<?> editIngredientQuantity(HttpServletRequest httpServletRequest, @PathVariable Long ingredientID, @RequestBody QuantityLog quantityLog)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientUpdateQuantityService.editIngredientQuantity(userID, ingredientID, quantityLog));
    }
}
