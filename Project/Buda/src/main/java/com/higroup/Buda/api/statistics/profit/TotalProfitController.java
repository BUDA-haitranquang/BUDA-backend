package com.higroup.Buda.api.statistics.profit;

import com.higroup.Buda.customDTO.PeriodDTO;
import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/statistics/profit/total")
@CrossOrigin("*")
public class TotalProfitController {
    private TotalProfitService totalProfitService;
    private RequestUtil requestUtil;
    @Autowired
    public TotalProfitController(TotalProfitService totalProfitService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.totalProfitService = totalProfitService;
    }
    @GetMapping("/all/day")
    public ResponseEntity<?> findTotalProfitEveryDay(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.totalProfitService.findTotalProfitEveryDay(userID));
    }
    @GetMapping("/all/week")
    public ResponseEntity<?> findTotalProfitEveryWeek(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.totalProfitService.findTotalProfitEveryWeek(userID));
    }
    @GetMapping("/all/month")
    public ResponseEntity<?> findTotalProfitEveryMonth(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.totalProfitService.findTotalProfitEveryMonth(userID));
    }
    @GetMapping("/all/year")
    public ResponseEntity<?> findTotalProfitEveryYear(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.totalProfitService.findTotalProfitEveryYear(userID));
    }
    @GetMapping("/last-x-days/{X}")
    public ResponseEntity<?> findTotalProfitLastXDays(HttpServletRequest httpServletRequest, @PathVariable Long X){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.totalProfitService.findTotalProfitLastXDays(userID, X));
    }
    @GetMapping("/all/period")
    public ResponseEntity<?> findTotalProfitPeriod(HttpServletRequest httpServletRequest, @RequestBody PeriodDTO periodDTO)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.totalProfitService.findTotalProfitPeriod(userID, periodDTO));
    }
}
