package com.higroup.Buda.api.user.register;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.higroup.Buda.customDTO.UserRegister;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.entities.enumeration.PlanType;
import com.higroup.Buda.repositories.RoleRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.services.MailConfirmationTokenService;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserRegisterService implements UserDetailsService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    private MailConfirmationTokenService mailConfirmationTokenService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserRegisterService(UserRepository userRepository,
    RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }
    @Transactional
    public void registerNewUser(UserRegister userRegister) {
        String email = userRegister.getEmail();
        String phoneNumber = userRegister.getPhoneNumber();
        String username = userRegister.getUsername();

        Optional<User> mailUser = userRepository.findUserByEmail(email);
        if ((email!=null) && (mailUser.isPresent()))
        {
            //BAD REQUEST da ton tai email
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists email");
        }
        if (!EmailValidator.getInstance().isValid(email) || email.length() > 60)
        {
            //khong phai email
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email");
        }
        Optional<User> phoneUser = userRepository.findUserByPhoneNumber(phoneNumber);
        if ((phoneNumber!=null) && (phoneUser.isPresent()))
        {
            //BAD REQUEST da ton tai phone
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists phoneNumber");
        }
        if (!phoneNumber.matches("[0-9]+"))
        {
            //khong phai phone
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid phone");
        }
        Optional<User> userNameUser = userRepository.findUserByUserName(username);
        if ((username!=null) && (userNameUser.isPresent()))
        {
            //BAD REQUEST da ton tai username
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists userName");
        }
        if (userRegister.getPassword().length() < 8)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Weak password");
        }

        User newUser = new User(userRegister);
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        newUser.addRole(roleRepository.findRoleByName("USER").get());
        newUser.setPlanType(PlanType.BASIC);
        userRepository.save(newUser);
        mailConfirmationTokenService.sendMailConfirmationTo(email);
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Optional<User> mailUser = this.userRepository.findUserByEmail(email);
        
        if(!mailUser.isPresent()){
            throw new UsernameNotFoundException("Not found user with email: " + email);
        }

        mailUser.get().getRoles().forEach(role  -> 
            {authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(mailUser.get().getEmail(), 
                                                                      mailUser.get().getPassword(), authorities);
        
    }
}
