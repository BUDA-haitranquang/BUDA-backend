package com.higroup.Buda.api.business.sell.print;

import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrintSellOrderDTO {
    private SellOrder SellOrder;
    private Store store;
}