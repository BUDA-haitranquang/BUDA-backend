package com.higroup.Buda.customDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductRanking {
    private Long productID;
    private Long name;
    private Double revenue;
    private Long profit;
    private Integer sellNumber;
    private Integer returnNumber;
    private Double returnPrice;

    public ProductRanking(Long productID, Long name, Double revenue, Long profit, Integer sellNumber,
            Integer returnNumber, Double returnPrice) {
        this.productID = productID;
        this.name = name;
        this.revenue = revenue;
        this.profit = profit;
        this.sellNumber = sellNumber;
        this.returnNumber = returnNumber;
        this.returnPrice = returnPrice;
    }

}
