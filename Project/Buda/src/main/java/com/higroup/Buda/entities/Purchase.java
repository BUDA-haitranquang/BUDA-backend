package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.Objects;

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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "planID", nullable = false)
    @JsonBackReference
    private Plan plan;
    private ZonedDateTime creationDate;
    private ZonedDateTime expiryDate;
    @Column(length = 1000)
    private String message;
    private double totalCost;

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

    public Purchase() {
    }

    public Purchase(Long purchaseID, User user, Plan plan, ZonedDateTime creationDate, ZonedDateTime expiryDate, String message) {
        this.purchaseID = purchaseID;
        this.user = user;
        this.plan = plan;
        this.creationDate = creationDate;
        this.expiryDate = expiryDate;
        this.message = message;
    }


    public Purchase(Long purchaseID, User user, Plan plan, ZonedDateTime creationDate, ZonedDateTime expiryDate, String message, double totalCost) {
        this.purchaseID = purchaseID;
        this.user = user;
        this.plan = plan;
        this.creationDate = creationDate;
        this.expiryDate = expiryDate;
        this.message = message;
        this.totalCost = totalCost;
    }

    public double getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(double totalCost) {
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

    public Purchase creationDate(ZonedDateTime creationDate) {
        setCreationDate(creationDate);
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

    public Purchase totalCost(double totalCost) {
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
        return Objects.equals(purchaseID, purchase.purchaseID) && Objects.equals(user, purchase.user) && Objects.equals(plan, purchase.plan) && Objects.equals(creationDate, purchase.creationDate) && Objects.equals(expiryDate, purchase.expiryDate) && Objects.equals(message, purchase.message) && totalCost == purchase.totalCost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseID, user, plan, creationDate, expiryDate, message, totalCost);
    }

    @Override
    public String toString() {
        return "{" +
            " purchaseID='" + getPurchaseID() + "'" +
            ", user='" + getUser() + "'" +
            ", plan='" + getPlan() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", expiryDate='" + getExpiryDate() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }
  
}
