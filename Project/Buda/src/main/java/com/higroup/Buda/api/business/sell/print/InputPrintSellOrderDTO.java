package com.higroup.Buda.api.business.sell.print;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputPrintSellOrderDTO {
    private Long sellOrderID;
    @Nullable
    private Long storeID;
}