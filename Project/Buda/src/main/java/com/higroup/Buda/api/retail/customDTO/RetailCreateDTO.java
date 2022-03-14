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
public class RetailCreateDTO {
    @NotNull(message = "SKU must not be null")
    private String productSKU;
    @NotNull(message = "SKU must not be null")
    private String ingredientSKU;
    private String name;
    private String description;
    @PositiveOrZero(message = "Amount left must be at least 0")
    private Integer amountLeft;
    @PositiveOrZero(message = "Alert amount must be at least 0")
    private Integer alertAmountLeft;
    @PositiveOrZero(message = "Price must be at least 0")
    private Double price;
    @PositiveOrZero(message = "Selling price must be at least 0")
    private Double sellingPrice;
    private Long pictureID;

}
