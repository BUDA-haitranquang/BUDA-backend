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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discountID;
    @Column(length = 200)
    private String name;
    @Column(length = 1000)
    private String description;
    private double cash;
    private double percentage;
    private double cashLimit;
    private int orderCount;
    private ZonedDateTime expiryTime;
    private ZonedDateTime createdTime;
    private Long userID;
    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<SellOrder> sellOrders;
    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<MembershipType> membershipTypes;

    public Discount(Long discountID, String name, String description, double cash, double percentage, double cashLimit, int orderCount, ZonedDateTime expiryTime, ZonedDateTime createdTime, Long userID, Set<SellOrder> sellOrders, Set<MembershipType> membershipTypes) {
        this.discountID = discountID;
        this.name = name;
        this.description = description;
        this.cash = cash;
        this.percentage = percentage;
        this.cashLimit = cashLimit;
        this.orderCount = orderCount;
        this.expiryTime = expiryTime;
        this.createdTime = createdTime;
        this.userID = userID;
        this.sellOrders = sellOrders;
        this.membershipTypes = membershipTypes;
    }

    public Set<MembershipType> getMembershipTypes() {
        return this.membershipTypes;
    }

    public void setMembershipTypes(Set<MembershipType> membershipTypes) {
        this.membershipTypes = membershipTypes;
    }

    public Discount membershipTypes(Set<MembershipType> membershipTypes) {
        setMembershipTypes(membershipTypes);
        return this;
    }
    public Discount() {
    }

    public Discount(Long discountID, String name, String description, double cash, double percentage, double cashLimit, int orderCount, ZonedDateTime expiryTime, ZonedDateTime createdTime, Long userID, Set<SellOrder> sellOrders) {
        this.discountID = discountID;
        this.name = name;
        this.description = description;
        this.cash = cash;
        this.percentage = percentage;
        this.cashLimit = cashLimit;
        this.orderCount = orderCount;
        this.expiryTime = expiryTime;
        this.createdTime = createdTime;
        this.userID = userID;
        this.sellOrders = sellOrders;
    }

    public Long getDiscountID() {
        return this.discountID;
    }

    public void setDiscountID(Long discountID) {
        this.discountID = discountID;
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

    public double getCash() {
        return this.cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getPercentage() {
        return this.percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public double getCashLimit() {
        return this.cashLimit;
    }

    public void setCashLimit(double cashLimit) {
        this.cashLimit = cashLimit;
    }

    public int getOrderCount() {
        return this.orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public ZonedDateTime getExpiryTime() {
        return this.expiryTime;
    }

    public void setExpiryTime(ZonedDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    public ZonedDateTime getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(ZonedDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Set<SellOrder> getSellOrders() {
        return this.sellOrders;
    }

    public void setSellOrders(Set<SellOrder> sellOrders) {
        this.sellOrders = sellOrders;
    }

    public Discount discountID(Long discountID) {
        setDiscountID(discountID);
        return this;
    }

    public Discount name(String name) {
        setName(name);
        return this;
    }

    public Discount description(String description) {
        setDescription(description);
        return this;
    }

    public Discount cash(double cash) {
        setCash(cash);
        return this;
    }

    public Discount percentage(double percentage) {
        setPercentage(percentage);
        return this;
    }

    public Discount cashLimit(double cashLimit) {
        setCashLimit(cashLimit);
        return this;
    }

    public Discount orderCount(int orderCount) {
        setOrderCount(orderCount);
        return this;
    }

    public Discount expiryTime(ZonedDateTime expiryTime) {
        setExpiryTime(expiryTime);
        return this;
    }

    public Discount createdTime(ZonedDateTime createdTime) {
        setCreatedTime(createdTime);
        return this;
    }

    public Discount userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public Discount sellOrders(Set<SellOrder> sellOrders) {
        setSellOrders(sellOrders);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Discount)) {
            return false;
        }
        Discount discount = (Discount) o;
        return Objects.equals(discountID, discount.discountID) && Objects.equals(name, discount.name) && Objects.equals(description, discount.description) && cash == discount.cash && percentage == discount.percentage && cashLimit == discount.cashLimit && orderCount == discount.orderCount && Objects.equals(expiryTime, discount.expiryTime) && Objects.equals(createdTime, discount.createdTime) && Objects.equals(userID, discount.userID) && Objects.equals(sellOrders, discount.sellOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountID, name, description, cash, percentage, cashLimit, orderCount, expiryTime, createdTime, userID, sellOrders);
    }

    @Override
    public String toString() {
        return "{" +
            " discountID='" + getDiscountID() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", cash='" + getCash() + "'" +
            ", percentage='" + getPercentage() + "'" +
            ", cashLimit='" + getCashLimit() + "'" +
            ", orderCount='" + getOrderCount() + "'" +
            ", expiryTime='" + getExpiryTime() + "'" +
            ", createdTime='" + getCreatedTime() + "'" +
            ", userID='" + getUserID() + "'" +
            ", sellOrders='" + getSellOrders() + "'" +
            "}";
    }

}
