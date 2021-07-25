package com.higroup.Buda.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "nho sua cai nay")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }
    //Lam du 4 request: CREATE - READ - UPDATE - DELETE
    //BEN NAY CAC FUNCTION CHI CO MOT DONG DUY NHAT
    //return this.userService.get(tham so)/update(tham so)/...
}
