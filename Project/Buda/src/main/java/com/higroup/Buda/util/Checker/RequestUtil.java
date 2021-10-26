package com.higroup.Buda.util.Checker;

import javax.servlet.http.HttpServletRequest;

import com.higroup.Buda.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class RequestUtil {
    private final JwtTokenUtil jwtTokenUtil;
    @Autowired
    public RequestUtil(JwtTokenUtil jwtTokenUtil)
    {
        this.jwtTokenUtil = jwtTokenUtil;
    }
    public Long getUserID(HttpServletRequest httpServletRequest)
    {
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        Long userID = this.jwtTokenUtil.getUserIDFromToken(token);
        if (userID!=null && jwtTokenUtil.isValid(token))
        {
            return userID;
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }
}
