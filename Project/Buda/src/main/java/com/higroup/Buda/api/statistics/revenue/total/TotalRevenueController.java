package com.higroup.Buda.api.statistics.revenue.total;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/all")
    public ResponseEntity<?> findTotalRevenueEveryDay(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.totalRevenueService.findTotalRevenueEveryDay(userID));
    }
    @GetMapping("/last-x-days/{X}")
    public ResponseEntity<?> findTotalRevenueLastXDays(HttpServletRequest httpServletRequest, @PathVariable Long X){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.totalRevenueService.findTotalRevenueLastXDays(userID, X));
    }
}
