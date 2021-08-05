package com.higroup.Buda.restcontroller;

import com.higroup.Buda.services.OtherCostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/other-cost")
@CrossOrigin("*")
public class OtherCostController {
    private final OtherCostService otherCostService;
    @Autowired
    public OtherCostController(OtherCostService otherCostService)
    {
        this.otherCostService = otherCostService;
    }
}
