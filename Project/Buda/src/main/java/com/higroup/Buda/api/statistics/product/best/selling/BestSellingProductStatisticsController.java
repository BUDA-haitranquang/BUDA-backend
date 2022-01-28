package com.higroup.Buda.api.statistics.product.best.selling;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/statistics/product/best/selling")
@CrossOrigin("*")
public class BestSellingProductStatisticsController {
    private final BestSellingProductStatisticsService bestSellingProductStatisticsService;
    private final RequestUtil requestUtil;
    @Autowired
    public BestSellingProductStatisticsController(BestSellingProductStatisticsService bestSellingProductStatisticsService,
    RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.bestSellingProductStatisticsService = bestSellingProductStatisticsService;
    }

    @GetMapping(path = "/sell-number")
    public ResponseEntity<?> findNProductsTopSellNumber(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.bestSellingProductStatisticsService.findNProductsTopSellNumber(userID));
    }

    @GetMapping(path = "/profit")
    public ResponseEntity<?> findNProductsTopProfit(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.bestSellingProductStatisticsService.findNProductsTopProfit(userID));
    }

    @GetMapping(path = "/revenue")
    public ResponseEntity<?> findNProductsTopRevenue(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.bestSellingProductStatisticsService.findNProductsTopRevenue(userID));
    }
}
