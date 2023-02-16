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
public class RetailCreateDTO {
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
