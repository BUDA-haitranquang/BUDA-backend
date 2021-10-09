package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.User;
import com.higroup.Buda.exception.APIBadRequestException;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.util.JwtTokenUtil;
import com.higroup.Buda.util.SHA_256_Encode;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService implements UserDetailsService{
    private final UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    // dang ky user moi
    public ResponseEntity<?> registerNewUser(User newUser) {
        String email = newUser.getEmail();
        String phoneNumber = newUser.getPhoneNumber();
        String userName = newUser.getUserName();
        Optional<User> mailUser = userRepository.findUserByEmail(email);
        if ((email!=null) && (mailUser.isPresent()))
        {
            //BAD REQUEST da ton tai email
            return ResponseEntity.badRequest().body("Already exists email");
        }
        if (!EmailValidator.getInstance().isValid(email) || email.length() > 60)
        {
            //khong phai email
            return ResponseEntity.badRequest().body("Invalid email");
        }
        Optional<User> phoneUser = userRepository.findUserByPhoneNumber(phoneNumber);
        if ((phoneNumber!=null) && (phoneUser.isPresent()))
        {
            //BAD REQUEST da ton tai phone
            return ResponseEntity.badRequest().body("Already exists phoneNumber");
        }
        if (!phoneNumber.matches("[0-9]+"))
        {
            //khong phai phone
            return ResponseEntity.badRequest().body("Invalid phone");
        }
        Optional<User> userNameUser = userRepository.findUserByUserName(userName);
        if ((userName!=null) && (userNameUser.isPresent()))
        {
            //BAD REQUEST da ton tai username
            return ResponseEntity.badRequest().body("Already exists userName");
        }
        if (newUser.getPassword().length() < 8)
        {
            return ResponseEntity.badRequest().body("Weak password");
        }
        System.out.println(newUser.getPassword());
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
        return ResponseEntity.ok().body(newUser);
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

    public ResponseEntity<?> deleteUserByID(Long id) {
        if (id == null)
        {
            return ResponseEntity.badRequest().body("Invalid id");
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok().body("Deleted successfully");
    }

    public ResponseEntity<String> correctLogin(String email, String rawPassword)
    {
        Optional<User> mailUser = userRepository.findUserByEmail(email);
        if (mailUser.isEmpty())
        {
            return ResponseEntity.badRequest().body("User not found");
        }
        //System.out.println(mailUser.get().getPassword());
        //System.out.println(bCryptPasswordEncoder.encode(rawPassword));
        //System.out.println(this.bCryptPasswordEncoder.matches(rawPassword, mailUser.get().getPassword()));
        //System.out.println(rawPassword);
        if (bCryptPasswordEncoder.matches(rawPassword, mailUser.get().getPassword()))
        {
            JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
            UserDetails userDetails = mailUser.get();
            //System.out.println(userDetails);
            String returnBody = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok().body(returnBody);
        }
        return ResponseEntity.badRequest().body("false");
    }

    @Transactional
    public ResponseEntity<?> updateUserByID(Long id, String userName, String email, String phoneNumber, String firstName, String lastName, String password) {
        User thisUser = userRepository.findUserByUserID(id).get();
        Optional<User> mailUser = userRepository.findUserByEmail(email);
        if ((email!=null) && (mailUser.isPresent()) && (!mailUser.get().getUserID().equals(thisUser.getUserID())))
        {
            //BAD REQUEST da ton tai email
            return ResponseEntity.badRequest().body("Already used by another user Email");
        }
        if ((email!=null) && ((!EmailValidator.getInstance().isValid(email) || email.length() > 60)))
        {
            //khong phai email
            return ResponseEntity.badRequest().body("Invalid email");
        }
        Optional<User> phoneUser = userRepository.findUserByPhoneNumber(phoneNumber);
        if ((phoneNumber==null) || (!phoneNumber.matches("[0-9]+")))
        {
            //khong phai phone
            return ResponseEntity.badRequest().body("Invalid phoneNumber");
        }
        if (phoneUser.isPresent() && !phoneUser.get().getUserID().equals(thisUser.getUserID()))
        {
            //BAD REQUEST da ton tai phone
            return ResponseEntity.badRequest().body("Already used by another user phoneNumber");
        }
        Optional<User> userNameUser = userRepository.findUserByUserName(userName);
        if ((userName!=null) && (userNameUser.isPresent()) && (!userNameUser.get().getUserID().equals(thisUser.getUserID())))
        {
            //BAD REQUEST da ton tai username
            return ResponseEntity.badRequest().body(thisUser);
        }
        if ((password!=null) && (password.length() < 8))
        {
            return ResponseEntity.badRequest().body("Password is too short");
        }
        thisUser.setPassword(password);
        thisUser.setEmail(email);
        thisUser.setUserName(userName);
        thisUser.setLastName(lastName);
        thisUser.setFirstName(firstName);
        thisUser.setPhoneNumber(phoneNumber);
        userRepository.save(thisUser);
        return ResponseEntity.ok().body(thisUser);
    }

    @Transactional
    public void updateUser(User user) {

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> mailUser = this.userRepository.findUserByEmail(email);
        return mailUser.orElse(null);
        // TODO Auto-generated method stub
    }

    // TUONG UNG VOI 4 REQUEST BEN USER CONTROLLER
    // LOGIC FLOW (IF - ELSE CAC THU SE NAM O DAY)
}
