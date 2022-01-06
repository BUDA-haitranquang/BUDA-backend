package com.higroup.Buda.api.statistics.gender;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/statistics/gender")
@CrossOrigin("*")
public class GenderStatisticsController {
    private final RequestUtil requestUtil;
    private final GenderStatisticsService genderStatisticsService;
    @Autowired
    public GenderStatisticsController(RequestUtil requestUtil, GenderStatisticsService genderStatisticsService)
    {
        this.requestUtil = requestUtil;
        this.genderStatisticsService = genderStatisticsService;
    }
    @GetMapping(path = "/total")
    public ResponseEntity<?> findTotalSpendOfGenderByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.genderStatisticsService.findTotalSpendOfGenderByUserID(userID));
    }
    @GetMapping(path = "/this-month")
    public ResponseEntity<?> findCurrentMonthSpendOfGenderByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.genderStatisticsService.findCurrentMonthSpendOfGenderByUserID(userID));
    }
}
