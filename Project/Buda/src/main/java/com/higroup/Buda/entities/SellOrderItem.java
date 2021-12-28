package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.Objects;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.higroup.Buda.entities.enumeration.AgeGroup;
import com.higroup.Buda.entities.enumeration.Gender;

@Entity
@Table(name = "Sell_order_item", indexes = {
    @Index(columnList = "sell_order_id", name = "sell_order_item_sell_order_id_index"),
    @Index(columnList = "user_id", name = "sell_order_item_user_id_index"),
    @Index(columnList = "product_id", name = "sell_order_item_product_id_index")
})
@JsonIgnoreProperties({"sellOrder"})
public class SellOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sell_order_item_id")
    private Long sellOrderItemID;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JsonBackReference(value = "sell_order - sell_order_item")
    @JoinColumn(name = "sell_Order_ID", nullable = true)
    private SellOrder sellOrder;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JsonBackReference(value = "product - sell_order_item")
    @JoinColumn(name = "product_ID", nullable = true)
    private Product product;
    private Integer quantity;
    private Double pricePerUnit;
    @Column(name = "user_id")
    private Long userID;
    private ZonedDateTime creationTime;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup;
    private Double actualTotalSale;
    private Double costPerUnit;

    public SellOrderItem() {
    }

    public SellOrderItem(SellOrder sellOrder, Product product, Integer quantity, Double pricePerUnit, Long userID, ZonedDateTime creationTime, Gender gender, AgeGroup ageGroup, Double actualTotalSale, Double costPerUnit) {
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
    public Long getSellOrderItemID()
    {
        return this.sellOrderItemID;
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

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPricePerUnit() {
        return this.pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
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

    public Double getActualTotalSale() {
        return this.actualTotalSale;
    }

    public void setActualTotalSale(Double actualTotalSale) {
        this.actualTotalSale = actualTotalSale;
    }

    public Double getCostPerUnit() {
        return this.costPerUnit;
    }

    public void setCostPerUnit(Double costPerUnit) {
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

    public SellOrderItem quantity(Integer quantity) {
        setQuantity(quantity);
        return this;
    }

    public SellOrderItem pricePerUnit(Double pricePerUnit) {
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

    public SellOrderItem actualTotalSale(Double actualTotalSale) {
        setActualTotalSale(actualTotalSale);
        return this;
    }

    public SellOrderItem costPerUnit(Double costPerUnit) {
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
        return quantity == sellOrderItem.quantity && pricePerUnit == sellOrderItem.pricePerUnit && Objects.equals(userID, sellOrderItem.userID) && Objects.equals(creationTime, sellOrderItem.creationTime) && Objects.equals(gender, sellOrderItem.gender) && Objects.equals(ageGroup, sellOrderItem.ageGroup) && actualTotalSale == sellOrderItem.actualTotalSale && costPerUnit == sellOrderItem.costPerUnit;
    }

    @Override
    public int hashCode() {
        return 5;
    }

    @Override
    public String toString() {
        return "{" +
            //" sellOrder='" + getSellOrder() + "'" +
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
