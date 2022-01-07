package com.higroup.Buda.api.user.register.confirm;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.higroup.Buda.entities.MailConfirmationToken;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.jwt.JwtResponse;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.services.MailConfirmationTokenService;
import com.higroup.Buda.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ConfirmAccountActivationService implements UserDetailsService{
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    @Autowired
    private MailConfirmationTokenService mailConfirmationTokenService;

    @Autowired
    public ConfirmAccountActivationService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }
    @Transactional
    public User confirmAccountActivation(String token) {
        MailConfirmationToken confirmationToken = mailConfirmationTokenService.getToken(token);

        if (confirmationToken.getConfirmedAt() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Confirmation token expired");
        }

        mailConfirmationTokenService.setConfirmedAt(token);

        User user = confirmationToken.getUser();
        this.enableUser(confirmationToken.getUser().getEmail());
        return user;
    }

    private void enableUser(String email) {
        System.out.println(email);
        userRepository.enableUserByEmail(email);
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
