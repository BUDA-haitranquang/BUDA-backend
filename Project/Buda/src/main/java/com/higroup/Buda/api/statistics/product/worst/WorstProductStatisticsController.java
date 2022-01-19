package com.higroup.Buda.api.statistics.product.worst;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/statistics/product/worst")
@CrossOrigin("*")
public class WorstProductStatisticsController {
    private final WorstProductStatisticsService worstProductStatisticsService;
    private final RequestUtil requestUtil;
    @Autowired
    public WorstProductStatisticsController(WorstProductStatisticsService worstProductStatisticsService,
    RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.worstProductStatisticsService = worstProductStatisticsService;
    }
}
