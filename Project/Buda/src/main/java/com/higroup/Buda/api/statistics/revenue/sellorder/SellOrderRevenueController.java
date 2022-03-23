package com.higroup.Buda.api.statistics.revenue.sellorder;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.customDTO.PeriodDTO;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics/revenue/sell-order") 
@CrossOrigin("*")
public class SellOrderRevenueController {
    private final RequestUtil requestUtil;
    private final SellOrderRevenueService sellOrderRevenueService;
    @Autowired
    public SellOrderRevenueController(RequestUtil requestUtil, SellOrderRevenueService revenueStatisticsService)
    {
        this.requestUtil = requestUtil;
        this.sellOrderRevenueService = revenueStatisticsService;
    }
    @GetMapping(path = "/monthly")
    public ResponseEntity<?> findRevenueGroupByMonth(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.sellOrderRevenueService.findRevenueGroupByMonth(userID));
    }
    @GetMapping(path = "/weekly")
    public ResponseEntity<?> findRevenueGroupByWeek(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.sellOrderRevenueService.findRevenueGroupByWeek(userID));
    }
    @GetMapping(path = "/weekday")
    public ResponseEntity<?> findRevenueGroupByWeekday(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.sellOrderRevenueService.findRevenueGroupByWeekday(userID));
    }
    @GetMapping(path = "/this-month/daily")
    public ResponseEntity<?> findRevenueAllDaysCurrentMonth(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.sellOrderRevenueService.findRevenueAllDaysCurrentMonth(userID));
    }
    @PostMapping(path = "/period")
    public ResponseEntity<?> findRevenueInPeriod(HttpServletRequest httpServletRequest, @RequestBody PeriodDTO periodDTO){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.sellOrderRevenueService.findRevenueByPeriod(userID, periodDTO));
    }
}
