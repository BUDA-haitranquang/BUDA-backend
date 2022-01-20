package com.higroup.Buda.api.business.sell.neworder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellOrderItemDTO {
    @NotNull
    private Long productID;
    @Positive(message = "quantity must be greater than 0")
    private Long quantity;
    @PositiveOrZero(message = "price per unit must be equal or greater than 0")
    private Double pricePerUnit;
}
