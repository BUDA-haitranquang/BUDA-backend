package com.higroup.Buda.api.staff.notification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.StaffNote;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class StaffNotificationServiceTest {
    @Autowired
    StaffNotificationService staffNotificationService;
    @Test
    void testDeleteStaffNotebyID() {

    }

    @Test
    void testFindAllByStaffID() {

    }

    @Test
    void testFindAllByUserID() {
        for (long userID = 0; userID < 3; userID++)
        {
            List<StaffNote> staffNotes = this.staffNotificationService.findAllByUserID(Long.valueOf(userID));
            for (StaffNote staffNote: staffNotes){
                assertEquals(staffNote.getUserID(), Long.valueOf(userID));
            }
        }
    }

    @Test
    void testFindAllUnseenByStaffID() {

    }

    @Test
    void testFindStaffNotebyID() {

    }

    @Test
    void testRegisterNewStaffNote() {

    }

    @Test
    void testUpdateStaffNotebyID() {

    }
}
