package com.higroup.Buda.api.business.receipt.view;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/business/receipt/view")
@CrossOrigin("*")
public class ViewReceiptController {
    private final ViewReceiptService viewReceiptService;
    private final RequestUtil requestUtil;
    public ViewReceiptController(ViewReceiptService viewReceiptService, RequestUtil requestUtil){
        this.viewReceiptService = viewReceiptService;
        this.requestUtil = requestUtil;
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAllByCurrentUser(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewReceiptService.findAllReceiptByUserID(userID));
    }
    @GetMapping("/id/{receiptID}")
    public ResponseEntity<?> findReceiptByReceiptID(HttpServletRequest httpServletRequest, @PathVariable Long receiptID){
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewReceiptService.findReceiptByReceiptID(userID, receiptID));
    }
}
