package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
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
    @Column(name = "sell_order_id")
    private Long sellOrderID;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "customer_ID", nullable = true)
    @JsonBackReference
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "discount_ID", nullable = true)
    @JsonBackReference
    private Discount discount;
    private ZonedDateTime creationTime;
    private AgeGroup ageGroup;
    private Gender gender;
    private double realCost;
    private double finalCost;
    @Column(name = "user_id")
    private Long userID;
    @Column(length = 1000)
    private String customerMessage;
    private Status status;
    @OneToMany(mappedBy = "sellOrder", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<SellOrderItem> sellOrderItems;

    public SellOrder() {
    }

    public SellOrder(Long sellOrderID, Customer customer, Discount discount, ZonedDateTime creationTime, AgeGroup ageGroup, Gender gender, double realCost, double finalCost, Long userID, String message, Status status, Set<SellOrderItem> sellOrderItems) {
        this.sellOrderID = sellOrderID;
        this.customer = customer;
        this.discount = discount;
        this.creationTime = creationTime;
        this.ageGroup = ageGroup;
        this.gender = gender;
        this.realCost = realCost;
        this.finalCost = finalCost;
        this.userID = userID;
        this.customerMessage = message;
        this.status = status;
        this.sellOrderItems = sellOrderItems;
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

    public Discount getDiscount() {
        return this.discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
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
        return this.customerMessage;
    }

    public void setMessage(String message) {
        this.customerMessage = message;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<SellOrderItem> getSellOrderItems() {
        return this.sellOrderItems;
    }

    public void setSellOrderItems(Set<SellOrderItem> sellOrderItems) {
        this.sellOrderItems = sellOrderItems;
    }

    public SellOrder sellOrderID(Long sellOrderID) {
        setSellOrderID(sellOrderID);
        return this;
    }

    public SellOrder customer(Customer customer) {
        setCustomer(customer);
        return this;
    }

    public SellOrder discount(Discount discount) {
        setDiscount(discount);
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

    public SellOrder sellOrderItems(Set<SellOrderItem> sellOrderItems) {
        setSellOrderItems(sellOrderItems);
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
        return Objects.equals(sellOrderID, sellOrder.sellOrderID) && Objects.equals(customer, sellOrder.customer) && Objects.equals(discount, sellOrder.discount) && Objects.equals(creationTime, sellOrder.creationTime) && Objects.equals(ageGroup, sellOrder.ageGroup) && Objects.equals(gender, sellOrder.gender) && realCost == sellOrder.realCost && finalCost == sellOrder.finalCost && Objects.equals(userID, sellOrder.userID) && Objects.equals(customerMessage, sellOrder.customerMessage) && Objects.equals(status, sellOrder.status) && Objects.equals(sellOrderItems, sellOrder.sellOrderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sellOrderID, customer, discount, creationTime, ageGroup, gender, realCost, finalCost, userID, customerMessage, status, sellOrderItems);
    }

    @Override
    public String toString() {
        return "{" +
            " sellOrderID='" + getSellOrderID() + "'" +
            ", customer='" + getCustomer() + "'" +
            ", discount='" + getDiscount() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", ageGroup='" + getAgeGroup() + "'" +
            ", gender='" + getGender() + "'" +
            ", realCost='" + getRealCost() + "'" +
            ", finalCost='" + getFinalCost() + "'" +
            ", userID='" + getUserID() + "'" +
            ", message='" + getMessage() + "'" +
            ", status='" + getStatus() + "'" +
            ", sellOrderItems='" + getSellOrderItems() + "'" +
            "}";
    }

}
