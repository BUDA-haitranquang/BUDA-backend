package com.higroup.Buda.api.statistics.product.worst.selling;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
}
