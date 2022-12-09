package com.higroup.Buda.api.product.quantitylog;

import java.util.List;

import com.higroup.Buda.repositories.ProductLeftLogRepository.ViewProductLeftLogInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductLeftLogViewDTO {
    private Long count;
    private List<ViewProductLeftLogInfo> productLeftLogs;
}
