package com.higroup.Buda.api.statistics.leftlog;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.higroup.Buda.util.Checker.RequestUtil;

@RestController
@RequestMapping("api/statistics/leftlog")
@CrossOrigin("*")
public class LeftLogStatisticController {
    private LeftLogStatisticService leftLogStatisticService;
    private RequestUtil requestUtil;

    @Autowired
    public LeftLogStatisticController(LeftLogStatisticService leftLogStatisticService, RequestUtil requestUtil){
        this.leftLogStatisticService = leftLogStatisticService;
        this.requestUtil = requestUtil;
    }

    @GetMapping(path = "/product")
    public ResponseEntity<?> getMostRemovedProduct(HttpServletRequest httpServletRequest, @PathVariable Long productID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.leftLogStatisticService.getMostRemovedProduct(userID));
    }

    @GetMapping(path = "/ingredient")
    public ResponseEntity<?> getMostRemovedIngredient(HttpServletRequest httpServletRequest, @PathVariable Long productID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.leftLogStatisticService.getMostRemovedIngredient(userID));
    }


}
