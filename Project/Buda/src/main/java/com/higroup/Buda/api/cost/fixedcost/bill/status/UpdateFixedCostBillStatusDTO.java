package com.higroup.Buda.api.cost.fixedcost.bill.status;

import com.higroup.Buda.entities.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFixedCostBillStatusDTO {
    private Long fixedCostBillID;
    private Status status;
}
