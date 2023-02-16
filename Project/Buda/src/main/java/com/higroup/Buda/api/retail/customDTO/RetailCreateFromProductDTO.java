package com.higroup.Buda.api.retail.customDTO;

import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RetailCreateFromProductDTO {
    @PositiveOrZero(message = "productID can not be null")
    private Long productID;
}
