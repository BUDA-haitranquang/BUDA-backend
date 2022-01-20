package com.higroup.Buda.api.staff.view;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.repositories.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ViewStaffService {
    private StaffRepository staffRepository;
    @Autowired
    public ViewStaffService(StaffRepository staffRepository){
        this.staffRepository = staffRepository;
    }
    public List<Staff> findAllStaffByUserID(Long userID){
        return this.staffRepository.findAllByUserID(userID);
    }
    public Staff findStaffByStaffID(Long userID, Long staffID){
        Optional<Staff> staffOptional = this.staffRepository.findStaffByStaffID(staffID);
        if ((staffOptional.isPresent()) && (staffOptional.get().getUserID().equals(userID))){
            return staffOptional.get();
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found");
    }
}
