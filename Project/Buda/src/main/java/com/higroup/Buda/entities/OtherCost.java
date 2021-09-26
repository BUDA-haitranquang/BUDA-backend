package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Other_cost")
public class OtherCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "other_cost_id")
    private Long otherCostID;
    @Column(name = "user_id")
    private Long userID;
    @Column(columnDefinition = "double default 0.0")
    private double totalCost;
    private ZonedDateTime paymentDate;
    @Column(length = 200)
    private String name;
    @Column(length = 2000)
    private String description;


    public OtherCost() {
    }

    public OtherCost(Long otherCostID, Long userID, double totalCost, ZonedDateTime paymentDate, String name, String description) {
        this.otherCostID = otherCostID;
        this.userID = userID;
        this.totalCost = totalCost;
        this.paymentDate = paymentDate;
        this.name = name;
        this.description = description;
    }

    public Long getOtherCostID() {
        return this.otherCostID;
    }

    public void setOtherCostID(Long otherCostID) {
        this.otherCostID = otherCostID;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public double getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public ZonedDateTime getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(ZonedDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OtherCost otherCostID(Long otherCostID) {
        setOtherCostID(otherCostID);
        return this;
    }

    public OtherCost userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public OtherCost totalCost(double totalCost) {
        setTotalCost(totalCost);
        return this;
    }

    public OtherCost paymentDate(ZonedDateTime paymentDate) {
        setPaymentDate(paymentDate);
        return this;
    }

    public OtherCost name(String name) {
        setName(name);
        return this;
    }

    public OtherCost description(String description) {
        setDescription(description);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OtherCost)) {
            return false;
        }
        OtherCost otherCost = (OtherCost) o;
        return Objects.equals(otherCostID, otherCost.otherCostID) && Objects.equals(userID, otherCost.userID) && totalCost == otherCost.totalCost && Objects.equals(paymentDate, otherCost.paymentDate) && Objects.equals(name, otherCost.name) && Objects.equals(description, otherCost.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(otherCostID, userID, totalCost, paymentDate, name, description);
    }

    @Override
    public String toString() {
        return "{" +
            " otherCostID='" + getOtherCostID() + "'" +
            ", userID='" + getUserID() + "'" +
            ", totalCost='" + getTotalCost() + "'" +
            ", paymentDate='" + getPaymentDate() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
    
}
