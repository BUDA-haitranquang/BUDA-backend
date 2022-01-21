package com.higroup.Buda.api.ingredient.delete;

import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/ingredient")
@CrossOrigin("*")
public class IngredientDeleteController {
    private final RequestUtil requestUtil;
    private final IngredientDeleteService ingredientDeleteService;
    @Autowired
    public IngredientDeleteController(IngredientDeleteService ingredientDeleteService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.ingredientDeleteService = ingredientDeleteService;
    }
    @DeleteMapping(path = "ingredientID/{ingredientID}")
    public ResponseEntity<?> deleteIngredientByIngredientID(HttpServletRequest httpServletRequest, @PathVariable Long ingredientID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.ingredientDeleteService.deleteIngredientByIngredient(userID, ingredientID);
        return ResponseEntity.ok().body("Delete successfully, this action can not be reversed");
    }
}
