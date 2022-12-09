package com.higroup.Buda.api.statistics.product.overall;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/statistics/product/overall")
@CrossOrigin("*")
public class ProductStatisticsController {
    private final RequestUtil requestUtil;
    private final ProductStatisticsService productStatisticsService;
    @Autowired
    public ProductStatisticsController(RequestUtil requestUtil, ProductStatisticsService productStatisticsService)
    {
        this.requestUtil = requestUtil;
        this.productStatisticsService = productStatisticsService;
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> findTotalRevenueOfAllProductByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.productStatisticsService.findTotalRevenueOfAllProductByUserID(userID));
    }
}
