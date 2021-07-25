package com.higroup.Buda.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    //dang ky user moi
    public void registerNewUser(User newUser)
    {
        Optional<User> user = userRepository.findUserByEmail(newUser.getEmail());
        if (user.isPresent())
        {
            //throw Exception here
        }
        userRepository.save(newUser);
    }

    public User getUserByID(Long id)
    {
        return userRepository.getById(id);
    }

    public void deleteUserByID(Long id)
    {
        userRepository.deleteById(id);
    }

    public void updateUser(@RequestBody User user)
    {

    }

    //TUONG UNG VOI 4 REQUEST BEN USER CONTROLLER
    //LOGIC FLOW (IF - ELSE CAC THU SE NAM O DAY)
}
