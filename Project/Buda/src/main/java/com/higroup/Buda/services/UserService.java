package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.util.SHA_256_Encode;

import org.apache.commons.validator.routines.EmailValidator;
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
        String email = newUser.getEmail();
        String phoneNumber = newUser.getPhoneNumber();
        String userName = newUser.getUserName();
        Optional<User> mailUser = userRepository.findUserByEmail(email);
        if ((email!=null) && (mailUser.isPresent()))
        {
            //BAD REQUEST da ton tai email
            return;
        }
        if (!EmailValidator.getInstance().isValid(email) || email.length() > 60)
        {
            //khong phai email
            return;
        }
        Optional<User> phoneUser = userRepository.findUserByPhoneNumber(phoneNumber);
        if ((phoneNumber!=null) && (phoneUser.isPresent()))
        {
            //BAD REQUEST da ton tai phone
            return;
        }
        if (!phoneNumber.matches("[0-9]+"))
        {
            //khong phai phone
            return;
        }
        Optional<User> userNameUser = userRepository.findUserByUserName(userName);
        if ((userName!=null) && (userNameUser.isPresent()))
        {
            //BAD REQUEST da ton tai username
            return;
        }
        if (newUser.getPassword().length() < 8)
        {
            return;
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
        if (id == null)
        {
            return;
        }
        userRepository.deleteById(id);
    }

    public boolean correctLogin(String email, String encodedPassword)
    {
        Optional<User> mailUser = userRepository.findUserByEmail(email);
        if (!mailUser.isPresent())
        {
            return false;
        }
        System.out.println(mailUser.get().getPassword());
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
        if (!EmailValidator.getInstance().isValid(email) || email.length() > 60)
        {
            //khong phai email
            return;
        }
        Optional<User> phoneUser = userRepository.findUserByPhoneNumber(phoneNumber);
        if (!phoneNumber.matches("[0-9]+"))
        {
            //khong phai phone
            return;
        }
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
        if (password.length() < 8)
        {
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
