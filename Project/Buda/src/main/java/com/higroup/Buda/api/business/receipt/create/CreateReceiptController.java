package com.higroup.Buda.api.business.receipt.create;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Receipt;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/business/receipt/create")
@CrossOrigin("*")
public class CreateReceiptController {
    private final CreateReceiptService createReceiptService;
    private final RequestUtil requestUtil;
    @Autowired
    public CreateReceiptController(CreateReceiptService createReceiptService, RequestUtil requestUtil){
        this.createReceiptService = createReceiptService;
        this.requestUtil = requestUtil;
    }
    @PostMapping
    public ResponseEntity<?> createNewReceipt(HttpServletRequest httpServletRequest, @RequestBody Receipt receipt){
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.createReceiptService.createNewReceipt(userID, receipt));
    }
}
