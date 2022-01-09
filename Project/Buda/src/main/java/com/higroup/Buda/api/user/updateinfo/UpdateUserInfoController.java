package com.higroup.Buda.api.user.updateinfo;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.User;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/update-info")
@CrossOrigin("*")
public class UpdateUserInfoController {
    private final RequestUtil requestUtil;
    private final UpdateUserInfoService updateUserInfoService;
    @Autowired
    public UpdateUserInfoController(RequestUtil requestUtil, UpdateUserInfoService updateUserInfoService)
    {
        this.requestUtil = requestUtil;
        this.updateUserInfoService = updateUserInfoService;
    }
    @PutMapping
    public ResponseEntity<?> updateUserInfo(HttpServletRequest httpServletRequest, @RequestBody User user) 
    throws IllegalAccessException, InvocationTargetException
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.updateUserInfoService.updateUser(userID, user));
    }
}
