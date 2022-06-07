package com.higroup.Buda.api.business.buy.print;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputPrintBuyOrderDTO {
    private Long buyOrderID;
    private Long storeID;
}