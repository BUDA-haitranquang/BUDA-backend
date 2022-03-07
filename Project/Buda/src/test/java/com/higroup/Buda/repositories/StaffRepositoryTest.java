package com.higroup.Buda.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.Staff;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class StaffRepositoryTest {
    @Autowired
    private StaffRepository staffRepository;
    @Test
    void testFindAllByUserID() {
        for (long i=0; i < 3; i++)
        {
            List<Staff> staffs = staffRepository.findAllByUserID(Long.valueOf(i));
            for (Staff staff: staffs)
            {
                assertEquals(staff.getUserID(), Long.valueOf(i));
            }
        }
    }

    @Test
    void testFindStaffByAccount() {

    }

    @Test
    void testFindStaffByStaffID() {
        for (long i=0; i < 300; i++)
        {
            Optional<Staff> staffOptional = this.staffRepository.findStaffByStaffID(Long.valueOf(i));
            if (staffOptional.isPresent()){
                assertEquals(staffOptional.get().getStaffID(), Long.valueOf(i));
            }
        }
    }

    @Test
    void testFindStaffByStaffUUID() {

    }
}
