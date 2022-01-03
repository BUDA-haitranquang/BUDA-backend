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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Sell_order_item", indexes = {
    @Index(columnList = "sell_order_id", name = "sell_order_item_sell_order_id_index"),
    @Index(columnList = "user_id", name = "sell_order_item_user_id_index"),
    @Index(columnList = "product_id", name = "sell_order_item_product_id_index")
})
@JsonIgnoreProperties({"sellOrder"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
