package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.SalaryLog;
import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.SalaryLogRepository;
import com.higroup.Buda.repositories.StaffRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public SalaryLog registerNewSalaryLog(Long userID, SalaryLog salaryLog)
    {
        Optional<User> user = userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
        Optional<Staff> staff = staffRepository.findStaffByStaffID(salaryLog.getStaffID());
        if (staff.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Staff not found");
        }
        salaryLog.setUserID(userID);
        this.salaryLogRepository.save(salaryLog);
        return salaryLog;
    }
    public List<SalaryLog> findAllByUserID(Long userID)
    {
        return this.salaryLogRepository.findAllByUserID(userID);
    }
    public List<SalaryLog> findAllByStaffID(Long staffID)
    {
        return this.salaryLogRepository.findAllByStaffID(staffID);
    }
    public SalaryLog findByID(Long salaryLogID){
        return this.salaryLogRepository.findById(salaryLogID).get();
    }
    public void deleteSalaryLogbyID(Long id){
        Optional<SalaryLog> salaryLog = this.salaryLogRepository.findSalaryLogBySalaryLogID(id);
        if (salaryLog.isPresent())
        {
            this.salaryLogRepository.delete(salaryLog.get());
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Salary log not found");
    }
}
