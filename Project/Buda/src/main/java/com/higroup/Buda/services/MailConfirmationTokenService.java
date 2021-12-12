package com.higroup.Buda.services;

import com.higroup.Buda.entities.MailConfirmationToken;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.MailConfirmationTokenRepository;
import com.higroup.Buda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MailConfirmationTokenService {
    private final MailConfirmationTokenRepository mailConfirmationTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    public MailConfirmationTokenService(MailConfirmationTokenRepository mailConfirmationTokenRepository) {
        this.mailConfirmationTokenRepository = mailConfirmationTokenRepository;
    }

    @Transactional
    public void sendMailConfirmationTo(String email) {
        User user = userRepository
            .findUserByEmail(email)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        MailConfirmationToken confirmationToken = this.buildConfirmationTokenFor(user);

        String confirmUrl = "http://localhost:8080/api/user/register/confirm?token=" + confirmationToken.getToken();
        emailService.send(
            email,
            "Please activate your BUDA account",
            emailService.buildAccountActivationEmail(email, confirmUrl)
        );

        this.save(confirmationToken);
    }

    @Transactional
    public void save(MailConfirmationToken token) {
        this.mailConfirmationTokenRepository.save(token);
    }

    public MailConfirmationToken getToken(String token) {
        return mailConfirmationTokenRepository
            .findByToken(token)
            .orElseThrow(() -> new IllegalStateException("Confirmation token not found"));
    }

    @Transactional
    public void setConfirmedAt(String token) {
        mailConfirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    private MailConfirmationToken buildConfirmationTokenFor(User user) {
        String confirmEmailToken = UUID.randomUUID().toString();
        return new MailConfirmationToken(
            confirmEmailToken,
            LocalDateTime.now(), LocalDateTime.now().plusMinutes(15),
            user
        );
    }
}
