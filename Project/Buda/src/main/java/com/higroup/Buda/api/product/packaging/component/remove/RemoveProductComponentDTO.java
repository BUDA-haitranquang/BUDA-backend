package com.higroup.Buda.api.product.packaging.component.remove;

import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemoveProductComponentDTO {
    @PositiveOrZero
    private Long productID;
    @PositiveOrZero
    private Long ingredientID;
}
