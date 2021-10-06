package com.higroup.Buda.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Product", indexes = {
    @Index(columnList = "user_id", name = "product_user_id_index"),
    @Index(columnList = "product_group_id", name = "product_product_group_id_index")
})
@JsonIgnoreProperties({"sellOrderItems", "productLeftLogs"})
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
    @Column(name = "picture_id")
    private Long pictureID;
    private double sellingPrice;
    private int alertAmount;
    private int amountLeft;
    private double costPerUnit;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "product - sell_order_item")
    private Set<SellOrderItem> sellOrderItems;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<ProductLeftLog> productLeftLogs;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "product_Group_ID", nullable = true)
    @JsonBackReference
    private ProductGroup productGroup;

    public Product() {
    }

    public Product(Long productID, String name, String description, Long userID, Long pictureID, double sellingPrice, int alertAmount, int amountLeft, double costPerUnit, Set<SellOrderItem> sellOrderItems, Set<ProductLeftLog> productLeftLogs, ProductGroup productGroup) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.userID = userID;
        this.pictureID = pictureID;
        this.sellingPrice = sellingPrice;
        this.alertAmount = alertAmount;
        this.amountLeft = amountLeft;
        this.costPerUnit = costPerUnit;
        this.sellOrderItems = sellOrderItems;
        this.productLeftLogs = productLeftLogs;
        this.productGroup = productGroup;
    }

    public Long getProductID() {
        return this.productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getPictureID() {
        return this.pictureID;
    }

    public void setPictureID(Long pictureID) {
        this.pictureID = pictureID;
    }

    public double getSellingPrice() {
        return this.sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getAlertAmount() {
        return this.alertAmount;
    }

    public void setAlertAmount(int alertAmount) {
        this.alertAmount = alertAmount;
    }

    public int getAmountLeft() {
        return this.amountLeft;
    }

    public void setAmountLeft(int amountLeft) {
        this.amountLeft = amountLeft;
    }

    public double getCostPerUnit() {
        return this.costPerUnit;
    }

    public void setCostPerUnit(double costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public Set<SellOrderItem> getSellOrderItems() {
        return this.sellOrderItems;
    }

    public void setSellOrderItems(Set<SellOrderItem> sellOrderItems) {
        this.sellOrderItems = sellOrderItems;
    }

    public Set<ProductLeftLog> getProductLeftLogs() {
        return this.productLeftLogs;
    }

    public void setProductLeftLogs(Set<ProductLeftLog> productLeftLogs) {
        this.productLeftLogs = productLeftLogs;
    }

    public ProductGroup getProductGroup() {
        return this.productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public Product productID(Long productID) {
        setProductID(productID);
        return this;
    }

    public Product name(String name) {
        setName(name);
        return this;
    }

    public Product description(String description) {
        setDescription(description);
        return this;
    }

    public Product userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public Product pictureID(Long pictureID) {
        setPictureID(pictureID);
        return this;
    }

    public Product sellingPrice(double sellingPrice) {
        setSellingPrice(sellingPrice);
        return this;
    }

    public Product alertAmount(int alertAmount) {
        setAlertAmount(alertAmount);
        return this;
    }

    public Product amountLeft(int amountLeft) {
        setAmountLeft(amountLeft);
        return this;
    }

    public Product costPerUnit(double costPerUnit) {
        setCostPerUnit(costPerUnit);
        return this;
    }

    public Product sellOrderItems(Set<SellOrderItem> sellOrderItems) {
        setSellOrderItems(sellOrderItems);
        return this;
    }

    public Product productLeftLogs(Set<ProductLeftLog> productLeftLogs) {
        setProductLeftLogs(productLeftLogs);
        return this;
    }

    public Product productGroup(ProductGroup productGroup) {
        setProductGroup(productGroup);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(productID, product.productID) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(userID, product.userID) && Objects.equals(pictureID, product.pictureID) && sellingPrice == product.sellingPrice && alertAmount == product.alertAmount && amountLeft == product.amountLeft && costPerUnit == product.costPerUnit && Objects.equals(sellOrderItems, product.sellOrderItems) && Objects.equals(productLeftLogs, product.productLeftLogs) && Objects.equals(productGroup, product.productGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, name, description, userID, pictureID, sellingPrice, alertAmount, amountLeft, costPerUnit, sellOrderItems, productLeftLogs, productGroup);
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
            ", productLeftLogs='" + getProductLeftLogs() + "'" +
            ", productGroup='" + getProductGroup() + "'" +
            "}";
    }
    
}
