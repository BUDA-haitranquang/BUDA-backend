package com.higroup.Buda.repositories;

import com.higroup.Buda.entities.MailConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MailConfirmationTokenRepository extends JpaRepository<MailConfirmationToken, Long> {
    Optional<MailConfirmationToken> findByToken(String token);

    @Modifying
    @Query(
        value = "UPDATE mail_confirmation_token " +
            "SET confirmed_at = :confirmed_at WHERE token LIKE :token",
        nativeQuery = true
    )
    void updateConfirmedAt(@Param("token") String token, @Param("confirmed_at") LocalDateTime confirmedAt);
}