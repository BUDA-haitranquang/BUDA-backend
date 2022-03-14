package com.higroup.Buda.repositories;

import java.util.Optional;

import com.higroup.Buda.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findUserByUserID(Long userID);
    @Query("select u from User u left join fetch u.roles where u.email = :email")
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUserUUID(String userUUID);
    Optional<User> findUserByPhoneNumber(String phoneNumber);
    Optional<User> findUserByUserName(String userName);

    @Modifying
    @Query(
        value = "UPDATE user SET enabled = TRUE WHERE email LIKE :email",
        nativeQuery = true
    )
    void enableUserByEmail(@Param("email") String email);
}
