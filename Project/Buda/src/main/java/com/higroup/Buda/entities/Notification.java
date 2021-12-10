package com.higroup.Buda.entities;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "notification", indexes = {
    @Index(columnList = "user_id", name = "notification_user_id_index")
})
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationID;
    @Column(name = "user_id")
    private Long userID;
    @Column(name = "creation_time")
    private ZonedDateTime creationTime;
    @Column(name = "message")
    private String message;
    @Column(name = "seen")
    private Boolean seen = Boolean.FALSE;
    public Boolean getSeen() {
        return seen;
    }
    public void setSeen(Boolean seen) {
        this.seen = seen;
    }
    public Notification() {
    }
    public Notification(Long notificationID, Long userID, ZonedDateTime creationTime, String message) {
        this.notificationID = notificationID;
        this.userID = userID;
        this.creationTime = creationTime;
        this.message = message;
    }
    public Notification(Long notificationID, Long userID, ZonedDateTime creationTime, String message, Boolean seen) {
        this.notificationID = notificationID;
        this.userID = userID;
        this.creationTime = creationTime;
        this.message = message;
        this.seen = seen;
    }
    @Override
    public String toString() {
        return "Notification [creationTime=" + creationTime + ", message=" + message + ", notificationID="
                + notificationID + ", userID=" + userID + "]";
    }
    public Long getNotificationID() {
        return notificationID;
    }
    public void setNotificationID(Long notificationID) {
        this.notificationID = notificationID;
    }
    public Long getUserID() {
        return userID;
    }
    public void setUserID(Long userID) {
        this.userID = userID;
    }
    public ZonedDateTime getCreationTime() {
        return creationTime;
    }
    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
}
