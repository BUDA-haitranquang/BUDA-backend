package com.higroup.Buda.api.statistics.overall;

import com.higroup.Buda.customDTO.PeriodDTO;
import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/statistics/overall/total")
@CrossOrigin("*")
public class BusinessOverallController {
    private BusinessOverallService businessOverallService;
    private RequestUtil requestUtil;
    @Autowired
    public BusinessOverallController(BusinessOverallService businessOverallService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.businessOverallService = businessOverallService;
    }
    @GetMapping("/all/day")
    public ResponseEntity<?> findTotalProfitEveryDay(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.businessOverallService.findTotalProfitEveryDay(userID));
    }
    @GetMapping("/all/week")
    public ResponseEntity<?> findTotalProfitEveryWeek(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.businessOverallService.findTotalProfitEveryWeek(userID));
    }
    @GetMapping("/all/month")
    public ResponseEntity<?> findTotalProfitEveryMonth(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.businessOverallService.findTotalProfitEveryMonth(userID));
    }
    @GetMapping("/all/year")
    public ResponseEntity<?> findTotalProfitEveryYear(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.businessOverallService.findTotalProfitEveryYear(userID));
    }
    @GetMapping("/last-x-days/{X}")
    public ResponseEntity<?> findTotalProfitLastXDays(HttpServletRequest httpServletRequest, @PathVariable Long X){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.businessOverallService.findTotalProfitLastXDays(userID, X));
    }
    @GetMapping("/all/period")
    public ResponseEntity<?> findTotalProfitPeriod(HttpServletRequest httpServletRequest, @RequestBody PeriodDTO periodDTO)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.businessOverallService.findTotalProfitPeriod(userID, periodDTO));
    }
}
