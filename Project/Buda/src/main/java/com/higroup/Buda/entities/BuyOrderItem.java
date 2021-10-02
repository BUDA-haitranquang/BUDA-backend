package com.higroup.Buda.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "Buy_order_item", indexes = {
    @Index(columnList = "buy_order_id", name = "buy_order_item_buy_order_id_index"),
    @Index(columnList = "user_id", name = "buy_order_item_user_id_index")
})
public class BuyOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buy_order_item_id")
    private Long buyOrderItemID;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JsonBackReference
    @JoinColumn(name = "buy_Order_ID", nullable = true)
    private BuyOrder buyOrder;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JsonBackReference
    @JoinColumn(name = "ingredient_ID", nullable = true)
    private Ingredient ingredient;
    private int quantity;
    private double pricePerUnit;
    private ZonedDateTime creationTime;
    @Column(name = "user_id")
    private Long userID;
    @Column(name = "supplier_id")
    private Long supplierID;

    public BuyOrderItem() {
    }

    public BuyOrderItem(Long buyOrderItemID, BuyOrder buyOrder, Ingredient ingredient, int quantity, double pricePerUnit, ZonedDateTime creationTime, Long userID, Long supplierID) {
        this.buyOrderItemID = buyOrderItemID;
        this.buyOrder = buyOrder;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.creationTime = creationTime;
        this.userID = userID;
        this.supplierID = supplierID;
    }

    public Long getBuyOrderItemID() {
        return this.buyOrderItemID;
    }

    public void setBuyOrderItemID(Long buyOrderItemID) {
        this.buyOrderItemID = buyOrderItemID;
    }

    public BuyOrder getBuyOrder() {
        return this.buyOrder;
    }

    public void setBuyOrder(BuyOrder buyOrder) {
        this.buyOrder = buyOrder;
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
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

    public ZonedDateTime getCreationTime() {
        return this.creationTime;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getSupplierID() {
        return this.supplierID;
    }

    public void setSupplierID(Long supplierID) {
        this.supplierID = supplierID;
    }

    public BuyOrderItem buyOrderItemID(Long buyOrderItemID) {
        setBuyOrderItemID(buyOrderItemID);
        return this;
    }

    public BuyOrderItem buyOrder(BuyOrder buyOrder) {
        setBuyOrder(buyOrder);
        return this;
    }

    public BuyOrderItem ingredient(Ingredient ingredient) {
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

    public BuyOrderItem creationTime(ZonedDateTime creationTime) {
        setCreationTime(creationTime);
        return this;
    }

    public BuyOrderItem userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public BuyOrderItem supplierID(Long supplierID) {
        setSupplierID(supplierID);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BuyOrderItem)) {
            return false;
        }
        BuyOrderItem buyOrderItem = (BuyOrderItem) o;
        return Objects.equals(buyOrderItemID, buyOrderItem.buyOrderItemID) && Objects.equals(buyOrder, buyOrderItem.buyOrder) && Objects.equals(ingredient, buyOrderItem.ingredient) && quantity == buyOrderItem.quantity && pricePerUnit == buyOrderItem.pricePerUnit && Objects.equals(creationTime, buyOrderItem.creationTime) && Objects.equals(userID, buyOrderItem.userID) && Objects.equals(supplierID, buyOrderItem.supplierID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyOrderItemID, buyOrder, ingredient, quantity, pricePerUnit, creationTime, userID, supplierID);
    }

    @Override
    public String toString() {
        return "{" +
            " buyOrderItemID='" + getBuyOrderItemID() + "'" +
            ", buyOrder='" + getBuyOrder() + "'" +
            ", ingredient='" + getIngredient() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", pricePerUnit='" + getPricePerUnit() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", userID='" + getUserID() + "'" +
            ", supplierID='" + getSupplierID() + "'" +
            "}";
    }

}
