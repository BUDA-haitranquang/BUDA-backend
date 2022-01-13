package com.higroup.Buda.api.staff.login;

import javax.validation.Valid;

import com.higroup.Buda.customDTO.StaffLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/staff/login")
@CrossOrigin("*")
public class StaffLoginController {
    private final StaffLoginService staffLoginService;
    @Autowired
    public StaffLoginController(StaffLoginService staffLoginService){
        this.staffLoginService = staffLoginService;
    }
    @PostMapping
    public ResponseEntity<?> correctLogin(@RequestBody @Valid StaffLogin staffLogin)
    {
        String account = staffLogin.getAccount();
        String password = staffLogin.getPassword();
        return ResponseEntity.ok(this.staffLoginService.correctLogin(account, password));
    }
}
