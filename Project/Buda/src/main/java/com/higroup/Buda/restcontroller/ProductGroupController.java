package com.higroup.Buda.restcontroller;

import java.util.List;

import com.higroup.Buda.entities.ProductGroup;
import com.higroup.Buda.services.ProductGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product-group")
@CrossOrigin("*")
public class ProductGroupController {
    private final ProductGroupService productGroupService;
    @Autowired
    public ProductGroupController(ProductGroupService productGroupService)
    {
        this.productGroupService = productGroupService;
    }
    @GetMapping(path = "userID/{userID}/all")
    public List<ProductGroup> findAllByUserID(@PathVariable Long userID)
    {
        return this.productGroupService.findAllByUserID(userID);
    }
}
