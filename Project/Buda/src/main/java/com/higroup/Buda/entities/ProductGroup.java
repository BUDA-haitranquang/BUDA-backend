package com.higroup.Buda.entities;

import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.junit.experimental.categories.Categories.IncludeCategory;

@Entity
@Table(name = "Product_group", indexes = {
    @Index(columnList = "user_id", name = "product_group_user_id_index")
})
@JsonIgnoreProperties({"properties"})
public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_group_id")
    private Long productGroupID;
    private String name;
    @Column(name = "user_id")
    private Long userID;
    @ManyToMany(mappedBy = "productGroups", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Product> products;

    public ProductGroup() {
    }

    public ProductGroup(Long productGroupID, String name, Long userID, Set<Product> products) {
        this.productGroupID = productGroupID;
        this.name = name;
        this.userID = userID;
        this.products = products;
    }

    public Long getProductGroupID() {
        return this.productGroupID;
    }

    public void setProductGroupID(Long productGroupID) {
        this.productGroupID = productGroupID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public ProductGroup productGroupID(Long productGroupID) {
        setProductGroupID(productGroupID);
        return this;
    }

    public ProductGroup name(String name) {
        setName(name);
        return this;
    }

    public ProductGroup userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public ProductGroup products(Set<Product> products) {
        setProducts(products);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProductGroup)) {
            return false;
        }
        ProductGroup productGroup = (ProductGroup) o;
        return Objects.equals(productGroupID, productGroup.productGroupID) && Objects.equals(name, productGroup.name) && Objects.equals(userID, productGroup.userID) && Objects.equals(products, productGroup.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productGroupID, name, userID, products);
    }

    @Override
    public String toString() {
        return "{" +
            " productGroupID='" + getProductGroupID() + "'" +
            ", name='" + getName() + "'" +
            ", userID='" + getUserID() + "'" +
            ", products='" + getProducts() + "'" +
            "}";
    }
       
}
