package com.higroup.Buda.api.user.password.forgot;

import com.higroup.Buda.customDTO.UserUpdatePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user/password/forgot")
@CrossOrigin("*")
public class ForgotPasswordController {
    private final ForgotPasswordService forgotPasswordService;

    @Autowired
    public ForgotPasswordController(ForgotPasswordService forgotPasswordService) {
        this.forgotPasswordService = forgotPasswordService;
    }

    @GetMapping
    public ResponseEntity<?> forgotPassword(@RequestParam(name = "email") String email) {
        this.forgotPasswordService.forgotPassword(email);
        return ResponseEntity.ok().body("Check your email");
    }

    @PutMapping(path = "/confirm")
    public ResponseEntity<?> updateForgotPassword(@RequestParam(name = "token") String token, @RequestBody UserUpdatePassword userUpdatePassword)
    {
        this.forgotPasswordService.updateForgotPassword(token, userUpdatePassword);
        return ResponseEntity.ok().body("Password updated successfully");
    }
}
