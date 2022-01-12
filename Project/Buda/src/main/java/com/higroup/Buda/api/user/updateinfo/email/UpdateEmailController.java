package com.higroup.Buda.api.user.updateinfo.email;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/update-info/email")
@CrossOrigin("*")
public class UpdateEmailController {
    private final UpdateEmailService updateEmailService;
    private final RequestUtil requestUtil;
    @Autowired
    public UpdateEmailController(UpdateEmailService updateEmailService, RequestUtil requestUtil){
        this.requestUtil = requestUtil;
        this.updateEmailService = updateEmailService;
    }
    @PutMapping
    public ResponseEntity<?> updateEmail(HttpServletRequest httpServletRequest, 
    @RequestBody @Valid SimpleEmailDTO simpleEmailDTO){
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.updateEmailService.updateEmail(userID, simpleEmailDTO);
        return ResponseEntity.ok().body("Email sent");
    }
}
