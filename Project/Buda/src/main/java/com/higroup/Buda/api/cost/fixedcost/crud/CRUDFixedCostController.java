package com.higroup.Buda.api.cost.fixedcost.crud;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.FixedCost;
import com.higroup.Buda.util.Checker.RequestUtil;

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
@RequestMapping("api/fixed-cost/crud")
public class CRUDFixedCostController {
    private final CRUDFixedCostService fixedCostService;
    private final RequestUtil requestUtil;
    @Autowired
    public CRUDFixedCostController(CRUDFixedCostService fixedCostService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.fixedCostService = fixedCostService;
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.fixedCostService.findAllByUserID(userID));
    }
    @PostMapping(path = "/new")
    public ResponseEntity<?> createNewFixedCost(HttpServletRequest httpServletRequest, @RequestBody FixedCost fixedCost)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.fixedCostService.createNewFixedCost(userID, fixedCost));
    }
    @DeleteMapping(path = "{fixedCostID}")
    public ResponseEntity<?> deleteFixedCostByFixedCostID(HttpServletRequest httpServletRequest, @PathVariable Long fixedCostID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.fixedCostService.deleteFixedCost(userID, fixedCostID);
        return ResponseEntity.ok().body("Delete successfully");
    }
    @PutMapping(path = "/update")
    public ResponseEntity<?> updateFixedCost(HttpServletRequest httpServletRequest, @RequestBody FixedCost fixedCost)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.fixedCostService.updateFixedCost(userID, fixedCost));
    }
    @GetMapping(path = "/hide/{fixedCostID}")
    public ResponseEntity<?> hideFixedCost(HttpServletRequest httpServletRequest, @PathVariable Long fixedCostID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.fixedCostService.hideFixedCost(userID, fixedCostID));
    }
    @GetMapping(path = "/all/hidden")
    public ResponseEntity<?> findAllHiddenFixedCostByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.fixedCostService.findAllHiddenFixedCostByUserID(userID));
    }
}
