package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.SalaryLog;
import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.SalaryLogRepository;
import com.higroup.Buda.repositories.StaffRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SalaryLogService {
    private final SalaryLogRepository salaryLogRepository;
    private final UserRepository userRepository;
    private final StaffRepository staffRepository;
    @Autowired
    public SalaryLogService(SalaryLogRepository salaryLogRepository, UserRepository userRepository, StaffRepository staffRepository)
    {
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
        this.salaryLogRepository = salaryLogRepository;
    }
    public ResponseEntity<?> registerNewSalaryLog(Long userID, SalaryLog salaryLog)
    {
        Optional<User> user = userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            return ResponseEntity.badRequest().body("User not found");
        }
        Optional<Staff> staff = staffRepository.findStaffByStaffID(salaryLog.getStaffID());
        if (staff.isEmpty())
        {
            return ResponseEntity.badRequest().body("Staff not found");
        }
        salaryLog.setUserID(userID);
        this.salaryLogRepository.save(salaryLog);
        return ResponseEntity.ok().body(salaryLog.toString());
    }
    public List<SalaryLog> findAllByUserID(Long userID)
    {
        return this.salaryLogRepository.findAllByUserID(userID);
    }
    public List<SalaryLog> findAllByStaffID(Long staffID)
    {
        return this.salaryLogRepository.findAllByStaffID(staffID);
    }
}
