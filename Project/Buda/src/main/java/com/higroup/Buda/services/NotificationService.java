package com.higroup.Buda.services;

import java.util.List;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Notification;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.NotificationRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository)
    {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    public List<Notification> findAllPendingNotificationByUserID(Long userID)
    {
        this.notificationRepository.seenAllNotification(userID);
        return this.notificationRepository.findPendingNotificationByUserID(userID);
    }
    public List<Notification> findAllNotificationByUserID(Long userID)
    {
        return this.notificationRepository.findAllNotificationByUserID(userID);
    }
}
