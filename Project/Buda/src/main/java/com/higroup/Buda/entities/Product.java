package com.higroup.Buda.entities;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.higroup.Buda.util.RandomID.RandomIDGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Product", indexes = {
    @Index(columnList = "user_id", name = "product_user_id_index"),
//    @Index(columnList = "product_group_id", name = "product_product_group_id_index")
})
@JsonIgnoreProperties({"sellOrderItems", "productLeftLogs", "productGroups", "productComboItems"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productID;

    @Column(length = 150, name = "product_sku")
    private String productSKU;

    @Column(length = 100)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "visible", columnDefinition = "boolean default true")
    private Boolean visible = Boolean.TRUE;

    // @Column(name = "picture_id")
    // private Long pictureID;
    @OneToOne
    @JoinColumn(name = "picture_id", referencedColumnName = "picture_id")
    private Picture picture;

    @Column(name = "selling_price", columnDefinition = "double default 0.0", nullable = false)
    @PositiveOrZero(message = "Price must be at least 0")
    private Double sellingPrice;

    @Column(name = "alert_amount", columnDefinition = "int default 0", nullable = false)
    @PositiveOrZero(message = "Alert amount must be at least 0")
    private Integer alertAmount;

    @Column(name = "amount_left", columnDefinition = "int default 0", nullable = false)
    @PositiveOrZero(message = "Amount left must be at least 0")
    private Integer amountLeft;

    @Column(name = "cost_per_unit", columnDefinition = "double default 0.0", nullable = false)
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
            inverseJoinColumns = @JoinColumn(name = "product_group_id"),
            indexes = {
                @Index(name = "product_group_component_product_id", columnList = "product_id"),
                @Index(name = "product_group_component_product_group_id", columnList = "product_group_id")
            })
    @JsonBackReference
    private Set<ProductGroup> productGroups;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<ProductComboItem> productComboItems;
    private Long warrantyPeriod;
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
                && Objects.equals(sellingPrice, product.sellingPrice)
                && Objects.equals(alertAmount, product.alertAmount)
                && Objects.equals(amountLeft, product.amountLeft)
                && Objects.equals(costPerUnit, product.costPerUnit)
                && Objects.equals(warrantyPeriod, product.warrantyPeriod);
    }
    
    @Override
    public int hashCode() {
//        return Objects.hash(productID, name, description, userID, pictureID, sellingPrice, alertAmount, amountLeft, costPerUnit, sellOrderItems, productLeftLogs, productGroup);
        return Objects.hash(productID, name, description, userID, sellingPrice, alertAmount, amountLeft, costPerUnit, warrantyPeriod);

    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", userID=" + userID +
                ", sellingPrice=" + sellingPrice +
                ", alertAmount=" + alertAmount +
                ", amountLeft=" + amountLeft +
                ", costPerUnit=" + costPerUnit +
//                ", sellOrderItems=" + sellOrderItems +
//                ", productLeftLogs=" + productLeftLogs +
//                ", productGroups=" + productGroups +
//                ", productComboItems=" + productComboItems +
//                ", warrantyPeriod=" + warrantyPeriod +
                '}';
    }

}
