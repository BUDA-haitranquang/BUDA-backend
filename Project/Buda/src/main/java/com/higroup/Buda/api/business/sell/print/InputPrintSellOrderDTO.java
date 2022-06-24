package com.higroup.Buda.api.business.sell.print;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputPrintSellOrderDTO {
    private Long sellOrderID;
    private Long storeID;
}