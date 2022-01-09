package com.higroup.Buda.api.user.register.confirm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/register/confirm")
@CrossOrigin("*")
public class ConfirmAccountActivationController {
    private final ConfirmAccountActivationService confirmAccountActivationService;
    @Autowired
    public ConfirmAccountActivationController(ConfirmAccountActivationService confirmAccountActivationService)
    {
        this.confirmAccountActivationService = confirmAccountActivationService;
    }
    @GetMapping
    public ResponseEntity<?> confirmActivationAccount(@RequestParam(name = "token") String token)
    {
        return ResponseEntity.ok().body(this.confirmAccountActivationService.confirmAccountActivation(token));
    }
}
