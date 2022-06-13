package com.higroup.Buda.api.business.sell.view;

import java.util.List;

import com.higroup.Buda.entities.SellOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ViewSellOrderDTO {
    private Long count;
    private List<SellOrder> sellOrders;
}
