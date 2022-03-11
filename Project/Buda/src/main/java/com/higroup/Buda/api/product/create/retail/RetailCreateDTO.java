package com.higroup.Buda.api.product.create.retail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.PositiveOrZero;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RetailCreateDTO {
    @PositiveOrZero(message = "ingredientID can not be null")
    private Long ingredientID;
    private String productSKU;
    private Double price;
}
