package com.higroup.Buda.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.higroup.Buda.util.RandomID.RandomIDGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Ingredient", indexes = {
    @Index(columnList = "user_id", name = "ingredient_user_id_index")
})
@JsonIgnoreProperties({"buyOrderItems"})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long ingredientID;
    @Column(name = "ingredient_sku", length = 150)
    private String ingredientSKU;
    @Column(length = 100)
    private String name;
    @Column(length = 1000)
    private String description;
    @Column(columnDefinition = "int default 0", name = "amount_left", nullable = false)
    private Integer amountLeft;
    @Column(columnDefinition = "double default 0.0", name = "price", nullable = false)
    private Double price;
    @Column(columnDefinition = "boolean default true", name = "visible")
    private Boolean visible = Boolean.TRUE;
    @Column(name = "user_id")
    private Long userID;
    @OneToOne
    @JoinColumn(name = "picture_id", referencedColumnName = "picture_id")
    private Picture picture;
    @Column(name = "alert_amount_left", columnDefinition = "int default 0", nullable = false)
    private Integer alertAmountLeft;
    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "ingredient - buy_order_item")
    private Set<BuyOrderItem> buyOrderItems;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return amountLeft == that.amountLeft && Double.compare(that.price, price) == 0 && alertAmountLeft == that.alertAmountLeft && Objects.equals(ingredientID, that.ingredientID) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(userID, that.userID) && Objects.equals(buyOrderItems, that.buyOrderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientID, name, description, amountLeft, price, userID, alertAmountLeft, buyOrderItems);
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
                ", alertAmountLeft=" + alertAmountLeft +
                '}';
    }

}

