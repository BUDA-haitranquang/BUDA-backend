package com.higroup.Buda.api.statistics.product.worst.returning;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/statistics/product/worst/returning")
@CrossOrigin("*")
public class WorstReturnProductStatisticsController {
    private final WorstReturnProductStatisticsService worstReturnProductStatisticsService;
    private final RequestUtil requestUtil;
    @Autowired
    public WorstReturnProductStatisticsController(WorstReturnProductStatisticsService worstReturnProductStatisticsService,
    RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.worstReturnProductStatisticsService = worstReturnProductStatisticsService;
    }

    @GetMapping(path = "/return-number")
    public ResponseEntity<?> findNProductsMostReturnNumber(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.worstReturnProductStatisticsService.findNProductsMostReturnNumber(userID));
    }

    @GetMapping(path = "/return-price")
    public ResponseEntity<?> findNProductsMostReturnPrice(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.worstReturnProductStatisticsService.findNProductsMostReturnPrice(userID));
    }
}
