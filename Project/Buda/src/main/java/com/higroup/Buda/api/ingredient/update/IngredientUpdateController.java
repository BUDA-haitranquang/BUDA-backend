package com.higroup.Buda.api.ingredient.update;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("api/ingredient/update")
@CrossOrigin("*")
public class IngredientUpdateController {
    private final RequestUtil requestUtil;
    private final IngredientUpdateService ingredientUpdateService;
    @Autowired
    public IngredientUpdateController(IngredientUpdateService ingredientUpdateService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.ingredientUpdateService = ingredientUpdateService;
    }
    @PutMapping(path = "/{ingredientID}")
    public ResponseEntity<?> editIngredient(HttpServletRequest httpServletRequest, @PathVariable Long ingredientID, @RequestBody Ingredient ingredient) throws InvocationTargetException, IllegalAccessException {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ingredientUpdateService.editIngredient(userID, ingredientID, ingredient));
    }
}
