package com.higroup.Buda.entities;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchaseID")
    private Long purchaseID;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userID", nullable = false)
    @JsonBackReference
    private User user;
    private Long planID;
    private ZonedDateTime creationDate;
    private ZonedDateTime expiryDate;
    private String message;

    public Purchase() {
    }

    public Purchase(Long purchaseID, User user, Long planID, ZonedDateTime creationDate, ZonedDateTime expiryDate, String message) {
        this.purchaseID = purchaseID;
        this.user = user;
        this.planID = planID;
        this.creationDate = creationDate;
        this.expiryDate = expiryDate;
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
            " purchaseID='" + getPurchaseID() + "'" +
            ", user='" + getUser() + "'" +
            ", planID='" + getPlanID() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", expiryDate='" + getExpiryDate() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }

    public Long getPurchaseID() {
        return this.purchaseID;
    }

    public void setPurchaseID(Long purchaseID) {
        this.purchaseID = purchaseID;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getPlanID() {
        return this.planID;
    }

    public void setPlanID(Long planID) {
        this.planID = planID;
    }

    public ZonedDateTime getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public ZonedDateTime getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(ZonedDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
