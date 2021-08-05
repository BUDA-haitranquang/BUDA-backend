package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.Objects;

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
@Table(name = "Sell_order_item")
public class SellOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellOrderItemID;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JsonBackReference
    @JoinColumn(name = "sellOrderID", nullable = true)
    private SellOrder sellOrder;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JsonBackReference
    @JoinColumn(name = "productID", nullable = true)
    private Product product;
    private int quantity;
    private double pricePerUnit;
    private Long userID;
    private ZonedDateTime creationTime;
    private Gender gender;
    private AgeGroup ageGroup;
    private double actualTotalSale;
    private double costPerUnit;

    public SellOrderItem() {
    }

    public SellOrderItem(SellOrder sellOrder, Product product, int quantity, double pricePerUnit, Long userID, ZonedDateTime creationTime, Gender gender, AgeGroup ageGroup, double actualTotalSale, double costPerUnit) {
        this.sellOrder = sellOrder;
        this.product = product;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.userID = userID;
        this.creationTime = creationTime;
        this.gender = gender;
        this.ageGroup = ageGroup;
        this.actualTotalSale = actualTotalSale;
        this.costPerUnit = costPerUnit;
    }

    public SellOrder getSellOrder() {
        return this.sellOrder;
    }

    public void setSellOrder(SellOrder sellOrder) {
        this.sellOrder = sellOrder;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerUnit() {
        return this.pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public ZonedDateTime getCreationTime() {
        return this.creationTime;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public AgeGroup getAgeGroup() {
        return this.ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public double getActualTotalSale() {
        return this.actualTotalSale;
    }

    public void setActualTotalSale(double actualTotalSale) {
        this.actualTotalSale = actualTotalSale;
    }

    public double getCostPerUnit() {
        return this.costPerUnit;
    }

    public void setCostPerUnit(double costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public SellOrderItem sellOrder(SellOrder sellOrder) {
        setSellOrder(sellOrder);
        return this;
    }

    public SellOrderItem product(Product product) {
        setProduct(product);
        return this;
    }

    public SellOrderItem quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    public SellOrderItem pricePerUnit(double pricePerUnit) {
        setPricePerUnit(pricePerUnit);
        return this;
    }

    public SellOrderItem userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public SellOrderItem creationTime(ZonedDateTime creationTime) {
        setCreationTime(creationTime);
        return this;
    }

    public SellOrderItem gender(Gender gender) {
        setGender(gender);
        return this;
    }

    public SellOrderItem ageGroup(AgeGroup ageGroup) {
        setAgeGroup(ageGroup);
        return this;
    }

    public SellOrderItem actualTotalSale(double actualTotalSale) {
        setActualTotalSale(actualTotalSale);
        return this;
    }

    public SellOrderItem costPerUnit(double costPerUnit) {
        setCostPerUnit(costPerUnit);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SellOrderItem)) {
            return false;
        }
        SellOrderItem sellOrderItem = (SellOrderItem) o;
        return Objects.equals(sellOrder, sellOrderItem.sellOrder) && Objects.equals(product, sellOrderItem.product) && quantity == sellOrderItem.quantity && pricePerUnit == sellOrderItem.pricePerUnit && Objects.equals(userID, sellOrderItem.userID) && Objects.equals(creationTime, sellOrderItem.creationTime) && Objects.equals(gender, sellOrderItem.gender) && Objects.equals(ageGroup, sellOrderItem.ageGroup) && actualTotalSale == sellOrderItem.actualTotalSale && costPerUnit == sellOrderItem.costPerUnit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sellOrder, product, quantity, pricePerUnit, userID, creationTime, gender, ageGroup, actualTotalSale, costPerUnit);
    }

    @Override
    public String toString() {
        return "{" +
            " sellOrder='" + getSellOrder() + "'" +
            ", product='" + getProduct() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", pricePerUnit='" + getPricePerUnit() + "'" +
            ", userID='" + getUserID() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", gender='" + getGender() + "'" +
            ", ageGroup='" + getAgeGroup() + "'" +
            ", actualTotalSale='" + getActualTotalSale() + "'" +
            ", costPerUnit='" + getCostPerUnit() + "'" +
            "}";
    }

}
