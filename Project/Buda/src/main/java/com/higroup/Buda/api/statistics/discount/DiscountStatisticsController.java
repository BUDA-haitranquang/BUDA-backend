package com.higroup.Buda.api.statistics.discount;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/statistics/discount")
public class DiscountStatisticsController {
    private final DiscountStatisticsService discountStatisticsService;
    private final RequestUtil requestUtil;
    @Autowired
    public DiscountStatisticsController(DiscountStatisticsService discountStatisticsService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.discountStatisticsService = discountStatisticsService;
    }
    @GetMapping(path = "total-revenue/id/{discountID}")
    public ResponseEntity<?> findTotalRevenueByDiscount(HttpServletRequest httpServletRequest, @PathVariable Long discountID){
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.discountStatisticsService.totalRevenueByDiscountID(userID, discountID));
    }
}
