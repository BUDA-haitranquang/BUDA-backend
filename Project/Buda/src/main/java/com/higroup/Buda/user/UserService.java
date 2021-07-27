package com.higroup.Buda.user;

import java.util.List;
import java.util.Optional;

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
    public void updateUserByID(Long id, String email, String phoneNumber, String firstName, String lastName) {
        User thisUser = userRepository.findUserByUserID(id).get();
        if (!thisUser.getEmail().equals(email)) {
            Optional<User> emailUser = userRepository.findUserByEmail(email);
            if (emailUser.isPresent()) {
                // throw exception here
            } else {
                Optional<User> phoneUser = userRepository.findUserByPhoneNumber(phoneNumber);
                // co user dung phone nay, dong thoi nguoi dung nay cung khong dung phone nay ->
                // cua nguoi khac
                if (phoneUser.isPresent() && (!thisUser.getPhoneNumber().equals(phoneNumber))) {
                    // throw exception here
                } else {
                    thisUser.setEmail(email);
                    thisUser.setPhoneNumber(phoneNumber);
                    thisUser.setFirstName(firstName);
                    thisUser.setLastName(lastName);
                }
            }
        }
    }

    @Transactional
    public void updateUser(User user) {

    }

    // TUONG UNG VOI 4 REQUEST BEN USER CONTROLLER
    // LOGIC FLOW (IF - ELSE CAC THU SE NAM O DAY)
}
