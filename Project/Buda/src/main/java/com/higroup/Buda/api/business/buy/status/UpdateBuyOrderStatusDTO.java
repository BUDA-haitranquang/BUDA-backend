package com.higroup.Buda.api.business.buy.status;

import com.higroup.Buda.entities.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBuyOrderStatusDTO {
    private Long buyOrderID;
    private Status status;
}
