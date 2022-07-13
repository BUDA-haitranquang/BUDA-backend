package com.higroup.Buda.api.cost.othercost.status;

import com.higroup.Buda.entities.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOtherCostStatusDTO {
    private Long otherCostID;
    private Status status;
}
