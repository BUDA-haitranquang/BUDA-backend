package com.higroup.Buda.api.customer.view;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customer/view")
@CrossOrigin("*")
public class ViewCustomerController {
    private final ViewCustomerService viewCustomerService;
    private final RequestUtil requestUtil;
    @Autowired
    public ViewCustomerController(ViewCustomerService viewCustomerService, RequestUtil requestUtil){
        this.viewCustomerService = viewCustomerService;
        this.requestUtil = requestUtil;
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAllByCurrentUser(HttpServletRequest httpServletRequest){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.viewCustomerService.findAllByUserID(userID));
    }
}
