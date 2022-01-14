package com.higroup.Buda.api.user.updateinfo.email.confirm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/update-info/email/confirm")
@CrossOrigin("*")
public class ConfirmUpdateEmailController {
    private final ConfirmUpdateEmailService confirmUpdateEmailService;
    @Autowired
    public ConfirmUpdateEmailController(ConfirmUpdateEmailService confirmUpdateEmailService){
        this.confirmUpdateEmailService = confirmUpdateEmailService;
    }
    @GetMapping
    public ResponseEntity<?> confirmUpdateEmail(@RequestParam(name = "token") String token){
        return ResponseEntity.ok().body(this.confirmUpdateEmailService.confirmUpdateEmailToken(token));
    }
}
