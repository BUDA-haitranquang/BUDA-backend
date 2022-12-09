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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Product_group", indexes = {
    @Index(columnList = "user_id", name = "product_group_user_id_index")
})
@JsonIgnoreProperties({"properties"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_group_id")
    private Long productGroupID;
    private String name;
    @Column(name = "user_id")
    private Long userID;
    @ManyToMany(mappedBy = "productGroups", fetch = FetchType.LAZY)
    private Set<Product> products;

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
