package com.higroup.Buda.api.statistics.customer.agegroup;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/statistics/customer/age-group")
public class AgeGroupStatisticsController {
    private final RequestUtil requestUtil;
    private final AgeGroupStatisticsService ageGroupStatisticsService;
    @Autowired
    public AgeGroupStatisticsController(RequestUtil requestUtil, AgeGroupStatisticsService ageGroupStatisticsService)
    {
        this.requestUtil = requestUtil;
        this.ageGroupStatisticsService = ageGroupStatisticsService;
    }
    @GetMapping(path = "/total")
    public ResponseEntity<?> findTotalSpendOfAgeGroupByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getPremiumUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ageGroupStatisticsService.findTotalSpendOfAgeGroupByUserID(userID));
    }
    @GetMapping(path = "/this-month")
    public ResponseEntity<?> findCurrentMonthSpendOfAgeGroupByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getPremiumUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.ageGroupStatisticsService.findCurrentMonthSpendOfAgeGroupByUserID(userID));
    }
}
