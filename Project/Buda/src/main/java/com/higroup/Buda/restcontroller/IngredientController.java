package com.higroup.Buda.restcontroller;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/Ingredient")
@CrossOrigin("*")
public class IngredientController {
    private final IngredientService ingredientService;
    @Autowired
    public IngredientController(IngredientService ingredientService)
    {
        this.ingredientService = ingredientService;
    }
    @GetMapping(path = "/ingredientID/{ingredientID}")
    public Ingredient findIngredientByIngredientID(@PathVariable long ingredientID)
    {
        return this.ingredientService.findIngredientByIngredientID(ingredientID);
    }
    @GetMapping(path = "/{ingredientName}")
    public Ingredient findIngredientByName(@PathVariable String ingredientName)
    {
        return this.ingredientService.findIngredientByName(ingredientName);
    }
    @PostMapping(path = "new/userID/{userID}")
    public ResponseEntity<?> creatNewIngredient(@PathVariable Long userID, @RequestBody Ingredient ingredient)
    {
        return this.ingredientService.createNewIngredient(userID, ingredient);
    }
}
