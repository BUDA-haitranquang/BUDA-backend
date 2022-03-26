package com.higroup.Buda.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_combo_item", indexes = {
    @Index(columnList = "product_id", name = "product_combo_item_product_id"),
    @Index(columnList = "product_combo_id", name = "product_combo_item_product_combo_id")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductComboItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userID;
    @ManyToOne
    @JoinColumn(name = "product_combo_id")
    @JsonBackReference
    private ProductCombo productCombo;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer quantity;
    private Double pricePerUnit;
}
