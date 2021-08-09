package com.higroup.Buda.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "Buy_order_item")
public class BuyOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buyOrderItemID;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JsonBackReference
    @JoinColumn(name = "buyOrderID", nullable = true)
    private BuyOrder buyOrder;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JsonBackReference
    @JoinColumn(name = "ingredientID", nullable = true)
    private Ingredient ingredient;
    private int quantity;
    private double pricePerUnit;
    private ZonedDateTime creationTime;
    private Long userID;

    public BuyOrderItem() {
    }

    public BuyOrderItem(BuyOrder buyOrder, Ingredient ingredient, int quantity, double pricePerUnit, ZonedDateTime creationTime, Long userID) {
        this.buyOrder = buyOrder;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.creationTime = creationTime;
        this.userID = userID;
    }

    public BuyOrder getBuyOrder() {
        return buyOrder;
    }

    public void setBuyOrder(BuyOrder buyOrder) {
        this.buyOrder = buyOrder;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public ZonedDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public BuyOrderItem sellOrder(BuyOrder buyOrder) {
        setBuyOrder(buyOrder);
        return this;
    }

    public BuyOrderItem Ingredient(Ingredient ingredient) {
        setIngredient(ingredient);
        return this;
    }

    public BuyOrderItem quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    public BuyOrderItem pricePerUnit(double pricePerUnit) {
        setPricePerUnit(pricePerUnit);
        return this;
    }

    public BuyOrderItem userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public BuyOrderItem creationTime(ZonedDateTime creationTime) {
        setCreationTime(creationTime);
        return this;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyOrderItem that = (BuyOrderItem) o;
        return quantity == that.quantity && Double.compare(that.pricePerUnit, pricePerUnit) == 0 && Objects.equals(buyOrder, that.buyOrder) && Objects.equals(ingredient, that.ingredient) && Objects.equals(creationTime, that.creationTime) && Objects.equals(userID, that.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyOrder, ingredient, quantity, pricePerUnit, creationTime, userID);
    }

    @Override
    public String toString() {
        return "BuyOrderItem{" +
                "buyOrder=" + buyOrder +
                ", ingredient=" + ingredient +
                ", quantity=" + quantity +
                ", pricePerUnit=" + pricePerUnit +
                ", creationTime=" + creationTime +
                ", userID=" + userID +
                '}';
    }
}
