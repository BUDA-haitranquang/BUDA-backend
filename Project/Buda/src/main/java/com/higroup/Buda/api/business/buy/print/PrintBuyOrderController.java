package com.higroup.Buda.api.business.buy.print;

import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("api/business/buy/print")

public class PrintBuyOrderController {
    private final PrintBuyOrderService printBuyOrderService;
    private final RequestUtil requestUtil;

    @Autowired
    public PrintBuyOrderController(PrintBuyOrderService printBuyOrderService, RequestUtil requestUtil) {
        this.printBuyOrderService = printBuyOrderService;
        this.requestUtil = requestUtil;
    }
    @GetMapping
    public ResponseEntity<?> printBuyOrder(HttpServletRequest httpServletRequest, @RequestBody InputPrintBuyOrderDTO inputPrintBuyOrderDTO) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.printBuyOrderService.printBuyOrder(userID, inputPrintBuyOrderDTO));
    }
}