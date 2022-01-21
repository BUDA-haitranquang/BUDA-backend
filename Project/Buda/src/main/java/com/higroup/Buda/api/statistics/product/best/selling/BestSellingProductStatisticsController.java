package com.higroup.Buda.api.statistics.product.best.selling;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
}
