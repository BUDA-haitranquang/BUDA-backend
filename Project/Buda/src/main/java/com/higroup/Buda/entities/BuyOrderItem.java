package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "buy_order_item_id")
    private Long buyOrderItemID;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JsonBackReference(value = "buy_order - buy_order_item")
    @JoinColumn(name = "buy_Order_ID", nullable = true)
    private BuyOrder buyOrder;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
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
