package com.higroup.Buda.api.statistics.customer.repeat;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/statistics/customer/repeat")
@CrossOrigin("*")
public class RepeatCustomerRateStatsController {
    private final RepeatCustomerRateStatsService repeatCustomerRateStatsService;
    private final RequestUtil requestUtil;
    @Autowired
    public RepeatCustomerRateStatsController(RepeatCustomerRateStatsService repeatCustomerRateStatsService,
    RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.repeatCustomerRateStatsService = repeatCustomerRateStatsService;
    }
    @GetMapping("/weekly")
    public ResponseEntity<?> findRepeatRateGroupByWeek(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getPremiumUserIDFromUserToken(httpServletRequest);
        return null;
    }
    @GetMapping("/monthly")
    public ResponseEntity<?> findRepeatRateGroupByMonth(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getPremiumUserIDFromUserToken(httpServletRequest);
        return null;
    }
}
