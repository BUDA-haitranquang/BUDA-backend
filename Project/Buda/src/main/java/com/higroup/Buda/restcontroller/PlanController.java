package com.higroup.Buda.restcontroller;

import com.higroup.Buda.entities.Plan;
import com.higroup.Buda.services.PlanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/plan")
public class PlanController {
    private final PlanService planService;
    @Autowired
    public PlanController(PlanService planService)
    {
        this.planService = planService;
    }
    @PutMapping
    public void addPlan(@RequestBody Plan plan)
    {
        this.planService.addPlan(plan);
    }
    @DeleteMapping(path = "{id}")
    public void deletePlanByID(@PathVariable("id") Long id)
    {
        this.planService.deletePlanByID(id);
    }
}
