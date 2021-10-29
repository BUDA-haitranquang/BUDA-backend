package com.higroup.Buda.restcontroller;

import com.higroup.Buda.entities.Plan;
import com.higroup.Buda.services.PlanService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    @GetMapping(path = "all")
    public ResponseEntity<?> findAllPlan()
    {
        return ResponseEntity.ok().body(this.planService.findAllPlan());
    }
    @PostMapping
    public ResponseEntity<?> createNewPlan(@RequestBody Plan plan)
    {
        //ADMIN moi duoc them
        return ResponseEntity.ok().body(this.planService.createNewPlan(plan));
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deletePlanByID(@PathVariable("id") Long id)
    {
        this.planService.deletePlanByID(id);
        return ResponseEntity.ok().body("Delete successfully");
    }
}
