package com.higroup.Buda.api.business.buy.print;

import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrintBuyOrderDTO {
    private BuyOrder buyOrder;
    private Store store;
}