package com.higroup.Buda.api.user.refreshtoken;

import com.higroup.Buda.security.jwt.JwtSimple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/refresh-token")
@CrossOrigin("*")
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;
    @Autowired
    public RefreshTokenController(RefreshTokenService refreshTokenService)
    {
        this.refreshTokenService = refreshTokenService;
    }
    @PostMapping
    public ResponseEntity<?> generateNewAccessToken(@RequestBody JwtSimple jwtSimple)
    {
        return ResponseEntity.ok().body(this.refreshTokenService.generateNewToken(jwtSimple.getToken()));
    }
}
