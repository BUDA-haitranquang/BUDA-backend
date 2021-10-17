package com.higroup.Buda.restcontroller;

import com.higroup.Buda.entities.Ingredient;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.services.IngredientService;
import com.higroup.Buda.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/Ingredient")
@CrossOrigin("*")
public class IngredientController {
    private final JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    private final IngredientService ingredientService;
    @Autowired
    public IngredientController(IngredientService ingredientService)
    {
        this.ingredientService = ingredientService;
    }
    @GetMapping(path = "/ingredientID/{ingredientID}")
    public ResponseEntity<?> findIngredientByIngredientID(HttpServletRequest request, @PathVariable long ingredientID)
    {   
        final String token = request.getHeader("Authorization").substring(7);

        Long userID = jwtTokenUtil.getUserIDFromToken(token);
        Ingredient ingredient = this.ingredientService.findIngredientByIngredientID(ingredientID);
        // if userid match ingredientID
        if(userID == ingredient.getUserID()){
            return ResponseEntity.ok(ingredient.toString());
        }
        // if not return unauthorized
        else{
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
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
