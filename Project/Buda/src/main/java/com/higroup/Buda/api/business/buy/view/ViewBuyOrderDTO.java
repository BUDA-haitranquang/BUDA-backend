package com.higroup.Buda.api.business.buy.view;

import java.util.List;

import com.higroup.Buda.entities.BuyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewBuyOrderDTO {
    private Long count;
    private List<BuyOrder> buyOrders;
}
