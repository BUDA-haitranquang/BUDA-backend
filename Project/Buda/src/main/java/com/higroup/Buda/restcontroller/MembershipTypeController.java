package com.higroup.Buda.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.MembershipType;
import com.higroup.Buda.services.MembershipTypeService;
import com.higroup.Buda.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/membership-type")
public class MembershipTypeController {
    private final MembershipTypeService membershipTypeService;
    private final JwtTokenUtil jwtTokenUtil;
    @Autowired
    public MembershipTypeController(MembershipTypeService membershipTypeService, JwtTokenUtil jwtTokenUtil)
    {
        this.jwtTokenUtil = jwtTokenUtil;
        this.membershipTypeService = membershipTypeService;
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAllByCurrentUser(HttpServletRequest httpServletRequest)
    {
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        
        Long userID = jwtTokenUtil.getUserIDFromToken(token);
        if ((userID != null) && (jwtTokenUtil.isValid(token)))
        {
            return this.membershipTypeService.findAllByUserID(userID);
        }
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");

    }
}
