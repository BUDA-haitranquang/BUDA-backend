package com.higroup.Buda.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.jwt.JwtResponse;
import com.higroup.Buda.repositories.RoleRepository;
import com.higroup.Buda.repositories.StaffRepository;
import com.higroup.Buda.util.Config;
import com.higroup.Buda.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StaffService implements UserDetailsService{
    private final StaffRepository staffRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public StaffService(StaffRepository staffRepository, RoleRepository roleRepository)
    {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.staffRepository = staffRepository;
        this.roleRepository = roleRepository;
    }

    public JwtResponse correctLogin(String uuid, String rawPassword)
    {
        Optional<Staff> staff = this.staffRepository.findStaffByStaffUUID(uuid);
        if ((staff.isPresent())&&(this.bCryptPasswordEncoder.matches(rawPassword, staff.get().getPassword())))
        {
            JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
            UserDetails userDetails = loadUserByUsername(uuid);

            // return token 
            Map<String, Object> claims = new HashMap<String, Object>();
            claims.put("staffID", staff.get().getStaffID());
            claims.put("roles", userDetails.getAuthorities());
            String jwtaccessToken = jwtTokenUtil.generateToken(userDetails, claims, Config.HoursAccessToken);
            String jwtrefreshToken = jwtTokenUtil.generateToken(userDetails, claims, Config.HoursRefreshToken);
        
            return new JwtResponse(jwtaccessToken, jwtrefreshToken);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "false");
    }

    public ResponseEntity<?> registerNewStaff(Staff newStaff)
    {
        Optional<Staff> staff = this.staffRepository.findStaffByStaffUUID(newStaff.getLoginID());
        if (staff.isPresent())
        {
            return ResponseEntity.badRequest().body("Exists UUID, try again");
        }
        String phoneNumber = staff.get().getPhoneNumber();
        if ((phoneNumber==null) || (!phoneNumber.matches("[0-9]+")))
        {
            return ResponseEntity.badRequest().body("Invalid PhoneNumber");
        }
        newStaff.setPassword(this.bCryptPasswordEncoder.encode(newStaff.getPassword()));
        newStaff.addRole(roleRepository.findRoleByName("STAFF").get());
        this.staffRepository.save(newStaff);
        return ResponseEntity.ok().body(newStaff);
    }

    public List<Staff> findAllByUserID(Long userID)
    {
        return this.staffRepository.findAllByUserID(userID);
    }

    @Override
    public UserDetails loadUserByUsername(String staffUUID) throws UsernameNotFoundException {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Optional<Staff> staff = this.staffRepository.findStaffByStaffUUID(staffUUID);

        if(!staff.isPresent()){
            throw new UsernameNotFoundException("Not found staff with uuid: " + staffUUID);
        }
        
        staff.get().getRoles().forEach(role -> 
            {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        );
        return new org.springframework.security.core.userdetails.User(staff.get().getStaffUUID(), 
                                                                      staff.get().getPassword(), authorities);
    }

    public void deleteStaffByID(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id");
        }
        staffRepository.deleteById(id);
    }

    @Transactional
    public Staff updateStaffByID(Long id, String Name, String phoneNumber, String password, String address, Double salary, String staffUUID){
        Staff thisstaff = staffRepository.findStaffByStaffID(id).get();
        Optional<Staff> staff = staffRepository.findStaffByStaffUUID(staffUUID);
        if((staffUUID != null) && (staff.isPresent()) && !staff.get().getStaffID().equals(id)){
            //BAD REQUEST da ton tai email
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UUID Already used by another staff");
        }
        thisstaff.address(address);
        thisstaff.name(Name);
        thisstaff.phoneNumber(phoneNumber);
        thisstaff.password(password);
        thisstaff.salary(salary);
        thisstaff.loginID(staffUUID);

        staffRepository.save(thisstaff);
        return thisstaff;
    }   

    public Staff getStaffByID(Long id){
        return staffRepository.findStaffByStaffID(id).get();
    }

}
