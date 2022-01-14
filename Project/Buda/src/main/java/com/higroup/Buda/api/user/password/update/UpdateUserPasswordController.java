package com.higroup.Buda.api.user.password.update;

import com.higroup.Buda.api.user.login.UserLoginService;
import com.higroup.Buda.customDTO.UserLogin;
import com.higroup.Buda.customDTO.UserUpdatePassword;
import com.higroup.Buda.util.Checker.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
