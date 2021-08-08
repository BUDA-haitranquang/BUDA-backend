package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Sell_order")
public class SellOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellOrderID;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "customerID", nullable = true)
    @JsonBackReference
    private Customer customer;
    private ZonedDateTime creationTime;
    private AgeGroup ageGroup;
    private Gender gender;
    private double realCost;
    private double finalCost;
    private Long userID;
    private String message;
    private Status status;
    private Long discountID;
    @OneToMany(mappedBy = "sellOrder", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<SellOrderItem> sellOrderItems;

    public SellOrder() {
    }

    public SellOrder(Long sellOrderID, Customer customer, ZonedDateTime creationTime, AgeGroup ageGroup, Gender gender, double realCost, double finalCost, Long userID, String message, Status status, Long discountID, Set<SellOrderItem> sellOrderItems) {
        this.sellOrderID = sellOrderID;
        this.customer = customer;
        this.creationTime = creationTime;
        this.ageGroup = ageGroup;
        this.gender = gender;
        this.realCost = realCost;
        this.finalCost = finalCost;
        this.userID = userID;
        this.message = message;
        this.status = status;
        this.discountID = discountID;
        this.sellOrderItems = sellOrderItems;
    }

    public Set<SellOrderItem> getSellOrderItems() {
        return this.sellOrderItems;
    }

    public void setSellOrderItems(Set<SellOrderItem> sellOrderItems) {
        this.sellOrderItems = sellOrderItems;
    }

    public SellOrder sellOrderItems(Set<SellOrderItem> sellOrderItems) {
        setSellOrderItems(sellOrderItems);
        return this;
    }


    public Long getSellOrderID() {
        return this.sellOrderID;
    }

    public void setSellOrderID(Long sellOrderID) {
        this.sellOrderID = sellOrderID;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ZonedDateTime getCreationTime() {
        return this.creationTime;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public AgeGroup getAgeGroup() {
        return this.ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getRealCost() {
        return this.realCost;
    }

    public void setRealCost(double realCost) {
        this.realCost = realCost;
    }

    public double getFinalCost() {
        return this.finalCost;
    }

    public void setFinalCost(double finalCost) {
        this.finalCost = finalCost;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getDiscountID() {
        return this.discountID;
    }

    public void setDiscountID(Long discountID) {
        this.discountID = discountID;
    }

    public SellOrder sellOrderID(Long sellOrderID) {
        setSellOrderID(sellOrderID);
        return this;
    }

    public SellOrder customer(Customer customer) {
        setCustomer(customer);
        return this;
    }

    public SellOrder creationTime(ZonedDateTime creationTime) {
        setCreationTime(creationTime);
        return this;
    }

    public SellOrder ageGroup(AgeGroup ageGroup) {
        setAgeGroup(ageGroup);
        return this;
    }

    public SellOrder gender(Gender gender) {
        setGender(gender);
        return this;
    }

    public SellOrder realCost(double realCost) {
        setRealCost(realCost);
        return this;
    }

    public SellOrder finalCost(double finalCost) {
        setFinalCost(finalCost);
        return this;
    }

    public SellOrder userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public SellOrder message(String message) {
        setMessage(message);
        return this;
    }

    public SellOrder status(Status status) {
        setStatus(status);
        return this;
    }

    public SellOrder discountID(Long discountID) {
        setDiscountID(discountID);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SellOrder)) {
            return false;
        }
        SellOrder sellOrder = (SellOrder) o;
        return Objects.equals(sellOrderID, sellOrder.sellOrderID) && Objects.equals(customer, sellOrder.customer) && Objects.equals(creationTime, sellOrder.creationTime) && Objects.equals(ageGroup, sellOrder.ageGroup) && Objects.equals(gender, sellOrder.gender) && realCost == sellOrder.realCost && finalCost == sellOrder.finalCost && Objects.equals(userID, sellOrder.userID) && Objects.equals(message, sellOrder.message) && Objects.equals(status, sellOrder.status) && Objects.equals(discountID, sellOrder.discountID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sellOrderID, customer, creationTime, ageGroup, gender, realCost, finalCost, userID, message, status, discountID);
    }

    @Override
    public String toString() {
        return "{" +
            " sellOrderID='" + getSellOrderID() + "'" +
            ", customer='" + getCustomer() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", ageGroup='" + getAgeGroup() + "'" +
            ", gender='" + getGender() + "'" +
            ", realCost='" + getRealCost() + "'" +
            ", finalCost='" + getFinalCost() + "'" +
            ", userID='" + getUserID() + "'" +
            ", message='" + getMessage() + "'" +
            ", status='" + getStatus() + "'" +
            ", discountID='" + getDiscountID() + "'" +
            "}";
    }
    
}
