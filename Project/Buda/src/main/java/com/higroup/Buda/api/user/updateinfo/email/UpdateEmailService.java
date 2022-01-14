package com.higroup.Buda.api.user.updateinfo.email;

import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.UserRepository;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateEmailService {
    private final UserRepository userRepository;
    private final SendConfirmUpdateEmailService sendConfirmUpdateEmailService;
    public UpdateEmailService(UserRepository userRepository, SendConfirmUpdateEmailService sendConfirmUpdateEmailService){
        this.userRepository = userRepository;
        this.sendConfirmUpdateEmailService = sendConfirmUpdateEmailService;
    }
    @Transactional
    public void updateEmail(Long userID, SimpleEmailDTO simpleEmailDTO){
        Optional<User> userOptional = this.userRepository.findUserByUserID(userID);
        String email = simpleEmailDTO.getEmail();
        if (!userOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
        if (!EmailValidator.getInstance().isValid(email)
        || (email.length() > 60)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email");
        }
        Optional<User> mailUserOptional = this.userRepository.findUserByEmail(email);
        if (mailUserOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email has already been used");
        }
        //TO DO:
        //Need to send an email to current email to confirm 
        this.sendConfirmUpdateEmailService.sendConfirmationUpdateEmail(
            userOptional.get().getEmail(), simpleEmailDTO.getEmail());
    }
}
