package com.higroup.Buda.api.product.packaging.component;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin("*")
@RequestMapping("api/product/packaging/component")
public class ProductComponentController {
    private final ProductComponentService productComponentService;
    private final RequestUtil requestUtil;
    @Autowired
    public ProductComponentController(ProductComponentService productComponentService, RequestUtil requestUtil) {
        this.productComponentService = productComponentService;
        this.requestUtil = requestUtil;
    }
    // @GetMapping(path = "{productID}/{ingredientID}")
    // public ProductComponent findByProductAndIngredient (@PathVariable Long productID, @PathVariable Long ingredientID)
    // {
    //     return this.productComponentService.findByProductAndIngredient(productID,ingredientID);
    // }
    // @GetMapping(path = "/product/{productID}")
    // public ResponseEntity<?> findAllComponentByProductID(HttpServletRequest httpServletRequest, @PathVariable Long productID)
    // {
    //     Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
    //     return ResponseEntity.ok().body(this.productComponentService.findAllByProductID(userID, productID));
    // }
    // @PostMapping(path = "/{productID}/add/{ingredientID}")
    // public ResponseEntity<?> addIngredientToProduct(HttpServletRequest httpServletRequest, @PathVariable Long productID, @PathVariable Long ingredientID)
    // {
    //     Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
    //     return ResponseEntity.ok().body(this.productComponentService.addIngredientToProduct(userID, productID, ingredientID));
    // }
    @PostMapping(path = "/{productID}/remove/{ingredientID}")
    public ResponseEntity<?> removeIngredientFromProduct(HttpServletRequest httpServletRequest, @PathVariable Long productID, @PathVariable Long ingredientID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.productComponentService.removeIngredientFromProduct(userID, productID, ingredientID);
        return ResponseEntity.ok("Remove successfully");
    }

    @GetMapping(path = "contains/ingredient/{ingredientID}")
    public ResponseEntity<?> findAllProductContainIngredient(HttpServletRequest httpServletRequest, @PathVariable Long ingredientID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productComponentService.findAllProductContainIngredient(userID, ingredientID));
    }
}
