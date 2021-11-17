package com.higroup.Buda.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.FixedCost;
import com.higroup.Buda.services.FixedCostService;
import com.higroup.Buda.util.JwtTokenUtil;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties.Fixed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/fixed-cost")
public class FixedCostController {
    private final FixedCostService fixedCostService;
    private final RequestUtil requestUtil;
    @Autowired
    public FixedCostController(FixedCostService fixedCostService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.fixedCostService = fixedCostService;
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.fixedCostService.findAllByUserID(userID));
    }
    @PostMapping(path = "/new")
    public ResponseEntity<?> createNewFixedCost(HttpServletRequest httpServletRequest, @RequestBody FixedCost fixedCost)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.fixedCostService.createNewFixedCost(userID, fixedCost));
    }
}
