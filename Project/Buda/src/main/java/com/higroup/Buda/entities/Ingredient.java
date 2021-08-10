package com.higroup.Buda.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;
    @Column(length = 100)
    private String name;
    @Column(length = 1000)
    private String description;
    private int amountLeft;
    private double price;
    private Long storeID;
    private Long pictureID;
    private int alertAmountLeft;
    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<BuyOrderItem> buyOrderItems;

    public Ingredient() {
    }

    public Ingredient(Long productID, String name, String description, int amountLeft, double price, Long storeID, Long pictureID, int alertAmountLeft) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.amountLeft = amountLeft;
        this.price = price;
        this.storeID = storeID;
        this.pictureID = pictureID;
        this.alertAmountLeft = alertAmountLeft;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmountLeft() {
        return amountLeft;
    }

    public void setAmountLeft(int amountLeft) {
        this.amountLeft = amountLeft;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getStoreID() {
        return storeID;
    }

    public void setStoreID(Long storeID) {
        this.storeID = storeID;
    }

    public Long getPictureID() {
        return pictureID;
    }

    public void setPictureID(Long pictureID) {
        this.pictureID = pictureID;
    }

    public int getAlertAmountLeft() {
        return alertAmountLeft;
    }

    public void setAlertAmountLeft(int alertAmountLeft) {
        this.alertAmountLeft = alertAmountLeft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return amountLeft == that.amountLeft && Double.compare(that.price, price) == 0 && alertAmountLeft == that.alertAmountLeft && Objects.equals(productID, that.productID) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(storeID, that.storeID) && Objects.equals(pictureID, that.pictureID) && Objects.equals(buyOrderItems, that.buyOrderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, name, description, amountLeft, price, storeID, pictureID, alertAmountLeft, buyOrderItems);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "productID=" + productID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amountLeft=" + amountLeft +
                ", price=" + price +
                ", storeID=" + storeID +
                ", pictureID=" + pictureID +
                ", alertAmountLeft=" + alertAmountLeft +
                '}';
    }
}
