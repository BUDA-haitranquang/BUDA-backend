package com.higroup.Buda.api.user.register;

import javax.validation.Valid;

import com.higroup.Buda.customDTO.UserRegister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/register")
@CrossOrigin("*")
public class UserRegisterController {
    private final UserRegisterService userRegisterService;
    @Autowired
    public UserRegisterController(UserRegisterService userRegisterService)
    {
        this.userRegisterService = userRegisterService;
    }
    @PostMapping
    public ResponseEntity<?> registerNewUser(@RequestBody @Valid UserRegister userRegister)
    {
        this.userRegisterService.registerNewUser(userRegister);
        return ResponseEntity.ok().body("Please confirm your email");
    }
}
