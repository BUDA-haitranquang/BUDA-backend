package com.higroup.Buda.api.retail;

import com.higroup.Buda.api.retail.customDTO.RetailCreateDTO;
import com.higroup.Buda.api.retail.customDTO.RetailCreateFromIngredientDTO;
import com.higroup.Buda.api.retail.customDTO.RetailCreateFromProductDTO;
import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("api/retail/create")
@CrossOrigin("*")
public class RetailCreateController {
    private final RequestUtil requestUtil;
    private final RetailCreateService retailCreateService;
    @Autowired
    public RetailCreateController(RetailCreateService retailCreateService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.retailCreateService = retailCreateService;
    }
    @PostMapping
    public ResponseEntity<?> createNewRetail(HttpServletRequest httpServletRequest, @Valid @RequestBody RetailCreateDTO retailCreateDTO)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.retailCreateService.createNewRetail(userID, retailCreateDTO));
    }
    @PostMapping(path = "/ingredient")
    public ResponseEntity<?> createNewRetailFromIngredient(HttpServletRequest httpServletRequest, @Valid @RequestBody RetailCreateFromIngredientDTO retailCreateFromIngredientDTO)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.retailCreateService.createNewRetailFromIngredient(userID, retailCreateFromIngredientDTO));
    }
    @PostMapping(path = "/product")
    public ResponseEntity<?> createNewRetailFromProduct(HttpServletRequest httpServletRequest, @Valid @RequestBody RetailCreateFromProductDTO retailCreateFromProductDTO)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.retailCreateService.createNewRetailFromProduct(userID, retailCreateFromProductDTO));
    }
}
