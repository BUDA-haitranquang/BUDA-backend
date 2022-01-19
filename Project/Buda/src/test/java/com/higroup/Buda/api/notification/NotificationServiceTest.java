package com.higroup.Buda.api.notification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.Notification;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class NotificationServiceTest {
    @Autowired
    private NotificationService notificationService;
    @Test
    @Transactional
    void testFindAllNotificationByUserID() {
        List<Notification> notifications = this.notificationService.findAllNotificationByUserID(2l);
        for (Notification notification: notifications){
            assertEquals(notification.getUserID(), 2l);
        }
    }

    @Test
    void testFindAllPendingNotificationByUserID() {
        List<Notification> notifications = this.notificationService.findAllNotificationByUserID(2l);
        for (Notification notification: notifications){
            assertEquals(notification.getSeen(), Boolean.TRUE);
            assertEquals(notification.getUserID(), 2l);
        }
    }
}
