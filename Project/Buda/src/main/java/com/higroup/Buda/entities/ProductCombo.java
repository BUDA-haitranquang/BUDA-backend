package com.higroup.Buda.entities;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_combo", indexes = {
    @Index(columnList = "user_id", name = "product_combo_user_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductCombo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_combo_id")
    private Long productComboID;
    private String name;
    private String description;
    @Column(name = "user_id")
    private Long userID;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "product_product_combo",
        joinColumns = @JoinColumn(name = "product_combo_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id"),
        indexes = {
            @Index(name = "product_product_combo_product_combo_id", columnList = "product_combo_id"),
            @Index(name = "product_product_combo_product_id", columnList = "product_id")
        }
    )
    @JsonBackReference
    private Set<Product> products;

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((productComboID == null) ? 0 : productComboID.hashCode());
        result = prime * result + ((userID == null) ? 0 : userID.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductCombo other = (ProductCombo) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (productComboID == null) {
            if (other.productComboID != null)
                return false;
        } else if (!productComboID.equals(other.productComboID))
            return false;
        if (userID == null) {
            if (other.userID != null)
                return false;
        } else if (!userID.equals(other.userID))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "ProductCombo [description=" + description + ", name=" + name + ", productComboID=" + productComboID
                + ", userID=" + userID + "]";
    }
    
}
