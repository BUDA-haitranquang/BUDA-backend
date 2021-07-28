package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.util.SHA_256_Encode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // dang ky user moi
    public void registerNewUser(User newUser) {
        Optional<User> mailUser = userRepository.findUserByEmail(newUser.getEmail());
        if (mailUser.isPresent()) {
            // throw Exception here
            return;
        }
        if (newUser.getPhoneNumber() != null) {
            Optional<User> phoneUser = userRepository.findUserByPhoneNumber(newUser.getPhoneNumber());
            if (phoneUser.isPresent()) {
                // throw Exception here
                return;
            }
        }
        userRepository.save(newUser);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByID(Long id) {
        // try-catch
        return userRepository.findUserByUserID(id).get();
    }

    public User getUserByUserUUID(String uuid) {
        // try-catch
        return userRepository.findUserByUserUUID(uuid).get();
    }

    public void deleteUserByID(Long id) {
        userRepository.deleteById(id);
    }

    public boolean correctLogin(String email, String encodedPassword)
    {
        Optional<User> mailUser = userRepository.findUserByEmail(email);
        if (mailUser.isPresent() && (mailUser.get().getPassword().equals((encodedPassword))))
        {
            return true;
        }
        return false;
    }

    @Transactional
    public void updateUserByID(Long id, String userName, String email, String phoneNumber, String firstName, String lastName, String password) {
        User thisUser = userRepository.findUserByUserID(id).get();
        Optional<User> mailUser = userRepository.findUserByEmail(email);
        if ((email!=null) && (mailUser.isPresent()) && (mailUser.get().getUserID()!=thisUser.getUserID()))
        {
            //BAD REQUEST da ton tai email
            return;
        }
        Optional<User> phoneUser = userRepository.findUserByPhoneNumber(phoneNumber);
        if ((phoneNumber!=null) && (phoneUser.isPresent()) && (phoneUser.get().getUserID()!=thisUser.getUserID()))
        {
            //BAD REQUEST da ton tai phone
            return;
        }
        Optional<User> userNameUser = userRepository.findUserByUserName(userName);
        if ((userName!=null) && (userNameUser.isPresent()) && (userNameUser.get().getUserID()!=thisUser.getUserID()))
        {
            //BAD REQUEST da ton tai username
            return;
        }
        thisUser.setPassword(password);
        thisUser.setEmail(email);
        thisUser.setUserName(userName);
        thisUser.setLastName(lastName);
        thisUser.setFirstName(firstName);
        thisUser.setPhoneNumber(phoneNumber);
        userRepository.save(thisUser);
    }

    @Transactional
    public void updateUser(User user) {

    }

    // TUONG UNG VOI 4 REQUEST BEN USER CONTROLLER
    // LOGIC FLOW (IF - ELSE CAC THU SE NAM O DAY)
}
