package com.higroup.Buda.api.statistics.activehours;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    @GetMapping(path = "/total")
    public ResponseEntity<?> findTotalCountGroupByHours(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getPremiumUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.activeHoursStatisticsService.findTotalCountGroupByHours(userID));
    }
    @GetMapping(path = "/this-month")
    public ResponseEntity<?> findCurrentMonthCountGroupByHours(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getPremiumUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.activeHoursStatisticsService.findCurrentMonthCountGroupByHours(userID));
    }
}
