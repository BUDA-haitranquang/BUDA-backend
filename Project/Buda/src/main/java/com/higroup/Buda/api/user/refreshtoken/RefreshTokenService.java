package com.higroup.Buda.api.user.refreshtoken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

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
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Service
public class RefreshTokenService implements UserDetailsService{
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    @Autowired
    public RefreshTokenService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil)
    {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }
    public JwtResponse generateNewToken(String token)
    {
        if ((jwtTokenUtil.isValid(token)) && (jwtTokenUtil.getTokenTypeFromToken(token).equals("Refresh")))
        {
            Optional<User> mailUser = this.userRepository.findUserByUserID(jwtTokenUtil.getUserIDFromToken(token));
            if (mailUser.isPresent())
            {
                UserDetails userDetails = loadUserByUsername(mailUser.get().getEmail());
                String jwtaccessToken = jwtTokenUtil.generateAccessToken(userDetails, mailUser.get().getUserID());
                String jwtrefreshToken = jwtTokenUtil.generateRefreshToken(userDetails, mailUser.get().getUserID());
                return new JwtResponse(jwtaccessToken, jwtrefreshToken);
            }
            else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid refresh token");
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
