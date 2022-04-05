package com.higroup.Buda.api.statistics.revenue.total;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.customDTO.PeriodDTO;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/statistics/revenue/total")
@CrossOrigin("*")
public class TotalRevenueController {
    private TotalRevenueService totalRevenueService;
    private RequestUtil requestUtil;
    @Autowired
    public TotalRevenueController(TotalRevenueService totalRevenueService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.totalRevenueService = totalRevenueService;
    }
    @GetMapping("/all/day")
    public ResponseEntity<?> findTotalRevenueEveryDay(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.totalRevenueService.findTotalRevenueEveryDay(userID));
    }
    @GetMapping("/all/week")
    public ResponseEntity<?> findTotalRevenueEveryWeek(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.totalRevenueService.findTotalRevenueEveryWeek(userID));
    }
    @GetMapping("/all/month")
    public ResponseEntity<?> findTotalRevenueEveryMonth(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.totalRevenueService.findTotalRevenueEveryMonth(userID));
    }
    @GetMapping("/all/year")
    public ResponseEntity<?> findTotalRevenueEveryYear(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.totalRevenueService.findTotalRevenueEveryYear(userID));
    }
    @GetMapping("/last-x-days/{X}")
    public ResponseEntity<?> findTotalRevenueLastXDays(HttpServletRequest httpServletRequest, @PathVariable Long X){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.totalRevenueService.findTotalRevenueLastXDays(userID, X));
    }
    @GetMapping("/all/period")
    public ResponseEntity<?> findTotalRevenuePeriod(HttpServletRequest httpServletRequest, @RequestBody PeriodDTO periodDTO)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.totalRevenueService.findTotalRevenuePeriod(userID, periodDTO));
    }
}
