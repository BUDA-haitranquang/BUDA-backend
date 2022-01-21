package com.higroup.Buda.api.ingredient.create;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/ingredient/new")
@CrossOrigin("*")
public class IngredientCreateController {
    private final RequestUtil requestUtil;
    private final IngredientCreateService ingredientCreateService;
    @Autowired
    public IngredientCreateController(IngredientCreateService ingredientCreateService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.ingredientCreateService = ingredientCreateService;
    }

    @PostMapping
    public ResponseEntity<?> createNewIngredient(HttpServletRequest httpServletRequest, @RequestBody Ingredient ingredient)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientCreateService.createNewIngredient(userID, ingredient));
    }
}
