package com.higroup.Buda.api.business.sell.status;

import com.higroup.Buda.entities.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSellOrderStatusDTO {
    private Long sellOrderID;
    private Status status;
}
