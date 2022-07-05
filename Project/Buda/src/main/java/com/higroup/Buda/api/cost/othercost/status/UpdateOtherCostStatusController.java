package com.higroup.Buda.api.cost.othercost.status;


import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("api/cost/othercost/status")
public class UpdateOtherCostStatusController {
    private final UpdateOtherCostStatusService updateOtherCostStatusService;
    private final RequestUtil requestUtil;
    
    @Autowired
    public UpdateOtherCostStatusController(UpdateOtherCostStatusService updateOtherCostStatusService, RequestUtil requestUtil) {
        this.updateOtherCostStatusService = updateOtherCostStatusService;
        this.requestUtil = requestUtil;
    }

    
    
    @PutMapping
    public ResponseEntity<?> printOtherCost(HttpServletRequest httpServletRequest, @RequestBody UpdateOtherCostStatusDTO updateOtherCostStatusDTO) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.updateOtherCostStatusService.updateOtherCostStatus(userID, updateOtherCostStatusDTO));
    }
}
