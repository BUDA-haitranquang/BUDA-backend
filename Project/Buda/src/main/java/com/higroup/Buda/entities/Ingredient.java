package com.higroup.Buda.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Ingredient", indexes = {
    @Index(columnList = "user_id", name = "ingredient_user_id_index")
})
@JsonIgnoreProperties({"buyOrderItems"})
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long ingredientID;
    @Column(length = 100)
    private String name;
    @Column(length = 1000)
    private String description;
    @Column(columnDefinition = "int default 0", name = "amount_left")
    private Integer amountLeft;
    @Column(columnDefinition = "double default 0.0", name = "price")
    private Double price;
    @Column(columnDefinition = "boolean default true", name = "visible")
    private Boolean visible;
    @Column(name = "user_id")
    private Long userID;
    @Column(name = "picture_id")
    private Long pictureID;
    @Column(name = "alert_amount_left", columnDefinition = "int default 0")
    private Integer alertAmountLeft;
    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "ingredient - buy_order_item")
    private Set<BuyOrderItem> buyOrderItems;

    public Ingredient() {
    }

    public Boolean isVisible() {
        return this.visible;
    }

    public Boolean getVisible() {
        return this.visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }


    public Ingredient(Long ingredientID, String name, String description, Integer amountLeft, Double price, Boolean visible, Long userID, Long pictureID, Integer alertAmountLeft, Set<BuyOrderItem> buyOrderItems) {
        this.ingredientID = ingredientID;
        this.name = name;
        this.description = description;
        this.amountLeft = amountLeft;
        this.price = price;
        this.visible = visible;
        this.userID = userID;
        this.pictureID = pictureID;
        this.alertAmountLeft = alertAmountLeft;
        this.buyOrderItems = buyOrderItems;
    }
    
    public Long getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(Long IngredientID) {
        this.ingredientID = IngredientID;
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

    public Integer getAmountLeft() {
        return amountLeft;
    }

    public void setAmountLeft(Integer amountLeft) {
        this.amountLeft = amountLeft;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getUserID() {
        return userID;
    }
    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Set<BuyOrderItem> getBuyOrderItems() {
        return this.buyOrderItems;
    }

    public void setBuyOrderItems(Set<BuyOrderItem> buyOrderItems) {
        this.buyOrderItems = buyOrderItems;
    }

    public void setStoreID(Long storeID) {
        this.userID = storeID;
    }

    public Long getPictureID() {
        return pictureID;
    }

    public void setPictureID(Long pictureID) {
        this.pictureID = pictureID;
    }

    public Integer getAlertAmountLeft() {
        return alertAmountLeft;
    }

    public void setAlertAmountLeft(Integer alertAmountLeft) {
        this.alertAmountLeft = alertAmountLeft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return amountLeft == that.amountLeft && Double.compare(that.price, price) == 0 && alertAmountLeft == that.alertAmountLeft && Objects.equals(ingredientID, that.ingredientID) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(userID, that.userID) && Objects.equals(pictureID, that.pictureID) && Objects.equals(buyOrderItems, that.buyOrderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientID, name, description, amountLeft, price, userID, pictureID, alertAmountLeft, buyOrderItems);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "IngredientID=" + ingredientID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amountLeft=" + amountLeft +
                ", price=" + price +
                ", userID=" + userID +
                ", pictureID=" + pictureID +
                ", alertAmountLeft=" + alertAmountLeft +
                '}';
    }

}

