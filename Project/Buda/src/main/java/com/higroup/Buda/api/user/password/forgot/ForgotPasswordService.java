package com.higroup.Buda.api.user.password.forgot;

import com.higroup.Buda.customDTO.UserUpdatePassword;
import com.higroup.Buda.entities.MailConfirmationToken;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ForgotPasswordService {
    private final UserRepository userRepository;

    @Autowired
    private ForgotPasswordMailService mailConfirmationTokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public ForgotPasswordService(UserRepository userRepository, ForgotPasswordMailService mailConfirmationTokenService)
    {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.userRepository = userRepository;
    }

    public void forgotPassword(String email)
    {
        Optional<User> user = userRepository.findUserByEmail(email);
        if ((email!=null) && (user.isPresent()))
            mailConfirmationTokenService.sendMailForgotPassword(email);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");

    }

    @Transactional
    public void updateForgotPassword(String token, UserUpdatePassword userUpdatePassword) {
        MailConfirmationToken confirmationToken = mailConfirmationTokenService.getToken(token);

        if (confirmationToken.getConfirmedAt() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Confirmation token expired");
        }

        mailConfirmationTokenService.setConfirmedAt(token);

        User user = confirmationToken.getUser();
        if (userUpdatePassword.getNewPassword().equals(userUpdatePassword.getConfirmNewPassword()))
        {
            user.setPassword(bCryptPasswordEncoder.encode(userUpdatePassword.getNewPassword()));
            this.userRepository.save(user);
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password not matching");
        }
    }
}
