package com.higroup.Buda.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.higroup.Buda.api.staff.crud.StaffService;
import com.higroup.Buda.services.UserService;
import com.higroup.Buda.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
    

    @Autowired
    private UserService jwtUserDetailsService;
    @Autowired
    private StaffService jwtStaffDetailService;

    @Autowired 
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
            final String requestTokenHeader = request.getHeader("Authorization");
            // System.out.println(requestTokenHeader);
            String username = null;
            String jwtToken = null;
            // JWT Token is in the form "Bearer token". Remove Bearer word and get
            // only the Token
            // System.out.println(requestTokenHeader.toString());
            //get role 
            
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                jwtToken = requestTokenHeader.substring(7);
                try {
                    username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                    System.out.println(username);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Uanble to get Jwt Token");
                    // System.out.println("Unable to get JWT Token");
                } catch (ExpiredJwtException e) {
                    // throw new ExpiredJwtException(jwtTokenUtil.ge, claims, message)
                    System.out.println("JWT Token has expired");
                }
            } else {
                logger.warn("JWT Token does not begin with Bearer String");
            }

            // Once we get the token validate it.
		    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                ArrayList<?> roles = (ArrayList<?>) this.jwtTokenUtil.getRolesFromToken(requestTokenHeader.substring(7));

                if(roles.isEmpty()){
                    throw new IOException("Role empty");
                }
                
                String role = (String)((LinkedHashMap<?, ?>)roles.get(0)).get("authority");
                UserDetails userDetails;
                
                System.out.println("ROLE: " + role);
                
                // if ROLE USER
                if(role.equals("USER")){
                    userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
                }
                else if (role.equals("STAFF")){
                    userDetails = this.jwtStaffDetailService.loadUserByUsername(username);
                }
                else return; // NOT YET
                // if token is valid configure Spring Security to manually set
                // authentication
                System.out.println(userDetails.toString());
                if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // After setting the Authentication in the context, we specify
                    // that the current user is authenticated. So it passes the
                    // Spring Security Configurations successfully.
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            chain.doFilter(request, response);
    }
}
