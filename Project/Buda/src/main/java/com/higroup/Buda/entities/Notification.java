package com.higroup.Buda.entities;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notification", indexes = {
    @Index(columnList = "user_id", name = "notification_user_id_index")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    
    @Override
    public String toString() {
        return "Notification [creationTime=" + creationTime + ", message=" + message + ", notificationID="
                + notificationID + ", userID=" + userID + "]";
    }
    
}
