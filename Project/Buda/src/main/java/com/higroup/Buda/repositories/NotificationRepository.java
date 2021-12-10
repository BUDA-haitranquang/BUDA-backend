package com.higroup.Buda.repositories;

import java.util.List;

import com.higroup.Buda.entities.Notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query(value = "select n from Notification n where n.userID = :userID and n.seen = FALSE")
    List<Notification> findPendingNotificationByUserID(@Param("userID") Long userID);
    @Query(value = "select n from Notification n where n.userID = :userID")
    List<Notification> findAllNotificationByUserID(@Param("userID") Long userID);
}
