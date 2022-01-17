package com.higroup.Buda.api.staff.view;

import java.util.List;

import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.repositories.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
