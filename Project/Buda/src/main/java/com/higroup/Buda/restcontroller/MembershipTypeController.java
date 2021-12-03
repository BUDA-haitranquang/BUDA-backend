package com.higroup.Buda.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.MembershipType;
import com.higroup.Buda.services.MembershipTypeService;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin("*")
@RequestMapping("api/membership-type")
public class MembershipTypeController {
    private final MembershipTypeService membershipTypeService;
    private final RequestUtil requestUtil;
    @Autowired
    public MembershipTypeController(MembershipTypeService membershipTypeService, RequestUtil requestUtil)
    {
        this.requestUtil = requestUtil;
        this.membershipTypeService = membershipTypeService;
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAllByCurrentUser(HttpServletRequest httpServletRequest)
    {   
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.membershipTypeService.findAllByUserID(userID));
    }
    @GetMapping("membershipTypeID/{membershipTypeID}")
    public ResponseEntity<?> findMembershipTypeByMembershipTypeID(HttpServletRequest httpServletRequest, @PathVariable Long membershipTypeID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        MembershipType membershipType = this.membershipTypeService.findMembershipTypeByMembershipTypeID(membershipTypeID);
        if (membershipType.getUserID()!=userID)
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        return ResponseEntity.ok().body(membershipType);
    }
    @PostMapping("/new")
    public ResponseEntity<?> createNewMembershipType(HttpServletRequest httpServletRequest, @RequestBody MembershipType membershipType)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.membershipTypeService.createNewMembershipType(userID, membershipType));
    }
}
