package com.higroup.Buda.api.staff.login;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.repositories.StaffRepository;
import com.higroup.Buda.security.jwt.JwtResponse;
import com.higroup.Buda.security.jwt.JwtTokenUtil;
import com.higroup.Buda.util.Config;

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
public class StaffLoginService implements UserDetailsService{
    private final StaffRepository staffRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public StaffLoginService(StaffRepository staffRepository)
    {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.staffRepository = staffRepository;
    }
    public JwtResponse correctLogin(String account, String rawPassword)
    {
        Optional<Staff> staff = this.staffRepository.findStaffByAccount(account);
        if ((staff.isPresent())
        &&(this.bCryptPasswordEncoder.matches(rawPassword, staff.get().getPassword())))
        {
            if (!(staff.get().getEnabled().equals(Boolean.TRUE))){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This staff account has been disabled");
            }
            JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
            UserDetails userDetails = loadUserByUsername(account);

            // return token 
            Map<String, Object> claims = new HashMap<String, Object>();
            claims.put("userID", staff.get().getUserID());
            claims.put("roles", userDetails.getAuthorities());
            String jwtaccessToken = jwtTokenUtil.generateToken(userDetails, claims, Config.HoursAccessToken);
            String jwtrefreshToken = jwtTokenUtil.generateToken(userDetails, claims, Config.HoursRefreshToken);
        
            return new JwtResponse(jwtaccessToken, jwtrefreshToken);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "false");
    }
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Optional<Staff> staff = this.staffRepository.findStaffByAccount(account);

        if(!staff.isPresent()){
            throw new UsernameNotFoundException("Not found staff with uuid: " + account);
        }
        
        staff.get().getRoles().forEach(role -> 
            {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        );
        return new org.springframework.security.core.userdetails.User(staff.get().getAccount(), 
                                                                      staff.get().getPassword(), authorities);
    }
}
