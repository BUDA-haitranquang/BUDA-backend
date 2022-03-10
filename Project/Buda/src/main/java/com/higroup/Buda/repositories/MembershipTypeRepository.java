package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.MembershipType;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipTypeRepository extends JpaRepository<MembershipType, Long>{
    Optional<MembershipType> findMembershipTypeByMembershipTypeID(Long membershipTypeID);
    List<MembershipType> findAllByUserID(Long userID);
}
