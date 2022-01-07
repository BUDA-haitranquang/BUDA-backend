package com.higroup.Buda.api.user.view;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/view")
@CrossOrigin("*")
public class UserViewController {
    private final RequestUtil requestUtil;
    private final UserViewService userViewService;
    @Autowired
    public UserViewController(RequestUtil requestUtil, UserViewService userViewService){
        this.requestUtil = requestUtil;
        this.userViewService = userViewService;
    }
    @GetMapping("/me")
    public ResponseEntity<?> viewMyProfile(HttpServletRequest httpServletRequest)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.userViewService.findUserByUserID(userID));
    }
}
