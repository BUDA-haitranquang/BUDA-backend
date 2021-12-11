package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.checkerframework.checker.units.qual.C;

@Entity
@Table(name = "Purchase", indexes = {
    @Index(columnList = "user_id", name = "purchase_user_id_index"),
    @Index(columnList = "plan_id", name = "purchase_plan_id_index")
})
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Long purchaseID;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    @JsonBackReference
    private Plan plan;
    private ZonedDateTime creationTime;
    private ZonedDateTime expiryDate;
    @Column(length = 1000)
    private String message;
    @Column(columnDefinition = "Double default 0.0")
    private Double totalCost;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.PREPARING;
    

    public Purchase(Long purchaseID, User user, Plan plan, ZonedDateTime creationTime, ZonedDateTime expiryDate,
            String message, Double totalCost, Status status) {
        this.purchaseID = purchaseID;
        this.user = user;
        this.plan = plan;
        this.creationTime = creationTime;
        this.expiryDate = expiryDate;
        this.message = message;
        this.totalCost = totalCost;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Purchase() {
    }

    public Purchase(Long purchaseID, User user, Plan plan, ZonedDateTime creationTime, ZonedDateTime expiryDate, String message, Double totalCost) {
        this.purchaseID = purchaseID;
        this.user = user;
        this.plan = plan;
        this.creationTime = creationTime;
        this.expiryDate = expiryDate;
        this.message = message;
        this.totalCost = totalCost;
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

    public Plan getPlan() {
        return this.plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public ZonedDateTime getCreationTime() {
        return this.creationTime;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
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

    public Double getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Purchase purchaseID(Long purchaseID) {
        setPurchaseID(purchaseID);
        return this;
    }

    public Purchase user(User user) {
        setUser(user);
        return this;
    }

    public Purchase plan(Plan plan) {
        setPlan(plan);
        return this;
    }

    public Purchase creationTime(ZonedDateTime creationTime) {
        setCreationTime(creationTime);
        return this;
    }

    public Purchase expiryDate(ZonedDateTime expiryDate) {
        setExpiryDate(expiryDate);
        return this;
    }

    public Purchase message(String message) {
        setMessage(message);
        return this;
    }

    public Purchase totalCost(Double totalCost) {
        setTotalCost(totalCost);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Purchase)) {
            return false;
        }
        Purchase purchase = (Purchase) o;
        return Objects.equals(purchaseID, purchase.purchaseID) && Objects.equals(user, purchase.user) && Objects.equals(plan, purchase.plan) && Objects.equals(creationTime, purchase.creationTime) && Objects.equals(expiryDate, purchase.expiryDate) && Objects.equals(message, purchase.message) && totalCost == purchase.totalCost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseID);
    }

    @Override
    public String toString() {
        return "{" +
            " purchaseID='" + getPurchaseID() + "'" +
            ", user='" + getUser() + "'" +
            ", plan='" + getPlan() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", expiryDate='" + getExpiryDate() + "'" +
            ", message='" + getMessage() + "'" +
            ", totalCost='" + getTotalCost() + "'" +
            "}";
    }
    
}
