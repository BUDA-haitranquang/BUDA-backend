package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.repositories.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    private final StaffRepository staffRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public StaffService(StaffRepository staffRepository)
    {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.staffRepository = staffRepository;
    }
    public ResponseEntity<?> correctLogin(String uuid, String rawPassword)
    {
        Optional<Staff> staff = this.staffRepository.findStaffByStaffUUID(uuid);
        if ((staff.isPresent())&&(this.bCryptPasswordEncoder.matches(rawPassword, staff.get().getPassword())))
        {
            return ResponseEntity.ok().body("True");
        }
        return ResponseEntity.ok().body("False");
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
        this.staffRepository.save(newStaff);
        return ResponseEntity.ok().body(newStaff);
    }
    public List<Staff> findAllByUserID(Long userID)
    {
        return this.staffRepository.findAllByUserID(userID);
    }
}
