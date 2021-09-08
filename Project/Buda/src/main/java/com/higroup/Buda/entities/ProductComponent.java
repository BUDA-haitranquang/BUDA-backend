package com.higroup.Buda.entities;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "ProductComponent")
public class ProductComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ProductComponentID;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JsonBackReference
    @JoinColumn(name = "IngredientID", nullable = true)
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JsonBackReference
    @JoinColumn(name = "ProductID", nullable = true)
    private Product product;

    private Long RequireQuantity;
    private Long userID;
    private Double totalCost;

    public ProductComponent() {
    }

    public ProductComponent(Ingredient ingredient, Product product, Long requireQuantity,
                            Long userID, Double totalCost) {
        this.ingredient = ingredient;
        this.product = product;
        RequireQuantity = requireQuantity;
        this.userID = userID;
        this.totalCost = totalCost;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getRequireQuantity() {
        return RequireQuantity;
    }

    public void setRequireQuantity(Long requireQuantity) {
        RequireQuantity = requireQuantity;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    @Override 
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductComponent productComponent = (ProductComponent) o;
        return 
            productComponent.userID == this.userID &&
            productComponent.totalCost == this.totalCost && 
            productComponent.RequireQuantity == this.RequireQuantity && 
            productComponent.ingredient.equals(this.ingredient) &&
            productComponent.product.equals(this.product);

    }

    @Override
    public String toString() {
        return "ProductComponent{" + 
        "Ingredient=" + ingredient + 
        ",Product=" + product + 
        ",UserID" + userID + 
        ",RequireQuantity=" + RequireQuantity + 
        "totalCost=" + totalCost +
        '}';        
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient, product, RequireQuantity, userID, totalCost);
    }


}
