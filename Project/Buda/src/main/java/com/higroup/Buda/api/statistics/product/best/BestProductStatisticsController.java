package com.higroup.Buda.api.statistics.product.best;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/statistics/product/best")
@CrossOrigin("*")
public class BestProductStatisticsController {
    private final BestProductStatisticsService bestProductStatisticsService;
    private final RequestUtil requestUtil;
    @Autowired
    public BestProductStatisticsController(BestProductStatisticsService bestProductStatisticsService,
    RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.bestProductStatisticsService = bestProductStatisticsService;
    }
}
