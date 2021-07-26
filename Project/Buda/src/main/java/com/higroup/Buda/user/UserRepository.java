package com.higroup.Buda.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findUserByUserID(Long userID);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUserUUID(String userUUID);
    Optional<User> findUserByPhoneNumber(String phoneNumber);
}
