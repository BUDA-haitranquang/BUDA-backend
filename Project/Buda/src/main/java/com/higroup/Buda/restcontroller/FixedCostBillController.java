package com.higroup.Buda.restcontroller;

import java.util.List;

import com.higroup.Buda.entities.FixedCostBill;
import com.higroup.Buda.services.FixedCostBillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/fixed-cost-bill")
@CrossOrigin("*")
public class FixedCostBillController {
    private final FixedCostBillService fixedCostBillService;
    @Autowired
    public FixedCostBillController(FixedCostBillService fixedCostBillService)
    {
        this.fixedCostBillService = fixedCostBillService;
    }
    @GetMapping("userID/{userID}/all")
    public List<FixedCostBill> findAllByUserID(@PathVariable Long userID)
    {
        return this.fixedCostBillService.findAllByUserID(userID);
    }
    @GetMapping("fixedCostID/{fixedCostID}/all")
    public List<FixedCostBill> findAllByFixedCostID(@PathVariable Long fixedCostID)
    {
        return this.fixedCostBillService.findAllByFixedCostID(fixedCostID);
    }
}
