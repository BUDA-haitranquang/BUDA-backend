package com.higroup.Buda.api.statistics.revenue;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics/revenue")
@CrossOrigin("*")
public class RevenueStatisticsController {
    private final RequestUtil requestUtil;
    private final RevenueStatisticsService revenueStatisticsService;
    @Autowired
    public RevenueStatisticsController(RequestUtil requestUtil, RevenueStatisticsService revenueStatisticsService)
    {
        this.requestUtil = requestUtil;
        this.revenueStatisticsService = revenueStatisticsService;
    }
    @GetMapping(path = "/monthly")
    public ResponseEntity<?> findRevenueGroupByMonth(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.revenueStatisticsService.findRevenueGroupByMonth(userID));
    }
    @GetMapping(path = "/weekly")
    public ResponseEntity<?> findRevenueGroupByWeek(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.revenueStatisticsService.findRevenueGroupByWeek(userID));
    }
    @GetMapping(path = "/weekday")
    public ResponseEntity<?> findRevenueGroupByWeekday(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.revenueStatisticsService.findRevenueGroupByWeekday(userID));
    }
    @GetMapping(path = "/this-month/daily")
    public ResponseEntity<?> findRevenueAllDaysCurrentMonth(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.revenueStatisticsService.findRevenueAllDaysCurrentMonth(userID));
    }
}
