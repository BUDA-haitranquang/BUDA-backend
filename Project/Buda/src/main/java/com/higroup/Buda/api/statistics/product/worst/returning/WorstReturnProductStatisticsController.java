package com.higroup.Buda.api.statistics.product.worst.returning;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
}
