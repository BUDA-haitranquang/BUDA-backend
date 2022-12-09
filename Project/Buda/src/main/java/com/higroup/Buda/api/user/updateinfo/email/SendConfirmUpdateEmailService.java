package com.higroup.Buda.api.user.updateinfo.email;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.MailConfirmationToken;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.entities.enumeration.MailTokenType;
import com.higroup.Buda.repositories.MailConfirmationTokenRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.services.EmailService;
import com.higroup.Buda.util.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SendConfirmUpdateEmailService {
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final MailConfirmationTokenRepository mailConfirmationTokenRepository;
    @Autowired
    public SendConfirmUpdateEmailService(EmailService emailService, UserRepository userRepository,
    MailConfirmationTokenRepository mailConfirmationTokenRepository){
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.mailConfirmationTokenRepository = mailConfirmationTokenRepository;
    }
    @Transactional
    public void sendConfirmationUpdateEmail(String oldEmail, String newEmail){
        User user = userRepository.findUserByEmail(oldEmail)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        MailConfirmationToken mailConfirmationToken = this.buildConfirmationTokenFor(user);
        mailConfirmationToken.setMailTokenType(MailTokenType.UPDATE_INFO);
        mailConfirmationToken.setTargetEmail(newEmail); 
        String confirmUrl = Config.url + "/api/user/update-info/email/confirm?token=" + 
        mailConfirmationToken.getToken();
        //TO DO: send email
        this.emailService.send
        (oldEmail, 
        "Please confirm your update", 
        "Click on this link to confirm the update:\n" + "You are trying to change your email to " + newEmail + "\n" + confirmUrl);
        this.mailConfirmationTokenRepository.save(mailConfirmationToken);
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
