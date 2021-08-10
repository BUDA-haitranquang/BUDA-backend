package com.higroup.Buda.restcontroller;

import java.util.List;

import com.higroup.Buda.entities.FixedCost;
import com.higroup.Buda.services.FixedCostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/fixed-cost")
public class FixedCostController {
    private final FixedCostService fixedCostService;
    @Autowired
    public FixedCostController(FixedCostService fixedCostService)
    {
        this.fixedCostService = fixedCostService;
    }
    @GetMapping(path = "userID/{userID}/all")
    public List<FixedCost> findAllByUserID(@PathVariable Long userID)
    {
        return this.fixedCostService.findAllByUserID(userID);
    }
}
