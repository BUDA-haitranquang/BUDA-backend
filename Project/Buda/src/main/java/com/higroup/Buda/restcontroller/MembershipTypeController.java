package com.higroup.Buda.restcontroller;

import java.util.List;

import com.higroup.Buda.entities.MembershipType;
import com.higroup.Buda.services.MembershipTypeService;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    public MembershipTypeController(MembershipTypeService membershipTypeService)
    {
        this.membershipTypeService = membershipTypeService;
    }
    @GetMapping("userID/{userID}/all")
    public List<MembershipType> findAllByUserID(@PathVariable Long userID)
    {
        return this.membershipTypeService.findAllByUserID(userID);
    }
}
