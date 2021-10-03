package com.higroup.Buda.restcontroller;

import com.higroup.Buda.entities.ProductComponent;
import com.higroup.Buda.services.ProductComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin("*")
@RequestMapping("api/product-component")
public class ProductComponentController {
    private final ProductComponentService productComponentService;

    @Autowired
    public ProductComponentController(ProductComponentService productComponentService) {
        this.productComponentService = productComponentService;
    }
    @GetMapping(path = "{productID}/{ingredientID}")
    public ProductComponent findByProductAndIngredient (@PathVariable Long productID, @PathVariable Long ingredientID)
    {
        return this.productComponentService.findByProductAndIngredient(productID,ingredientID);
    }
}
