package com.higroup.Buda.api.product.quantitylog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ViewProductLeftLogFilter {
    private String productSKU;
    private String name;
    private Integer amountLeft;
}
