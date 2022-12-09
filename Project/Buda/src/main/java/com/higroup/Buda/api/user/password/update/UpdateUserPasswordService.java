package com.higroup.Buda.api.user.password.update;

import java.util.Optional;

import com.higroup.Buda.customDTO.UserUpdatePassword;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateUserPasswordService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UpdateUserPasswordService(UserRepository userRepository) {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.userRepository = userRepository;
    }
    public void updateUserPassword(Long userID, UserUpdatePassword userUpdatePassword)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        String rawPassword = userUpdatePassword.getCurrentPassword();
        if (bCryptPasswordEncoder.matches(rawPassword, user.get().getPassword()) && user.get().isEnabled())
        {
//            System.out.println(userUpdatePassword.getNewPassword() + ' ' + userUpdatePassword.getConfirmNewPassword());
            if (userUpdatePassword.getNewPassword().equals(userUpdatePassword.getConfirmNewPassword()))
            {
                user.get().setPassword(bCryptPasswordEncoder.encode(userUpdatePassword.getNewPassword()));
                this.userRepository.save(user.get());
                return;
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password not matching");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong password");
    }
}
