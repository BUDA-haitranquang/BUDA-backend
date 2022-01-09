package com.higroup.Buda.api.user.login;

import javax.validation.Valid;

import com.higroup.Buda.customDTO.UserLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/login")
@CrossOrigin("*")
public class UserLoginController {
    private final UserLoginService userLoginService;
    @Autowired
    public UserLoginController(UserLoginService userLoginService)
    {
        this.userLoginService = userLoginService;
    }
    @PostMapping
    public ResponseEntity<?> correctLogin(@RequestBody @Valid UserLogin userLogin) {
        return ResponseEntity.ok(userLoginService.correctLogin(userLogin));
    }
}
