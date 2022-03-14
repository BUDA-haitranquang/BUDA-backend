package com.higroup.Buda.api.retail.customDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RetailCreateFromProductDTO {
    @PositiveOrZero(message = "productID can not be null")
    private Long productID;
    @NotNull(message = "SKU must not be null")
    private String ingredientSKU;
}
