package com.higroup.Buda.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Product", indexes = {
    @Index(columnList = "user_id", name = "product_user_id_index"),
//    @Index(columnList = "product_group_id", name = "product_product_group_id_index")
})
@JsonIgnoreProperties({"sellOrderItems", "productLeftLogs", "productGroups"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productID;

    @Column(length = 100)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "visible", columnDefinition = "boolean default true")
    private Boolean visible = Boolean.TRUE;

    @Column(name = "picture_id")
    private Long pictureID;

    @Column(name = "selling_price", columnDefinition = "double default 0.0")
    @PositiveOrZero(message = "Price must be at least 0")
    private Double sellingPrice;

    @Column(name = "alert_amount", columnDefinition = "int default 0")
    @PositiveOrZero(message = "Alert amount must be at least 0")
    private Integer alertAmount;

    @Column(name = "amount_left", columnDefinition = "int default 0")
    @PositiveOrZero(message = "Amount left must be at least 0")
    private Integer amountLeft;

    @Column(name = "cost_per_unit", columnDefinition = "double default 0.0")
    @PositiveOrZero(message = "Cost must be at least 0")
    private Double costPerUnit;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "product - sell_order_item")
    private Set<SellOrderItem> sellOrderItems;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<ProductLeftLog> productLeftLogs;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_group_component",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "product_group_id"))
    @JsonBackReference
    private Set<ProductGroup> productGroups;

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(productID, product.productID)
                && Objects.equals(name, product.name)
                && Objects.equals(description, product.description)
                && Objects.equals(userID, product.userID)
                && Objects.equals(pictureID, product.pictureID)
                && Objects.equals(sellingPrice, product.sellingPrice)
                && Objects.equals(alertAmount, product.alertAmount)
                && Objects.equals(amountLeft, product.amountLeft)
                && Objects.equals(costPerUnit, product.costPerUnit)
                && Objects.equals(sellOrderItems, product.sellOrderItems)
                && Objects.equals(productLeftLogs, product.productLeftLogs)
                && Objects.equals(productGroups, product.productGroups);
    }
    
    @Override
    public int hashCode() {
//        return Objects.hash(productID, name, description, userID, pictureID, sellingPrice, alertAmount, amountLeft, costPerUnit, sellOrderItems, productLeftLogs, productGroup);
        return Objects.hash(productID, name, description, userID, pictureID, sellingPrice, alertAmount, amountLeft, costPerUnit, sellOrderItems);

    }

    @Override
    public String toString() {
        return "{" +
            " productID='" + getProductID() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", userID='" + getUserID() + "'" +
            ", pictureID='" + getPictureID() + "'" +
            ", sellingPrice='" + getSellingPrice() + "'" +
            ", alertAmount='" + getAlertAmount() + "'" +
            ", amountLeft='" + getAmountLeft() + "'" +
            ", costPerUnit='" + getCostPerUnit() + "'" +
            ", sellOrderItems='" + getSellOrderItems() + "'" +
            // ", productLeftLogs='" + getProductLeftLogs() + "'" +
            // ", productGroup='" + getProductGroup() + "'" +
            "}";
    }
    
}
