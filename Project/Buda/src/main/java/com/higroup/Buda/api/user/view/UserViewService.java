package com.higroup.Buda.api.user.view;

import java.util.Optional;

import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserViewService {
    private final UserRepository userRepository;
    @Autowired
    public UserViewService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    public User findUserByUserID(Long userID)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isPresent()){
            return user.get();
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
    }
}
