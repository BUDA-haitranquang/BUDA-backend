package com.higroup.Buda.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.FixedCostBill;
import com.higroup.Buda.services.FixedCostBillService;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/fixed-cost-bill")
@CrossOrigin("*")
public class FixedCostBillController {
    private final FixedCostBillService fixedCostBillService;
    private final RequestUtil requestUtil;
    @Autowired
    public FixedCostBillController(FixedCostBillService fixedCostBillService, RequestUtil requestUtil)
    {
        this.fixedCostBillService = fixedCostBillService;
        this.requestUtil = requestUtil;
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAllByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.fixedCostBillService.findAllByUserID(userID));
    }
    @GetMapping("fixedCostID/{fixedCostID}/all")
    public ResponseEntity<?> findAllByFixedCostID(HttpServletRequest httpServletRequest, @PathVariable Long fixedCostID)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(fixedCostBillService.findAllByFixedCostID(userID, fixedCostID));
    }
    @GetMapping("all/last-x-days/{X}")
    public ResponseEntity<?> findAllFixedCostBillByCurrentUserLastXDays(HttpServletRequest httpServletRequest, @PathVariable Long X)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.fixedCostBillService.findAllFixedCostBillByUserIDLastXDays(userID, X));
    }
    @GetMapping("/all/incompleted")
    public ResponseEntity<?> findAllIncompletedFixedCostBillByCurrentUser(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.fixedCostBillService.findAllIncompletedFixedCostBillByUserID(userID));
    }
    @PostMapping("/new")
    public ResponseEntity<?> createNewFixedCostBill(HttpServletRequest httpServletRequest, @RequestBody FixedCostBill fixedCostBill)
    {
        Long userID = this.requestUtil.getUserID(httpServletRequest);
        return ResponseEntity.ok().body(this.fixedCostBillService.createNewFixedCostBill(userID, fixedCostBill));
    }

}
