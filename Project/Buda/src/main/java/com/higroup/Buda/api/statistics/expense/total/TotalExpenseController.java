package com.higroup.Buda.api.statistics.expense.total;

import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/statistics/expense/total")
@CrossOrigin("*")
public class TotalExpenseController {
    private final TotalExpenseService totalExpenseService;
    private final RequestUtil requestUtil;
    @Autowired
    public TotalExpenseController(TotalExpenseService totalExpenseService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.totalExpenseService = totalExpenseService;
    }
    @GetMapping("/all/day")
    public ResponseEntity<?> findTotalExpenseEveryDay(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.totalExpenseService.findTotalExpenseEveryDay(userID));
    }
    @GetMapping("/all/week")
    public ResponseEntity<?> findTotalExpenseEveryWeek(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.totalExpenseService.findTotalExpenseEveryWeek(userID));
    }
    @GetMapping("/all/month")
    public ResponseEntity<?> findTotalExpenseEveryMonth(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.totalExpenseService.findTotalExpenseEveryMonth(userID));
    }
    @GetMapping("/all/year")
    public ResponseEntity<?> findTotalExpenseEveryYear(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.totalExpenseService.findTotalExpenseEveryYear(userID));
    }
    @GetMapping("/last-x-days/{X}")
    public ResponseEntity<?> findTotalExpenseLastXDays(HttpServletRequest httpServletRequest, @PathVariable Long X){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.totalExpenseService.findTotalExpenseLastXDays(userID, X));
    }
}
