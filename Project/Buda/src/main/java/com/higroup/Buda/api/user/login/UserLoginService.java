package com.higroup.Buda.api.user.login;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import com.higroup.Buda.api.user.register.SendConfirmRegisterMailService;
import com.higroup.Buda.customDTO.UserLogin;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.jwt.JwtResponse;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserLoginService implements UserDetailsService{
    private final UserRepository userRepository;
    private final SendConfirmRegisterMailService mailConfirmationTokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    @Autowired
    public UserLoginService(UserRepository userRepository, SendConfirmRegisterMailService mailConfirmationTokenService, JwtTokenUtil jwtTokenUtil)
    {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.jwtTokenUtil = jwtTokenUtil;
        this.mailConfirmationTokenService = mailConfirmationTokenService;
        this.userRepository = userRepository;
    }
    public JwtResponse correctLogin(@Valid UserLogin userLogin)
    {
        String email = userLogin.getEmail();
        String rawPassword = userLogin.getPassword();
        Optional<User> mailUser = userRepository.findUserByEmail(email);
        if (mailUser.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }

        if(!mailUser.get().isEnabled()) {
            mailConfirmationTokenService.sendMailConfirmationTo(email);
        }

        if (bCryptPasswordEncoder.matches(rawPassword, mailUser.get().getPassword()) && mailUser.get().isEnabled())
        {
            UserDetails userDetails = loadUserByUsername(mailUser.get().getEmail());
            //System.out.println(userDetails);
            String jwtaccessToken = jwtTokenUtil.generateAccessToken(userDetails, mailUser.get().getUserID());
            String jwtrefreshToken = jwtTokenUtil.generateRefreshToken(userDetails, mailUser.get().getUserID());

            return new JwtResponse(jwtaccessToken, jwtrefreshToken);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "false");
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
