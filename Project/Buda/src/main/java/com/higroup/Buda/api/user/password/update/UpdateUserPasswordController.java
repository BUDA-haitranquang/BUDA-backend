package com.higroup.Buda.api.user.password.update;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.customDTO.UserUpdatePassword;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/password/update")
@CrossOrigin("*")
public class UpdateUserPasswordController {
    private final UpdateUserPasswordService updateUserPasswordService;
    private final RequestUtil requestUtil;
    @Autowired
    public UpdateUserPasswordController(UpdateUserPasswordService updateUserPasswordService, RequestUtil requestUtil) {
        this.updateUserPasswordService = updateUserPasswordService;
        this.requestUtil = requestUtil;
    }
    @PutMapping
    public ResponseEntity<?> updateUserPassword(HttpServletRequest httpServletRequest, @RequestBody UserUpdatePassword userUpdatePassword) {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        this.updateUserPasswordService.updateUserPassword(userID, userUpdatePassword);
        return ResponseEntity.ok().body("Password updated successfully");
    }
}
