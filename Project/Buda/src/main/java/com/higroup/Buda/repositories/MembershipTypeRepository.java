package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.MembershipType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface MembershipTypeRepository extends JpaRepository<MembershipType, Long>{
    Optional<MembershipType> findMembershipTypeByMembershipTypeID(@Param("membershipTypeID") Long membershipTypeID);
    List<MembershipType> findAllByUserID(@Param("userID") Long userID);
}
