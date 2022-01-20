package com.higroup.Buda.customDTO;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterSellOrderItem {
    @NotNull
    private Long productID;
    @NotNull
    private Long sellOrderID;
    @NotNull
    private Integer quantity;
    @NotNull
    private Double pricePerUnit;
}
