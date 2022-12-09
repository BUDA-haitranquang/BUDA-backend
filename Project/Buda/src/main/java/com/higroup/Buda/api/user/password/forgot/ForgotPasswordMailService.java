package com.higroup.Buda.api.user.password.forgot;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.MailConfirmationToken;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.MailConfirmationTokenRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.services.EmailService;
import com.higroup.Buda.util.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ForgotPasswordMailService {
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final MailConfirmationTokenRepository mailConfirmationTokenRepository;
    @Autowired
    public ForgotPasswordMailService(EmailService emailService, UserRepository userRepository,
    MailConfirmationTokenRepository mailConfirmationTokenRepository){
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.mailConfirmationTokenRepository = mailConfirmationTokenRepository;
    }
    @Transactional
    public void sendMailForgotPassword(String email) {
        User user = userRepository
                .findUserByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        MailConfirmationToken confirmationToken = this.buildConfirmationTokenFor(user);

        String confirmUrl = Config.url + "/api/user/password/forgot/confirm?token=" + confirmationToken.getToken();
        emailService.send(email, "Forgot password", this.buildAccountConfirmationEmail(confirmUrl));
        this.mailConfirmationTokenRepository.save(confirmationToken);
    }
    private String buildAccountConfirmationEmail(String link)
    {
        return link;
    }
    private MailConfirmationToken buildConfirmationTokenFor(User user) {
        String confirmEmailToken = UUID.randomUUID().toString();
        return new MailConfirmationToken(
            confirmEmailToken,
            LocalDateTime.now(), LocalDateTime.now().plusMinutes(15),
            user
        );
    }
    public MailConfirmationToken getToken(String token) {
        return mailConfirmationTokenRepository
            .findByToken(token)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Confirmation token not found"));
    }

    @Transactional
    public void setConfirmedAt(String token) {
        mailConfirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
