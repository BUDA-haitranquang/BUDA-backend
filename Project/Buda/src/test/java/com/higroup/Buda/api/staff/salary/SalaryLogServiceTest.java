package com.higroup.Buda.api.staff.salary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.SalaryLog;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class SalaryLogServiceTest {
    @Autowired
    private SalaryLogService salaryLogService;
    @Test
    void testDeleteSalaryLogbyID() {

    }

    @Test
    void testFindAllByStaffID() {
        
        
    }

    @Test
    void testFindAllByUserID() {
        for (long i = 0; i < 3; i++)
        {
            List<SalaryLog> salaryLogs = this.salaryLogService.findAllByUserID(Long.valueOf(i));
            for (SalaryLog salaryLog: salaryLogs)
            {
                assertEquals(salaryLog.getUserID(), Long.valueOf(i));
            }
        }
    }

    @Test
    void testFindByID() {

    }

    @Test
    void testFindSalaryLogExpenseCurrentMonth() {
    }

    @Test
    void testFindSalaryLogExpenseGroupByMonth() {

    }

    @Test
    void testRegisterNewSalaryLog() {

    }
}
