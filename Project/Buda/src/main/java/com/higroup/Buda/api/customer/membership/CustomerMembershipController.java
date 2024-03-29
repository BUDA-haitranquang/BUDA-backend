package com.higroup.Buda.api.customer.membership;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.MembershipType;
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
@RequestMapping("api/customer/membership")
public class CustomerMembershipController {
    private final CustomerMembershipService customerMembershipService;
    private final RequestUtil requestUtil;
    @Autowired
    public CustomerMembershipController(CustomerMembershipService membershipService, RequestUtil requestUtil) {
        this.customerMembershipService = membershipService;
        this.requestUtil = requestUtil;
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAllByCurrentUser(HttpServletRequest httpServletRequest)
    {   
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.customerMembershipService.findAllByUserID(userID));
    }
    @GetMapping("membershipTypeID/{membershipTypeID}")
    public ResponseEntity<?> findMembershipTypeByMembershipTypeID(HttpServletRequest httpServletRequest, @PathVariable Long membershipTypeID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        Optional<MembershipType> membershipType = this.customerMembershipService.findMembershipTypeByMembershipTypeID(membershipTypeID);
        if (membershipType.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
        }
        if (!membershipType.get().getUserID().equals(userID))
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        return ResponseEntity.ok().body(membershipType);
    }
    @PostMapping("/new")
    public ResponseEntity<?> createNewMembershipType(HttpServletRequest httpServletRequest, @RequestBody MembershipType membershipType)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        return ResponseEntity.ok().body(this.customerMembershipService.createNewMembershipType(userID, membershipType));
    }
    @PostMapping("/update/{membershipTypeID}")
    public ResponseEntity<?> updateMembershipType(HttpServletRequest httpServletRequest, @PathVariable Long membershipTypeID)
    {
        Long userID = this.requestUtil.getUserIDFromUserToken(httpServletRequest);
        Optional<MembershipType> membershipType = this.customerMembershipService.findMembershipTypeByMembershipTypeID(membershipTypeID);
        if (membershipType.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
        }
        if (!membershipType.get().getUserID().equals(userID))
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        return ResponseEntity.ok().body(this.customerMembershipService.updateMembershipType(userID, membershipTypeID));
    }

}
