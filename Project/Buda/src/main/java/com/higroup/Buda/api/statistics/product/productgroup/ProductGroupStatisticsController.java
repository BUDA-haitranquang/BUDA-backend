package com.higroup.Buda.api.statistics.product.productgroup;

import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/statistics/product/product-group")
public class ProductGroupStatisticsController {
    private final ProductGroupStatisticsService productGroupStatisticsService;
    private final RequestUtil requestUtil;
    @Autowired

    public ProductGroupStatisticsController(ProductGroupStatisticsService productGroupStatisticsService, RequestUtil requestUtil) {
        this.productGroupStatisticsService = productGroupStatisticsService;
        this.requestUtil = requestUtil;
    }
    @GetMapping("/total/{productGroupID}")
    public ResponseEntity<?> findTotalRevenueFromProductGroup(HttpServletRequest httpServletRequest, @PathVariable Long productGroupID) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productGroupStatisticsService.findTotalRevenueFromProductGroup(userID, productGroupID));
    }
}
