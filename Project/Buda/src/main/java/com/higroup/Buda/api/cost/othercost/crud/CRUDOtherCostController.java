package com.higroup.Buda.api.cost.othercost.crud;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.OtherCost;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/other-cost/crud")
@CrossOrigin("*")
public class CRUDOtherCostController {
    private final CRUDOtherCostService otherCostService;
    private final RequestUtil requestUtil;
    @Autowired
    public CRUDOtherCostController(CRUDOtherCostService otherCostService, RequestUtil requestUtil)
    {
        this.otherCostService = otherCostService;
        this.requestUtil = requestUtil;
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAllOtherCostByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.otherCostService.findAllOtherCostByUserID(userID));
    }
    @GetMapping("/all/incompleted")
    public ResponseEntity<?> findAllIncompletedOtherCostByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.otherCostService.findAllIncompletedOtherCostByUserID(userID));
    }
    @GetMapping("/all/last-x-days/{X}")
    public ResponseEntity<?> findAllOtherCostByCurrentUserLastXDays(HttpServletRequest httpServletRequest, @PathVariable Long X)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.otherCostService.findAllOtherCostByUserIDLastXDays(userID, X));
    }
    @PostMapping("/new")
    public ResponseEntity<?> createNewOtherCost(HttpServletRequest httpServletRequest, @RequestBody OtherCost otherCost)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.otherCostService.createNewOtherCost(userID, otherCost));
    }
    @GetMapping("/hide/{otherCostID}")
    public ResponseEntity<?> hideOtherCost(HttpServletRequest httpServletRequest, @PathVariable Long otherCostID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.otherCostService.hideOtherCost(userID, otherCostID));
    }
    @GetMapping("/all/hidden")
    public ResponseEntity<?> findAllHiddenOtherCostByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.otherCostService.findAllHiddenOtherCostByUserID(userID));
    }
}
