package com.higroup.Buda.api.statistics.product.worst.selling;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/statistics/product/worst/selling")
@CrossOrigin("*")
public class WorstSellingProductStatisticsController {
    private final WorstSellingProductStatisticsService worstSellingProductStatisticsService;
    private final RequestUtil requestUtil;

    @Autowired
    public WorstSellingProductStatisticsController(WorstSellingProductStatisticsService worstSellingProductStatisticsService,
    RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.worstSellingProductStatisticsService = worstSellingProductStatisticsService;
    }

    @GetMapping(path = "/sell-number")
    public ResponseEntity<?> findNProductsTopSellNumber(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.worstSellingProductStatisticsService.findNProductsTopSellNumber(userID));
    }

    @GetMapping(path = "/profit")
    public ResponseEntity<?> findNProductsTopProfit(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.worstSellingProductStatisticsService.findNProductsLeastProfit(userID));
    }

    @GetMapping(path = "/revenue")
    public ResponseEntity<?> findNProductsTopRevenue(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.worstSellingProductStatisticsService.findNProductsLeastRevenue(userID));
    }
    

}
