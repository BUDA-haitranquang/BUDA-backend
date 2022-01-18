package com.higroup.Buda.api.staff.deactivate;

import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.repositories.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DeactivateStaffService {
    private final StaffRepository staffRepository;
    @Autowired
    public DeactivateStaffService(StaffRepository staffRepository){
        this.staffRepository = staffRepository;
    }
    @Transactional
    public Staff deactivateStaffByStaffID(Long userID, Long staffID){
        Optional<Staff> staffOptional = this.staffRepository.findStaffByStaffID(staffID);
        if ((staffOptional.isPresent()) && (staffOptional.get().getUserID().equals(userID))){
            Staff staff = staffOptional.get();
            staff.setEnabled(Boolean.FALSE);
            this.staffRepository.save(staff);
            return staff;
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Staff not found");
    }
}
