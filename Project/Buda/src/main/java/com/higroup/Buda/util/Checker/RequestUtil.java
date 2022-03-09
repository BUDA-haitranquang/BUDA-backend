package com.higroup.Buda.util.Checker;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.entities.Plan;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.entities.enumeration.PlanType;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.security.jwt.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class RequestUtil {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    @Autowired
    public RequestUtil(JwtTokenUtil jwtTokenUtil, UserRepository userRepository)
    {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }
    public Long getUserIDFromUserToken(HttpServletRequest httpServletRequest)
    {
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        Long userID = this.jwtTokenUtil.getUserIDFromToken(token);
        String email = this.jwtTokenUtil.getUsernameFromToken(token);
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if ((userID!=null) && (user.isPresent()) 
        && (user.get().getEmail().equals(email)) 
        && (jwtTokenUtil.isValid(token)))
        {
            if (jwtTokenUtil.getTokenTypeFromToken(token).equals("Access"))
            {
                return userID;
            }
            else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid access token");
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }
    public Long getProUserIDFromUserToken(HttpServletRequest httpServletRequest)
    {
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        Long userID = this.jwtTokenUtil.getUserIDFromToken(token);
        String email = this.jwtTokenUtil.getUsernameFromToken(token);
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if ((userID!=null) && (user.isPresent()) 
        && (user.get().getEmail().equals(email))
        && ((user.get().getPlanType().equals(PlanType.PRO)) || (user.get().getPlanType().equals(PlanType.PREMIUM))) 
        && (jwtTokenUtil.isValid(token)))
        {
            if (jwtTokenUtil.getTokenTypeFromToken(token).equals("Access"))
            {
                return userID;
            }
            else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid access token");
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }
    public Long getPremiumUserIDFromUserToken(HttpServletRequest httpServletRequest)
    {
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        Long userID = this.jwtTokenUtil.getUserIDFromToken(token);
        String email = this.jwtTokenUtil.getUsernameFromToken(token);
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if ((userID!=null) && (user.isPresent()) 
        && (user.get().getEmail().equals(email)) 
        && (user.get().getPlanType().equals(PlanType.PREMIUM))
        && (jwtTokenUtil.isValid(token)))
        {
            if (jwtTokenUtil.getTokenTypeFromToken(token).equals("Access"))
            {
                return userID;
            }
            else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid access token");
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }
    
    // public Long getStaffID(HttpServletRequest httpServletRequest)
    // {
    //     final String token = httpServletRequest.getHeader("Authorization").substring(7);
    //     Long staffID = this.jwtTokenUtil.getStaffIDFromToken(token);
    //     if (staffID!=null && jwtTokenUtil.isValid(token))
    //     {
    //         return staffID;
    //     }
    //     throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    // }
}
