package com.higroup.Buda.api.business.receipt.delete;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/business/receipt/delete")
@CrossOrigin("*")
public class DeleteReceiptController {
    private final DeleteReceiptService deleteReceiptService;
    private final RequestUtil requestUtil;
    @Autowired
    public DeleteReceiptController(DeleteReceiptService deleteReceiptService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.deleteReceiptService = deleteReceiptService;
    }
    @DeleteMapping(path = "/id/{receiptID}")
    public ResponseEntity<?> deleteReceiptByReceiptID(HttpServletRequest httpServletRequest, @PathVariable Long receiptID){
        Long userID = this.requestUtil.getProUserIDFromUserToken(httpServletRequest);
        this.deleteReceiptService.deleteReceiptByReceiptID(userID, receiptID);
        return ResponseEntity.ok().body("Delete successfully");
    }
}
