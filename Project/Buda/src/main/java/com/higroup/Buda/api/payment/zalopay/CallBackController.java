package com.higroup.Buda.api.payment.zalopay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/payment/zalopay")
public class CallBackController {

    @Autowired
    private CallBackService callBackService;

    @PostMapping("/callback")
    public String callback(@RequestBody String jsonStr) {
        return callBackService.callBack(jsonStr);
    }
}
