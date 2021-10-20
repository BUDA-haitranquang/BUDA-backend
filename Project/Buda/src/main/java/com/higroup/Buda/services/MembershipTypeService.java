package com.higroup.Buda.services;

import java.util.List;

import com.higroup.Buda.entities.MembershipType;
import com.higroup.Buda.repositories.MembershipTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MembershipTypeService {
    private final MembershipTypeRepository membershipTypeRepository;
    @Autowired
    public MembershipTypeService(MembershipTypeRepository membershipTypeRepository)
    {
        this.membershipTypeRepository = membershipTypeRepository;
    }
    public ResponseEntity<?> findAllByUserID(Long userID)
    {
        return ResponseEntity.ok().body(this.membershipTypeRepository.findAllByUserID(userID));
    }
    public ResponseEntity<?> findMembershipTypeByMembershipTypeID(Long membershipTypeID)
    {
        return ResponseEntity.ok().body(this.membershipTypeRepository.findMembershipTypeByMembershipTypeID(membershipTypeID));
    }
}
