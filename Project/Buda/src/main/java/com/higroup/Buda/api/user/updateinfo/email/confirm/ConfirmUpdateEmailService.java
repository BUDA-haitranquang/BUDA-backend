package com.higroup.Buda.api.user.updateinfo.email.confirm;

import java.time.LocalDateTime;
import java.util.Optional;

import com.higroup.Buda.entities.MailConfirmationToken;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.entities.enumeration.MailTokenType;
import com.higroup.Buda.repositories.MailConfirmationTokenRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ConfirmUpdateEmailService {
    private final UserRepository userRepository;
    private final MailConfirmationTokenRepository mailConfirmationTokenRepository;
    @Autowired
    public ConfirmUpdateEmailService(UserRepository userRepository, MailConfirmationTokenRepository mailConfirmationTokenRepository){
        this.userRepository = userRepository;
        this.mailConfirmationTokenRepository = mailConfirmationTokenRepository;
    }
    public User confirmUpdateEmailToken(String token){
        Optional<MailConfirmationToken> mailConfirmationTokenOptional = this.mailConfirmationTokenRepository.findByToken(token);
        if (!mailConfirmationTokenOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token not found");
        }
        MailConfirmationToken mailConfirmationToken = mailConfirmationTokenOptional.get();
        if (!mailConfirmationToken.getMailTokenType().equals(MailTokenType.UPDATE_INFO)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token type");
        }
        LocalDateTime expiredAt = mailConfirmationToken.getExpiredAt();
        if (expiredAt.isBefore(LocalDateTime.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Confirmation token expired");
        }
        mailConfirmationToken.setConfirmedAt(LocalDateTime.now());
        User user = mailConfirmationToken.getUser();
        user.setEmail(mailConfirmationToken.getTargetEmail());
        this.userRepository.save(user);
        this.mailConfirmationTokenRepository.save(mailConfirmationToken);
        return user;
    }
}
