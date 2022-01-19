package com.higroup.Buda.api.staff.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.Staff;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class StaffServiceTest {
    @Autowired
    private StaffService staffService;
    @Test
    void testCorrectLogin() {

    }

    @Test
    void testDeleteStaffByID() {

    }

    @Test
    void testFindAllByUserID() {
        List<Staff> staffs = this.staffService.findAllByUserID(2l);
        for (Staff staff: staffs){
            assertEquals(staff.getUserID(), 2l);
        }
    }

    @Test
    void testFindStaffByID() {

    }

    @Test
    void testLoadUserByUsername() {

    }

    @Test
    void testRegisterNewStaff() {

    }

    @Test
    void testUpdateStaff() {

    }

    @Test
    void testUpdateStaffByID() {

    }
}
