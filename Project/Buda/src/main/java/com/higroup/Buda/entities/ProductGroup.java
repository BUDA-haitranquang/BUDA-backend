package com.higroup.Buda.entities;

import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.junit.experimental.categories.Categories.IncludeCategory;

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
    @JsonBackReference
    private Set<Product> products;

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
