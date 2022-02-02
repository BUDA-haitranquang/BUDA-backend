package com.higroup.Buda.api.staff.create;

import java.util.Optional;
import java.util.UUID;

import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.repositories.RoleRepository;
import com.higroup.Buda.repositories.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CreateStaffService {
    private final StaffRepository staffRepository;
    private final RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired 
    public CreateStaffService(StaffRepository staffRepository, RoleRepository roleRepository){
        this.roleRepository = roleRepository;
        this.staffRepository = staffRepository;
    }
    @Transactional
    public Staff createStaff(Long userID, Staff newStaff)
    {
        newStaff.setUserID(userID);
        newStaff.setStaffUUID(UUID.randomUUID().toString());
        Optional<Staff> staff = this.staffRepository.findStaffByStaffUUID(newStaff.getStaffUUID());
        if (staff.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Another staff with this UUID has already exists");
        }
        String phoneNumber = newStaff.getPhoneNumber();
        if ((phoneNumber==null) || (!phoneNumber.matches("[0-9]+")))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone number can not contain anything other than numbers");
        }
        newStaff.setAccount(phoneNumber.concat("_").concat(userID.toString()));
        staff = this.staffRepository.findStaffByAccount(newStaff.getAccount());
        if(staff.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
            "Another staff with this account has already exist: " + staff.get().getName());
        }
        newStaff.setPassword(this.bCryptPasswordEncoder.encode(newStaff.getPassword()));
        newStaff.addRole(roleRepository.findRoleByName("STAFF").get());
        this.staffRepository.save(newStaff);
        return newStaff;
    }
}
