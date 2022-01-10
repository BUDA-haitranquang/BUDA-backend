package com.higroup.Buda.api.plan.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/plan/view")
public class PlanController {
    private final PlanService planService;
    @Autowired
    public PlanController(PlanService planService)
    {
        this.planService = planService;
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllPlan()
    {
        return ResponseEntity.ok().body(this.planService.findAllPlan());
    }
}
