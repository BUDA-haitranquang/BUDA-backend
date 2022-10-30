package com.higroup.Buda.api.product.view;

import java.util.List;

import com.higroup.Buda.repositories.ProductRepository.ViewProductInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductViewDTO {
    private Long count;
    private List<ViewProductInfo> products;
}
