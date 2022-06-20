package com.higroup.Buda.api.business.sell.print;

import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("api/business/sell/print")

public class PrintSellOrderController {
    private final PrintSellOrderService printSellOrderService;
    private final RequestUtil requestUtil;

    @Autowired
    public PrintSellOrderController(PrintSellOrderService printSellOrderService, RequestUtil requestUtil) {
        this.printSellOrderService = printSellOrderService;
        this.requestUtil = requestUtil;
    }
    @PostMapping
    public ResponseEntity<?> printSellOrder(HttpServletRequest httpServletRequest, @RequestBody InputPrintSellOrderDTO inputPrintSellOrderDTO) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.printSellOrderService.printSellOrder(userID, inputPrintSellOrderDTO));
    }
}