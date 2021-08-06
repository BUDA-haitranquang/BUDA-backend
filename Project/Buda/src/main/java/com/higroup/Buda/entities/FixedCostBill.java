package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Fixed_cost_bill")
public class FixedCostBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fixedCostBillID;
    @ManyToOne
    @JoinColumn(name = "fixedCostID", nullable = true)
    @JsonBackReference
    private FixedCost fixedCost;
    private Long userID;
    private double totalSpend;
    @Column(length = 500)
    private String message;
    private ZonedDateTime dueTime;
    private Status status;

    public FixedCostBill() {
    }

    public FixedCostBill(Long fixedCostBillID, FixedCost fixedCost, Long userID, double totalSpend, String message, ZonedDateTime dueTime, Status status) {
        this.fixedCostBillID = fixedCostBillID;
        this.fixedCost = fixedCost;
        this.userID = userID;
        this.totalSpend = totalSpend;
        this.message = message;
        this.dueTime = dueTime;
        this.status = status;
    }

    public Long getFixedCostBillID() {
        return this.fixedCostBillID;
    }

    public void setFixedCostBillID(Long fixedCostBillID) {
        this.fixedCostBillID = fixedCostBillID;
    }

    public FixedCost getFixedCost() {
        return this.fixedCost;
    }

    public void setFixedCost(FixedCost fixedCost) {
        this.fixedCost = fixedCost;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public double getTotalSpend() {
        return this.totalSpend;
    }

    public void setTotalSpend(double totalSpend) {
        this.totalSpend = totalSpend;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZonedDateTime getDueTime() {
        return this.dueTime;
    }

    public void setDueTime(ZonedDateTime dueTime) {
        this.dueTime = dueTime;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public FixedCostBill fixedCostBillID(Long fixedCostBillID) {
        setFixedCostBillID(fixedCostBillID);
        return this;
    }

    public FixedCostBill fixedCost(FixedCost fixedCost) {
        setFixedCost(fixedCost);
        return this;
    }

    public FixedCostBill userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public FixedCostBill totalSpend(double totalSpend) {
        setTotalSpend(totalSpend);
        return this;
    }

    public FixedCostBill message(String message) {
        setMessage(message);
        return this;
    }

    public FixedCostBill dueTime(ZonedDateTime dueTime) {
        setDueTime(dueTime);
        return this;
    }

    public FixedCostBill status(Status status) {
        setStatus(status);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FixedCostBill)) {
            return false;
        }
        FixedCostBill fixedCostBill = (FixedCostBill) o;
        return Objects.equals(fixedCostBillID, fixedCostBill.fixedCostBillID) && Objects.equals(fixedCost, fixedCostBill.fixedCost) && Objects.equals(userID, fixedCostBill.userID) && totalSpend == fixedCostBill.totalSpend && Objects.equals(message, fixedCostBill.message) && Objects.equals(dueTime, fixedCostBill.dueTime) && Objects.equals(status, fixedCostBill.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fixedCostBillID, fixedCost, userID, totalSpend, message, dueTime, status);
    }

    @Override
    public String toString() {
        return "{" +
            " fixedCostBillID='" + getFixedCostBillID() + "'" +
            ", fixedCost='" + getFixedCost() + "'" +
            ", userID='" + getUserID() + "'" +
            ", totalSpend='" + getTotalSpend() + "'" +
            ", message='" + getMessage() + "'" +
            ", dueTime='" + getDueTime() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }

}
