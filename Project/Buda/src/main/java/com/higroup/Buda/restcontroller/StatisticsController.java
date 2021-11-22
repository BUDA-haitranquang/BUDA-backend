package com.higroup.Buda.restcontroller;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.services.StatisticsService;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;
    private final RequestUtil requestUtil;
    @Autowired
    public StatisticsController(StatisticsService statisticsService, RequestUtil requestUtil)
    {
        this.statisticsService = statisticsService;
        this.requestUtil = requestUtil;
    }
    @GetMapping(path = "/age-group/total")
    public ResponseEntity<?> findTotalSpendOfAgeGroupByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.statisticsService.findTotalSpendOfAgeGroupByUserID(userID));
    }
    @GetMapping(path = "/gender/total")
    public ResponseEntity<?> findTotalSpendOfGenderByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.statisticsService.findTotalSpendOfGenderByUserID(userID));
    }
    @GetMapping(path = "/product/all")
    public ResponseEntity<?> findTotalRevenueOfAllProductByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.statisticsService.findTotalRevenueOfAllProductByUserID(userID));
    }
    @GetMapping(path = "revenue/monthly")
    public ResponseEntity<?> findRevenueGroupByMonth(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.statisticsService.findRevenueGroupByMonth(userID));
    }
    @GetMapping(path = "revenue/weekly")
    public ResponseEntity<?> findRevenueGroupByWeek(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.statisticsService.findRevenueGroupByWeek(userID));
    }
    @GetMapping(path = "revenue/weekday")
    public ResponseEntity<?> findRevenueGroupByWeekday(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.statisticsService.findRevenueGroupByWeekday(userID));
    }
    @GetMapping(path = "revenue/this-month/daily")
    public ResponseEntity<?> findRevenueAllDaysCurrentMonth(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.statisticsService.findRevenueAllDaysCurrentMonth(userID));
    }
}
