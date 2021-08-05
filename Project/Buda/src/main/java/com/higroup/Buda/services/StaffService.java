package com.higroup.Buda.services;

import java.util.Optional;

import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.repositories.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    private StaffRepository staffRepository;
    @Autowired
    public StaffService(StaffRepository staffRepository)
    {
        this.staffRepository = staffRepository;
    }
    public ResponseEntity<?> correctLogin(String uuid, String password)
    {
        Optional<Staff> staff = this.staffRepository.findStaffByLoginID(uuid);
        if (staff.get().getPassword().equals(password))
        {
            return ResponseEntity.ok().body("True");
        }
        return ResponseEntity.ok().body("False");
    }
    public ResponseEntity<?> registerNewStaff(Staff newStaff)
    {
        Optional<Staff> staff = this.staffRepository.findStaffByLoginID(newStaff.getLoginID());
        if (staff.isPresent())
        {
            return ResponseEntity.badRequest().body("Exists UUID, try again");
        }
        String phoneNumber = staff.get().getPhoneNumber();
        if ((phoneNumber==null) || (!phoneNumber.matches("[0-9]+")))
        {
            return ResponseEntity.badRequest().body("Invalid PhoneNumber");
        }
        this.staffRepository.save(newStaff);
        return ResponseEntity.ok().body(newStaff.toString());
    }
}
