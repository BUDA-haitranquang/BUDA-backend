package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Fixed_cost_bill", indexes = {
    @Index(columnList = "user_id", name = "fixed_cost_bill_user_id_index"),
    @Index(columnList = "fixed_cost_id", name = "fixed_cost_bill_fixed_cost_id_index")
})
public class FixedCostBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fixed_cost_bill_id")
    private Long fixedCostBillID;
    @ManyToOne
    @JoinColumn(name = "fixed_Cost_ID", nullable = true)
    @JsonBackReference(value = "fixed_cost - fixed_cost_bill")
    private FixedCost fixedCost;
    @Column(name = "user_id")
    private Long userID;
    private Double totalSpend;
    @Column(length = 1000)
    private String message;
    private ZonedDateTime creationTime;
    private ZonedDateTime dueTime;
    @Enumerated(EnumType.STRING)
    private Status status = Status.PREPARING;


    public FixedCostBill() {
    }

    public FixedCostBill(Long fixedCostBillID, FixedCost fixedCost, Long userID, Double totalSpend, String message, ZonedDateTime creationTime, ZonedDateTime dueTime, Status status) {
        this.fixedCostBillID = fixedCostBillID;
        this.fixedCost = fixedCost;
        this.userID = userID;
        this.totalSpend = totalSpend;
        this.message = message;
        this.creationTime = creationTime;
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

    public Double getTotalSpend() {
        return this.totalSpend;
    }

    public void setTotalSpend(Double totalSpend) {
        this.totalSpend = totalSpend;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZonedDateTime getCreationTime() {
        return this.creationTime;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
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

    public FixedCostBill totalSpend(Double totalSpend) {
        setTotalSpend(totalSpend);
        return this;
    }

    public FixedCostBill message(String message) {
        setMessage(message);
        return this;
    }

    public FixedCostBill creationTime(ZonedDateTime creationTime) {
        setCreationTime(creationTime);
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
        return Objects.equals(fixedCostBillID, fixedCostBill.fixedCostBillID)
//                && Objects.equals(fixedCost, fixedCostBill.fixedCost)
                && Objects.equals(userID, fixedCostBill.userID)
                && totalSpend == fixedCostBill.totalSpend
                && Objects.equals(message, fixedCostBill.message)
                && Objects.equals(creationTime, fixedCostBill.creationTime)
                && Objects.equals(dueTime, fixedCostBill.dueTime)
                && Objects.equals(status, fixedCostBill.status);
    }

    @Override
    public int hashCode() {
//        return Objects.hash(fixedCostBillID, fixedCost, userID, totalSpend, message, creationTime, dueTime, status);
        return 1;
    }

    @Override
    public String toString() {
        return "{" +
            " fixedCostBillID='" + getFixedCostBillID() + "'" +
//            ", fixedCost='" + getFixedCost() + "'" +
            ", userID='" + getUserID() + "'" +
            ", totalSpend='" + getTotalSpend() + "'" +
            ", message='" + getMessage() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", dueTime='" + getDueTime() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
   
}
