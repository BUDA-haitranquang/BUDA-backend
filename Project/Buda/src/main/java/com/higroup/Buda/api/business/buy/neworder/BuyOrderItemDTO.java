package com.higroup.Buda.api.business.buy.neworder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.higroup.Buda.entities.Ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyOrderItemDTO {
    @NotNull
    private Ingredient ingredient;
    @Positive(message = "quantity must be greater than 0")
    @NotNull
    private Integer quantity;
    @PositiveOrZero(message = "price per unit must be equal or greater than 0")
    private Double pricePerUnit;

    
}
