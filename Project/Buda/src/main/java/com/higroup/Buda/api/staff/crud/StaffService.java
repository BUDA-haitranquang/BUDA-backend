package com.higroup.Buda.api.staff.crud;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.higroup.Buda.BeanUtils.NullAwareBeanUtilsBean;
import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.entities.enumeration.StaffPosition;
import com.higroup.Buda.jwt.JwtResponse;
import com.higroup.Buda.repositories.RoleRepository;
import com.higroup.Buda.repositories.StaffRepository;
import com.higroup.Buda.util.Config;
import com.higroup.Buda.util.JwtTokenUtil;

import org.apache.commons.beanutils.BeanUtilsBean;
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

    public JwtResponse correctLogin(String account, String rawPassword)
    {
        Optional<Staff> staff = this.staffRepository.findStaffByAccount(account);
        if ((staff.isPresent())&&(this.bCryptPasswordEncoder.matches(rawPassword, staff.get().getPassword())))
        {
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
    @Transactional
    public ResponseEntity<?> registerNewStaff(Staff newStaff, Long userID)
    {
        newStaff.setUserID(userID);
        Optional<Staff> staff = this.staffRepository.findStaffByStaffUUID(newStaff.getStaffUUID());
        if (staff.isPresent())
        {
            return ResponseEntity.badRequest().body("Exists UUID, try again");
        }

        staff = this.staffRepository.findStaffByAccount(newStaff.getAccount());
        if(staff.isPresent()){
            return ResponseEntity.badRequest().body("Exists Account, try again");
        }

        String phoneNumber = newStaff.getPhoneNumber();
        if ((phoneNumber==null) || (!phoneNumber.matches("[0-9]+")))
        {
            return ResponseEntity.badRequest().body("Invalid PhoneNumber");
        }
        newStaff.setPassword(this.bCryptPasswordEncoder.encode(newStaff.getPassword()));
        newStaff.addRole(roleRepository.findRoleByName("STAFF").get());
        newStaff.setStaffUUID(UUID.randomUUID().toString());
        this.staffRepository.save(newStaff);
        return ResponseEntity.ok().body(newStaff);
    }

    public List<Staff> findAllByUserID(Long userID)
    {
        return this.staffRepository.findAllByUserID(userID);
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

    @Transactional
    public void deleteStaffByID(Long staffID, Long userID){
        // check valid staffid, userid
        this.findStaffByID(staffID, userID);
        staffRepository.deleteById(staffID);
    }

    @Transactional
    public Staff updateStaffByID(Long staffID, Long userID, String name, String phoneNumber, String password, String address, Double salary, 
                                 String staffUUID, String account, StaffPosition staffPosition)
    {
        if(staffID == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id");
        }
        Staff thisstaff = this.findStaffByID(staffID, userID);
        
        if(staffUUID != null){
            Optional<Staff> staff = staffRepository.findStaffByStaffUUID(staffUUID);
            if(staff.isPresent() && !staff.get().getStaffID().equals(staffID)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UUID Already used by another staff");
            }
        }

        if(name != null){
            thisstaff.setName(name);
        }
        if(address != null){
            thisstaff.setAddress(address);
        }
        if(phoneNumber != null){
            thisstaff.setPhoneNumber(phoneNumber);
        }
        if(staffPosition != null){
            thisstaff.setStaffPosition(staffPosition);
        }
        if(staffUUID != null){
            thisstaff.setStaffUUID(staffUUID);
        }
        if(password != null){
            thisstaff.setPassword(bCryptPasswordEncoder.encode(password));
        }
        if(account != null){
            thisstaff.setAccount(account);
        }
        if(salary != null){
            thisstaff.setSalary(salary);
        }
        
        staffRepository.save(thisstaff);
        return thisstaff;
    }   

    @Transactional
    public Staff updateStaff(Long userID, Long staffID, Staff staff) throws IllegalAccessException, InvocationTargetException
    {
        Optional<Staff> oldStaffOptional = this.staffRepository.findStaffByStaffID(staffID);
        if ((oldStaffOptional.isPresent()) && (oldStaffOptional.get().getUserID().equals(userID)))
        {
            Staff oldStaff = oldStaffOptional.get();
            //These information can't be changed by update info request
            staff.setUserID(userID);
            staff.setAccount(oldStaff.getAccount());
            staff.setStaffUUID(oldStaff.getStaffUUID());
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(oldStaff, staff);
            this.staffRepository.save(oldStaff);
            return oldStaff;
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Staff not found");
    }

    public Staff findStaffByID(Long staffID, Long userID){
        if(staffID == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id");
        }
        Staff staff = this.staffRepository.findById(staffID).get();
        if(staff == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "staff not exists");
        }
        if(!staff.getUserID().equals(userID)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "user have no staff");
        }
        return staff;
    }

}
