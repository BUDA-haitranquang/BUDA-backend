package com.higroup.Buda.api.statistics.activehours;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/statistics/active-hours")
@CrossOrigin("*")
public class ActiveHoursStatisticsController {
    private final ActiveHoursStatisticsService activeHoursStatisticsService;
    private final RequestUtil requestUtil;
    @Autowired
    public ActiveHoursStatisticsController(ActiveHoursStatisticsService activeHoursStatisticsService,
    RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.activeHoursStatisticsService = activeHoursStatisticsService;
    }
}
