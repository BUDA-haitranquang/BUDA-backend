package com.higroup.Buda.api.retail.customDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.PositiveOrZero;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RetailCreateFromIngredientDTO {
    @PositiveOrZero(message = "ingredientID can not be null")
    private Long ingredientID;
    private String productSKU;
    @PositiveOrZero(message = "price must be at least 0")
    private Double price;
}
