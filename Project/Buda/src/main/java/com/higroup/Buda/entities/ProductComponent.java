package com.higroup.Buda.entities;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ProductComponent", indexes = {
    @Index(columnList = "user_id", name = "product_component_user_id_index"),
    @Index(columnList = "product_id", name = "product_component_product_id_index"),
    @Index(columnList = "ingredient_id", name = "product_component_ingredient_id_index")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"product"})
public class ProductComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_component_id")
    private Long productComponentID;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    //@JsonBackReference(value = "ingredient - product_component")
    @JoinColumn(name = "Ingredient_ID", nullable = true)
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JsonBackReference(value = "product - product_component")
    @JoinColumn(name = "Product_ID", nullable = true)
    private Product product;

    private Long requiredQuantity;
    @Column(name = "user_id")
    private Long userID;
    private Double totalCost;

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProductComponent)) {
            return false;
        }
        ProductComponent productComponent = (ProductComponent) o;
        return Objects.equals(productComponentID, productComponent.productComponentID)
                && Objects.equals(ingredient, productComponent.ingredient)
                && Objects.equals(product, productComponent.product)
                && Objects.equals(requiredQuantity, productComponent.requiredQuantity)
                && Objects.equals(userID, productComponent.userID)
                && Objects.equals(totalCost, productComponent.totalCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productComponentID, ingredient, product, requiredQuantity, userID, totalCost);
    }

    @Override
    public String toString() {
        return "{" +
            " productComponentID='" + getProductComponentID() + "'" +
            ", ingredient='" + getIngredient() + "'" +
            ", product='" + getProduct() + "'" +
            ", requiredQuantity='" + getRequiredQuantity() + "'" +
            ", userID='" + getUserID() + "'" +
            ", totalCost='" + getTotalCost() + "'" +
            "}";
    }

    

}
