package com.higroup.Buda.api.staff.salary;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.higroup.Buda.customDTO.ExpenseByTimeStatistics;
import com.higroup.Buda.entities.SalaryLog;
import com.higroup.Buda.entities.Staff;
import com.higroup.Buda.repositories.SalaryLogRepository;
import com.higroup.Buda.repositories.StaffRepository;

@Service
public class SalaryLogService {
    private final SalaryLogRepository salaryLogRepository;
    private final StaffRepository staffRepository;
    @Autowired
    public SalaryLogService(SalaryLogRepository salaryLogRepository, StaffRepository staffRepository)
    {
        this.staffRepository = staffRepository;
        this.salaryLogRepository = salaryLogRepository;
    }
    @Transactional
    public SalaryLog registerNewSalaryLog(Long userID, SalaryLog salaryLog)
    {
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
    public List<SalaryLog> findAllByStaffID(Long staffID, Long userID)
    {
        if(staffID == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "staff id invalid");
        }
        Staff staff = this.staffRepository.findById(staffID).get();
        if(staff == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "staff not exists");
        }
        if(!staff.getUserID().equals(userID)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "staff not belong to user");
        }
        return this.salaryLogRepository.findAllByStaffID(staffID);
    }
    public SalaryLog findByID(Long salaryLogID){
        return this.salaryLogRepository.findById(salaryLogID).get();
    }
    public void deleteSalaryLogbyID(Long salaryLogID, Long userID){
        if(salaryLogID == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "salary log id invalid");
        }
        Optional<SalaryLog> salaryLog = this.salaryLogRepository.findSalaryLogBySalaryLogID(salaryLogID);
        if (salaryLog.isPresent())
        {
            if(salaryLog.get().getUserID().equals(userID)){
                this.salaryLogRepository.delete(salaryLog.get());
            }
            else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "salary log not belong to user");
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Salary log not found");
    }
    public List<ExpenseByTimeStatistics> findSalaryLogExpenseCurrentMonth(Long userID)
    {
        return this.salaryLogRepository.findSalaryLogExpenseCurrentMonth(userID);
    }
    public List<ExpenseByTimeStatistics> findSalaryLogExpenseGroupByMonth(Long userID)
    {
        return this.salaryLogRepository.findSalaryLogExpenseGroupByMonth(userID);
    }
}
