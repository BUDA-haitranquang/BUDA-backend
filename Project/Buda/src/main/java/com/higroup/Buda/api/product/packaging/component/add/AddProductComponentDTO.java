package com.higroup.Buda.api.product.packaging.component.add;

import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddProductComponentDTO {
    @PositiveOrZero(message = "productID can not be null")
    private Long productID;
    @PositiveOrZero(message = "ingredientID can not be null")
    private Long ingredientID;
    @PositiveOrZero(message = "required quantity must be non negative")
    private Long requiredQuantity;
}
