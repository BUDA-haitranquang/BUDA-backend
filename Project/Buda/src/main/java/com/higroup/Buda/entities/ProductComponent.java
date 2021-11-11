package com.higroup.Buda.entities;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ProductComponent", indexes = {
    @Index(columnList = "user_id", name = "product_component_user_id_index"),
    @Index(columnList = "product_id", name = "product_component_product_id_index"),
    @Index(columnList = "ingredient_id", name = "product_component_ingredient_id_index")
})
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


    public ProductComponent() {
    }

    public ProductComponent(Long productComponentID, Ingredient ingredient, Product product, Long requiredQuantity, Long userID, Double totalCost) {
        this.productComponentID = productComponentID;
        this.ingredient = ingredient;
        this.product = product;
        this.requiredQuantity = requiredQuantity;
        this.userID = userID;
        this.totalCost = totalCost;
    }

    public Long getProductComponentID() {
        return this.productComponentID;
    }

    public void setProductComponentID(Long productComponentID) {
        this.productComponentID = productComponentID;
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getRequiredQuantity() {
        return this.requiredQuantity;
    }

    public void setRequiredQuantity(Long requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Double getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public ProductComponent productComponentID(Long productComponentID) {
        setProductComponentID(productComponentID);
        return this;
    }

    public ProductComponent ingredient(Ingredient ingredient) {
        setIngredient(ingredient);
        return this;
    }

    public ProductComponent product(Product product) {
        setProduct(product);
        return this;
    }

    public ProductComponent requiredQuantity(Long requiredQuantity) {
        setRequiredQuantity(requiredQuantity);
        return this;
    }

    public ProductComponent userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public ProductComponent totalCost(Double totalCost) {
        setTotalCost(totalCost);
        return this;
    }

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
