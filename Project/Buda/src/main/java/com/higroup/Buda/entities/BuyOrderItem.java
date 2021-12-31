package com.higroup.Buda.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "Buy_order_item", indexes = {
    @Index(columnList = "buy_order_id", name = "buy_order_item_buy_order_id_index"),
    @Index(columnList = "user_id", name = "buy_order_item_user_id_index")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buy_order_item_id")
    private Long buyOrderItemID;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JsonBackReference(value = "buy_order - buy_order_item")
    @JoinColumn(name = "buy_Order_ID", nullable = true)
    private BuyOrder buyOrder;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JsonBackReference(value = "ingredient - buy_order_item")
    @JoinColumn(name = "ingredient_ID", nullable = true)
    private Ingredient ingredient;
    private Integer quantity;
    private Double pricePerUnit;
    private ZonedDateTime creationTime;
    @Column(name = "user_id")
    private Long userID;
    @Column(name = "supplier_id")
    private Long supplierID;

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BuyOrderItem)) {
            return false;
        }
        BuyOrderItem buyOrderItem = (BuyOrderItem) o;
        return Objects.equals(buyOrderItemID, buyOrderItem.buyOrderItemID) 
        // && Objects.equals(buyOrder, buyOrderItem.buyOrder) 
        // && Objects.equals(ingredient, buyOrderItem.ingredient) 
        && quantity == buyOrderItem.quantity 
        && pricePerUnit == buyOrderItem.pricePerUnit 
        && Objects.equals(creationTime, buyOrderItem.creationTime) 
        && Objects.equals(userID, buyOrderItem.userID) 
        && Objects.equals(supplierID, buyOrderItem.supplierID);
    }

    @Override
    public int hashCode() {
        //remove from hashCode: buyOrder, ingredient
        return Objects.hash(buyOrderItemID, quantity, pricePerUnit, creationTime, userID, supplierID);
    }

    @Override
    public String toString() {
        return "{" +
            " buyOrderItemID='" + getBuyOrderItemID() + "'" +
            // ", buyOrder='" + getBuyOrder() + "'" +8
            // ", ingredient='" + getIngredient() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", pricePerUnit='" + getPricePerUnit() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", userID='" + getUserID() + "'" +
            ", supplierID='" + getSupplierID() + "'" +
            "}";
    }

}
