package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import java.io.Serializable;

import javax.persistence.CascadeType;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Sell_order", indexes = {
    @Index(columnList = "user_id", name = "sell_order_user_id_index"),
    @Index(columnList = "customer_id", name = "sell_order_customer_id_index"),
    @Index(columnList = "discount_id", name = "sell_order_discount_id_index")
})
//@JsonIgnoreProperties("sellOrderItems")

public class SellOrder implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sell_order_id")
    private Long sellOrderID;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "customer_ID", nullable = true)
    @JsonBackReference(value = "customer - sell_order")
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "discount_ID", nullable = true)
    @JsonBackReference(value = "discount - sell_order")
    private Discount discount;
    private ZonedDateTime creationTime;
    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(columnDefinition = "Double default 0.0")
    private Double actualDiscountCash;
    @Column(columnDefinition = "Double default 0.0")
    private Double actualDiscountPercentage;
    private Double realCost;
    private Double finalCost;
    @Column(name = "user_id")
    private Long userID;
    @Column(length = 1000)
    private String customerMessage;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "sellOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "sell_order - sell_order_item")
    private Set<SellOrderItem> sellOrderItems;



    public SellOrder() {
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

    public Double getActualDiscountCash() {
        return this.actualDiscountCash;
    }

    public void setActualDiscountCash(Double actualDiscountCash) {
        this.actualDiscountCash = actualDiscountCash;
    }

    public Double getActualDiscountPercentage() {
        return this.actualDiscountPercentage;
    }

    public void setActualDiscountPercentage(Double actualDiscountPercentage) {
        this.actualDiscountPercentage = actualDiscountPercentage;
    }

    public Double getRealCost() {
        return this.realCost;
    }

    public void setRealCost(Double realCost) {
        this.realCost = realCost;
    }

    public Double getFinalCost() {
        return this.finalCost;
    }

    public void setFinalCost(Double finalCost) {
        this.finalCost = finalCost;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getCustomerMessage() {
        return this.customerMessage;
    }

    public void setCustomerMessage(String customerMessage) {
        this.customerMessage = customerMessage;
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

    public SellOrder actualDiscountCash(Double actualDiscountCash) {
        setActualDiscountCash(actualDiscountCash);
        return this;
    }

    public SellOrder actualDiscountPercentage(Double actualDiscountPercentage) {
        setActualDiscountPercentage(actualDiscountPercentage);
        return this;
    }

    public SellOrder realCost(Double realCost) {
        setRealCost(realCost);
        return this;
    }

    public SellOrder finalCost(Double finalCost) {
        setFinalCost(finalCost);
        return this;
    }

    public SellOrder userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public SellOrder customerMessage(String customerMessage) {
        setCustomerMessage(customerMessage);
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
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SellOrder other = (SellOrder) obj;
        if (Double.doubleToLongBits(actualDiscountCash) != Double.doubleToLongBits(other.actualDiscountCash))
            return false;
        if (Double.doubleToLongBits(actualDiscountPercentage) != Double
                .doubleToLongBits(other.actualDiscountPercentage))
            return false;
        if (ageGroup != other.ageGroup)
            return false;
        if (creationTime == null) {
            if (other.creationTime != null)
                return false;
        } else if (!creationTime.equals(other.creationTime))
            return false;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (customerMessage == null) {
            if (other.customerMessage != null)
                return false;
        } else if (!customerMessage.equals(other.customerMessage))
            return false;
        if (discount == null) {
            if (other.discount != null)
                return false;
        } else if (!discount.equals(other.discount))
            return false;
        if (Double.doubleToLongBits(finalCost) != Double.doubleToLongBits(other.finalCost))
            return false;
        if (gender != other.gender)
            return false;
        if (Double.doubleToLongBits(realCost) != Double.doubleToLongBits(other.realCost))
            return false;
        if (sellOrderID == null) {
            if (other.sellOrderID != null)
                return false;
        } else if (!sellOrderID.equals(other.sellOrderID))
            return false;
        if (sellOrderItems == null) {
            if (other.sellOrderItems != null)
                return false;
        } else if (!sellOrderItems.equals(other.sellOrderItems))
            return false;
        if (status != other.status)
            return false;
        if (userID == null) {
            if (other.userID != null)
                return false;
        } else if (!userID.equals(other.userID))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(actualDiscountCash);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(actualDiscountPercentage);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((ageGroup == null) ? 0 : ageGroup.hashCode());
        result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((customerMessage == null) ? 0 : customerMessage.hashCode());
        result = prime * result + ((discount == null) ? 0 : discount.hashCode());
        temp = Double.doubleToLongBits(finalCost);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        temp = Double.doubleToLongBits(realCost);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((sellOrderID == null) ? 0 : sellOrderID.hashCode());
        result = prime * result + ((sellOrderItems == null) ? 0 : sellOrderItems.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((userID == null) ? 0 : userID.hashCode());
        return result;
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
            ", actualDiscountCash='" + getActualDiscountCash() + "'" +
            ", actualDiscountPercentage='" + getActualDiscountPercentage() + "'" +
            ", realCost='" + getRealCost() + "'" +
            ", finalCost='" + getFinalCost() + "'" +
            ", userID='" + getUserID() + "'" +
            ", customerMessage='" + getCustomerMessage() + "'" +
            ", status='" + getStatus() + "'" +
            ", sellOrderItems='" + getSellOrderItems() + "'" +
            "}";
    }
    
}
