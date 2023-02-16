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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

