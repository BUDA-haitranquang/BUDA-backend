package com.higroup.Buda.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    //TUONG UNG VOI 4 REQUEST BEN USER CONTROLLER
    //LOGIC FLOW (IF - ELSE CAC THU SE NAM O DAY)
}
