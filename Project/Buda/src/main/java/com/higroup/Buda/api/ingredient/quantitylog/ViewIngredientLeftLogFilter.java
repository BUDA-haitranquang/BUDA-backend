package com.higroup.Buda.api.ingredient.quantitylog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ViewIngredientLeftLogFilter {
    private String ingredientSKU;
    private String name;
    private Integer amountLeft;
}
